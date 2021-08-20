var scroll = 0;

window.addEventListener('scroll', function() {
    var pos = $(this).scrollTop();
    var el = document.getElementById('menu');
    if(scroll > pos){
        el.style.transform = 'scaleY(1)';
    }
    else{
        el.style.transform = 'scaleY(0)';
    }
    scroll = pos;
});
