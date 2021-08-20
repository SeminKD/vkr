function control() {
    let element = document.getElementById('input-date');
    let date = Date.parse(element.value);
    if(isNaN(date)){
        element.focus();
        alert("Введите корректную дату");
        return;
    }
    let array = element.value.split('-');
    if(array.length !== 3 || array[0].length !== 4){
        element.focus();
        alert("Введите корректную дату");
        return;
    }
    for(i=0;i<array.length;i++){
        if(isNaN(parseInt(array[i]))){
            element.focus();
            alert("Введите корректную дату");
            return;
        }
    }
}

function controlNumber() {
    let element = document.getElementById('input-price');
    let number = element.value;
    if(isNaN(Number(number)) || number===''){
        element.focus();
        alert("Введите корректную цену");
    }
}