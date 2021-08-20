window.onload = function () {
    let a = document.querySelector('#fuel_text');
    let b = document.querySelector('#fuel');
    let c = document.querySelector('#prod_text');
    let d = document.querySelector('#prod');
    a.onmouseout = function(e) {
        document.getElementById('fuel_text').style.textDecoration='none';
        document.getElementById('fuel').style.textDecoration='none';
    }
    a.onmouseover = function(e) {
        document.getElementById('fuel_text').style.textDecoration='underline';
        document.getElementById('fuel').style.textDecoration='underline';
    };
    b.onmouseout = function(e) {
        document.getElementById('fuel_text').style.textDecoration='none';
        document.getElementById('fuel').style.textDecoration='none';
    }
    b.onmouseover = function(e) {
        document.getElementById('fuel_text').style.textDecoration='underline';
        document.getElementById('fuel').style.textDecoration='underline';
    };
    c.onmouseout = function(e) {
        document.getElementById('prod_text').style.textDecoration='none';
        document.getElementById('prod').style.textDecoration='none';
    }
    c.onmouseover = function(e) {
        document.getElementById('prod_text').style.textDecoration='underline';
        document.getElementById('prod').style.textDecoration='underline';
    };
    d.onmouseout = function(e) {
        document.getElementById('prod_text').style.textDecoration='none';
        document.getElementById('prod').style.textDecoration='none';
    }
    d.onmouseover = function(e) {
        document.getElementById('prod_text').style.textDecoration='underline';
        document.getElementById('prod').style.textDecoration='underline';
    };
}

function redirectFuel(){
    document.location.href = "http://localhost:8080/fuel";
}

function redirectProd(){
    document.location.href = "http://localhost:8080/otherProducts";
}

function redirectAdminFuel(){
    document.location.href = "http://localhost:8080/admin-fuel";
}

function redirectAdminProd(){
    document.location.href = "http://localhost:8080/admin-otherProducts";
}