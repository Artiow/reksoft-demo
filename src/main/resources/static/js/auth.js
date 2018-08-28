/**
 * @param login {string}
 * @return {string}
 */
function authorizeComponent(login) {
    return "<li class=\"nav-item btn-group\">\n" +
        "<a class=\"btn btn-outline-secondary\" href=\"/home\">" + login + "</a>\n" +
        "<a class=\"btn btn-outline-danger\" id=\"nav-auth-logout\" href=\"#\">выйти</a>\n" +
        "</li>\n"
}

/**
 * @return {string}
 */
function unauthorizeComponent() {
    return "<li class=\"nav-item\">\n" +
        "<a class=\"btn btn-outline-secondary\" id=\"nav-auth-login\" href=\"#\">войти</a>\n" +
        "</li>\n"
}

function setAuth() {
    const authContainer = $('#nav-auth-container').empty();
    const login = localStorage.getItem('userID');
    if (login !== null) {
        authContainer.html(authorizeComponent(login));
    } else {
        authContainer.html(unauthorizeComponent());
    }

    $('#nav-auth-logout').on('click', function () {
        event.preventDefault();
        ajaxLogout();
    });
    $('#nav-auth-login').on('click', function () {
        event.preventDefault();
        localStorage.setItem('redirect', ($(location).attr('pathname')));
        window.location.replace(DEFAULT_FAIL_REDIRECT);
    });
}