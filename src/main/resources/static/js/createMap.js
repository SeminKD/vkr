ymaps.ready(init);

let client;
let gCol;

function init() {
    gCol = new ymaps.GeoObjectCollection({}, {
        preset: 'islands#blueFuelStationIcon', //все метки красные
        draggable: false// и их можно перемещать
    });
    myMap = new ymaps.Map("map", {
        center: [53.21819458, 50.21817047],
        zoom: 11
    });
    ymaps.geolocation.get({
        provider: 'auto',
        mapStateAutoApply: false
    }).then(function (result) {
        client = result.geoObjects.get(0).geometry.getCoordinates();
        result.geoObjects.options.set('preset', 'islands#geolocationIcon');
        myMap.geoObjects.add(result.geoObjects);
    });
    myMap.behaviors.disable('scrollZoom');
    editMap();
}

function way() {

    myMap.controls.add('routePanelControl');
    let control = myMap.controls.get('routePanelControl');

    let storage = ymaps.geoQuery(myMap.geoObjects);
    let sorted = storage.sortByDistance(client);

    // Зададим состояние панели для построения машрутов.
    control.routePanel.state.set({
        // Тип маршрутизации.
        type: 'auto',
        // Выключим возможность задавать пункт отправления в поле ввода.
        fromEnabled: true,
        // Адрес или координаты пункта отправления.
        from: client,
        // Включим возможность задавать пункт назначения в поле ввода.
        toEnabled: false,

        to: sorted.get(1).geometry.getCoordinates()
        // Адрес или координаты пункта назначения.
        //to: 'Петербург'
    });

    // Зададим опции панели для построения машрутов.
    control.routePanel.options.set({
        // Запрещаем показ кнопки, позволяющей менять местами начальную и конечную точки маршрута.
        allowSwitch: false,
        // Включим определение адреса по координатам клика.
        // Адрес будет автоматически подставляться в поле ввода на панели, а также в подпись метки маршрута.
        reverseGeocoding: true,
        // Зададим виды маршрутизации, которые будут доступны пользователям для выбора.
        types: { auto: true, masstransit: true, pedestrian: true, taxi: true }
    });
}

function blocWay(){
    myMap.setCenter([53.21819458, 50.21817047]);
    myMap.setZoom(11);
    myMap.controls.remove('routePanelControl');
}

function editMap() {
    let url = "http://localhost:8080/mapFiltered?";

    let fuel = "", service = "", product = "";

    let fuelInfo = document.getElementsByClassName('radio-button-fuel');
    let serviceInfo = document.getElementsByClassName('radio-button-service');
    let productInfo = document.getElementsByClassName('radio-button-product');

    let test = 0;

    for (i = 0; i < fuelInfo.length; i++) {
        if (test !== 0 && fuelInfo[i].checked) {
            fuel += ",'" + fuelInfo[i].name + "'";
            ++test;
        }
        if (test === 0 && fuelInfo[i].checked) {
            fuel += "'" + fuelInfo[i].name + "'";
            ++test;
        }
    }
    if (fuel !== "")
        url += "fuel=" + fuel + "&";
    else
        url += "fuel=all&"

    test = 0;
    for (i = 0; i < serviceInfo.length; i++) {
        if (test !== 0 && serviceInfo[i].checked) {
            service += ",'" + serviceInfo[i].name + "'";
            ++test;
        }
        if (test === 0 && serviceInfo[i].checked) {
            service += "'" + serviceInfo[i].name + "'";
            ++test;
        }
    }
    if (service !== "")
        url += "service=" + service + "&";
    else
        url += "service=all&"

    test =0;
    for (i = 0; i < productInfo.length; i++) {
        if (test !== 0 && productInfo[i].checked) {
            product += ",'" + productInfo[i].name + "'";
            ++test;
        }
        if (test === 0 && productInfo[i].checked) {
            product += "'" + productInfo[i].name + "'";
            ++test;
        }
    }
    if (product !== "")
        url += "product=" + product;
    else
        url += "product=all";
    let request = new XMLHttpRequest();
    request.open('GET',url,true);
    request.addEventListener('readystatechange', function() {
        gCol.removeAll();
        if ((request.readyState==4) && (request.status==200)) {
            const element = request.response;
            let elements = JSON.parse(element);
            if(elements.addresses.length === 0)
                alert("К сожадению, заправок, отвечающим указанным требованиям не найдено");
            for(i=0; i<elements.addresses.length;i++){
                ymaps.geocode(elements.addresses[i],{}).then(function f(res) {
                    var geoObject = res.geoObjects.get(0);
                    var coords = geoObject.geometry.getCoordinates();
                    var placemark = new ymaps.Placemark(coords,{
                        hintContent: 'СамараАЗС',
                        balloonContent: [
                            '<img src="../media/logo.png" style="height: 40px; width: auto;">' +
                            '<p>' +
                            '<div style="font-size: small">ПАО "СамараАЗС"</div>' +
                            '<p>' +
                            '<div style="font-size: small">+ 7 927 907 74 07</div>'
                        ]
                    });
                    placemark.events.add('click', function (e) {
                            let control = myMap.controls.get('routePanelControl');
                        if (control != null) {
                            control.routePanel.state.set({
                                // Тип маршрутизации.
                                type: 'auto',
                                // Выключим возможность задавать пункт отправления в поле ввода.
                                fromEnabled: true,
                                // Адрес или координаты пункта отправления.
                                from: client,
                                // Включим возможность задавать пункт назначения в поле ввода.
                                toEnabled: false,

                                to: placemark.geometry.getCoordinates()
                                // Адрес или координаты пункта назначения.
                                //to: 'Петербург'
                            });
                        }
                    })
                    gCol.add(placemark);
                });
            }
            myMap.geoObjects.add(gCol);
        }
    });
    request.send();
}