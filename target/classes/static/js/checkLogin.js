function check() {
    if(document.location.href === "http://localhost:8080/login?error")
        alert("Неверный логин и пароль");
}