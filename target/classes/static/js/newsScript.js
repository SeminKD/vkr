function showText(id, obj) {
    var element = document.getElementById(id);
    var childElement = obj.querySelector(".prev");
    if (element.style.display === 'none'){
        childElement.style.display = 'none';
        obj.style.height = 'auto';
        element.style.display = 'block';
    }
    else {
        element.style.display = 'none';
        childElement.style.display = 'block';
        obj.style.height = '150px';
    }
}

window.addEventListener('click',function (e) {
    var block = document.getElementById('yearCont');
        var div = $("#filter");
        if (div.is(e.target) && block.style.display === 'none'){
            block.style.display = 'block';
            block.style.opacity = 1;}
        else
            block.style.display = 'none';
    });


