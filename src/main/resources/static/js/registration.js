const PASSWORD_DO_NOT_MATCH = 'Введенные пароли не совпадают!';

$(function () {
    $('#registration').on("submit", function () {
        event.preventDefault();

        const password = $('#password').val();
        const confirm = $('#confirm').val();

        if (password === confirm) {
            const userData = {
                name: $('#name').val(),
                surname: $('#surname').val(),
                patronymic: $('#patronymic').val(),
                address: $('#address').val(),
                phone: $('#phone').val(),
                login: $('#login').val(),
                password: password
            };

            ajaxRegistration(userData, function (response) {
                $('#message').empty().append(response.message);
            });
        } else {
            $('#message').empty().append(PASSWORD_DO_NOT_MATCH);
        }
    })
});