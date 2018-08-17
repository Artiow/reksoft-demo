const PASSWORD_DO_NOT_MATCH = 'Введенные пароли не совпадают!';

$(function () {
    $('#registration').on("submit", function () {
        event.preventDefault();

        $('#map').empty();
        $('input').each(function () {
            $(this).css("color", "green");
        });

        const messageField = $('#message').empty();
        const passwordField = $('#password');
        const password = passwordField.val();
        const confirmField = $('#confirm');
        const confirm = confirmField.val();

        if (password === confirm) {
            ajaxRegistration({
                name: $('#name').val(),
                surname: $('#surname').val(),
                patronymic: $('#patronymic').val(),
                address: $('#address').val(),
                phone: $('#phone').val(),
                login: $('#login').val(),
                password: password
            }, function (response) {
                const errorMessage = response.message;
                const errorMap = response.errors;

                messageField.append(errorMessage);
                const mapField = $('#map').empty();
                Object.keys(errorMap).forEach(function (key) {
                    const inputField = $('#' + key);
                    const message = errorMap[key];

                    inputField.css("color", "red");
                    mapField.append('<p>').append(inputField.attr("placeholder")).append(': ').append(message).append('</p>');
                });
            });
        } else {
            messageField.append(PASSWORD_DO_NOT_MATCH);
            passwordField.css("color", "red");
            confirmField.css("color", "red");
        }
    })
});