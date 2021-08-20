function showText(id, obj) {
    var element = document.getElementById(id);
    var childElement = obj.querySelector(".imgFuel");
    if (element.style.display === 'none'){
        childElement.style.display = 'none';
        obj.style.height = 'auto';
        element.style.display = 'inline-block';
    }
    else {
        element.style.display = 'none';
        childElement.style.display = 'block';
    }
}