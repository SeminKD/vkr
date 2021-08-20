function initStation() {
    let val = document.getElementById('idSt').value;
    let url = "http://localhost:8080/getFPS?id="+val;
    let request = new XMLHttpRequest();
    request.open('GET',url,true);
    request.addEventListener('readystatechange', function() {
        if ((request.readyState==4) && (request.status==200)) {
            const element = request.response;
            let elements = JSON.parse(element);
            for(i=0; i<elements.length;i++){
                for (j=0; j<elements[i].length;j++){
                    let el = elements[i][j];
                    document.getElementById(el.name).checked = true;
                }
            }
        }
    });
    request.send();
}

function updateFPS() {
    let val = document.getElementById('idSt').value;
    let url = "http://localhost:8080/updateFPS?id=" + val + "&";

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

    test = 0;
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
    request.open('POST', url, true);
    request.addEventListener('readystatechange', function () {
        if ((request.readyState == 4) && (request.status == 200)) {
            alert("Успешно обновлено");
        }
    });
    request.send();
}