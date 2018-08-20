const PASSWORD_DO_NOT_MATCH = 'Введенные пароли не совпадают!';

$(function () {
    $('#register-form').on("submit", function () {
        event.preventDefault();

        const messageField = $('#message-field')
            .css('display', 'none')
            .empty();

        $('.form-control').each(function () {
            $(this).removeClass("is-invalid");
            $(this).removeClass("is-valid");
        });
        $('.invalid-feedback').each(function () {
            $(this).empty();
        });

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
                messageField
                    .css('display', 'block')
                    .append(errorMessage);

                Object.keys(errorMap).forEach(function (key) {
                    const message = errorMap[key];
                    let errorInputId = '#' + key;
                    let errorInput = $(errorInputId).addClass("is-invalid");
                    if (key === 'password') {
                        errorInputId = '#confirm';
                        errorInput = $(errorInputId).addClass("is-invalid");
                    }
                    $(errorInputId + ' + .invalid-feedback').empty()
                        .append('Значение поля \"')
                        .append(errorInput.attr("placeholder").toLowerCase())
                        .append('\" ').append(message).append('!');
                });
            });
        } else {
            passwordField.addClass("is-invalid");
            confirmField.addClass("is-invalid");
            $('#confirm + .invalid-feedback')
                .append(PASSWORD_DO_NOT_MATCH);
        }
    })
});