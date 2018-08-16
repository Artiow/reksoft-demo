$(function () {
    $('#login').on("submit", function () {
        const login = $('#inputLogin').val();
        const password = $('#inputPassword').val();

        event.preventDefault();
        ajaxLogin(login, password, function (response) {
            $('#message').empty().append(response.message);
        });
    })
});