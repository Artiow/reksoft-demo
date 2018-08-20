const DEFAULT_PAGE_SIZE = 4;

/**
 * @param index {number}
 * @param successEvent {function(object)}
 * @param errorHandler {function(object)}
 */
function loadPage(index, successEvent, errorHandler) {
    $.ajax({
        type: 'POST',
        url: '/api/media/list/byFilter',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({pageSize: DEFAULT_PAGE_SIZE, pageNum: index}),
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            console.log('Accepted Data:', data);
            successEvent(data)
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errorHandler(jqXHR.responseJSON);
        }
    });
}

$(function () {
    loadPage(0, function (response) {
        const showcase = $('#showcase').empty();
        response.content.forEach(function (item, i, arr) {
            showcase.append(
                "<li class=\"card\">\n" +
                "<img class=\"card-img-top\" src=\"" + item.pictureURI + "\" alt=\"" + item.album + "\">\n" +
                "<div class=\"card-body\">\n" +
                "<h5 class=\"card-title\">" + item.album + "</h5>\n" +
                "<h6 class=\"card-title text-muted\">" + item.singer + "</h6>" +
                "</div>\n" +
                "<div class=\"card-footer\">\n" +
                "<p class=\"card-text\"><b>$</b><span>" + (item.price / 100) + " (" + item.type + ")" + "</span></p>\n" +
                "<a href=\"\" class=\"btn btn-primary\">В корзину</a>\n" +
                "</div>\n" +
                "</li>\n"
            );
        });
    }, function (response) {

    });

    $('.page-link').on('click', function () {

    })
});