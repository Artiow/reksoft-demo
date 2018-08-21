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

/**
 * @param item {object}
 */
function cardComponent(item) {
    return "<li class=\"card\">\n" +
        "<img class=\"card-img-top\" src=\"" + item.pictureURI + "\" alt=\"" + item.album + "\">\n" +
        "<div class=\"card-body\">\n" +
        "<h5 class=\"card-title\"><a href=\"\">" + item.album + "</a></h5>\n" +
        "<h6 class=\"card-title\"><a href=\"\">" + item.singer + "</a></h6>" +
        "</div>\n" +
        "<div class=\"card-footer\">\n" +
        "<p class=\"card-text\"><b>$</b><span>" + (item.price / 100) + " (" + item.type + ")" + "</span></p>\n" +
        "<a href=\"\" class=\"btn btn-primary\">В корзину</a>\n" +
        "</div>\n" +
        "</li>\n"
}

function showPage(index) {
    sessionStorage.setItem("currentPage", index);
    loadPage(index, function (response) {
        const showcase = $('#showcase').empty();
        const display = showcase.css("display");
        showcase.css("display", "none");
        response.content.forEach(function (item, i, arr) {
            showcase.append(cardComponent(item));
        });
        showcase.css("display", display);

        let pageNumber = response.pageNumber;
        let totalPages = response.totalPages;
        const pagination = $('#pagination').empty();

        if (index > 0) {
            pagination.append(
                "<li class=\"page-item\">\n" +
                "<a id=\"link-prev\" class=\"page-link\" href=\"\">Предыдущая</a>\n" +
                "</li>"
            );
        } else {
            pagination.append(
                "<li class=\"page-item disabled\">\n" +
                "<a id=\"link-prev\" class=\"page-link\" href=\"\" tabindex=\"-1\">Предыдущая</a>\n" +
                "</li>"
            );
        }
        for (let i = 0; i < totalPages; i++) {
            if (i !== index) {
                pagination.append("<li class=\"page-item\"><a id=\"link-" + i + "\" class=\"page-link\" href=\"\">" + (i + 1) + "</a></li>\n")
            } else {
                pagination.append("<li class=\"page-item\"><a id=\"link-" + i + "\" class=\"page-link\" href=\"\"><b>" + (i + 1) + "</b></a></li>\n")
            }
        }
        if (index < (totalPages - 1)) {
            pagination.append(
                "<li class=\"page-item\">\n" +
                "<a id=\"link-next\" class=\"page-link\" href=\"\">Следующая</a>\n" +
                "</li>"
            );
        } else {
            pagination.append(
                "<li class=\"page-item disabled\">\n" +
                "<a id=\"link-next\" class=\"page-link\" href=\"\" tabindex=\"-1\">Следующая</a>\n" +
                "</li>"
            );
        }
    }, function (response) {

    });
}

$(function () {
    showPage(0);
    $(document).on('click', '.page-link', function () {
        event.preventDefault();
        const tmpArr = $(this).attr('id').split('-');
        const index = tmpArr[tmpArr.length - 1];
        if (!isNaN(index)) {
            showPage(+index);
        } else {
            let currentPage = (+sessionStorage.getItem("currentPage"));
            showPage((index === "prev") ? (currentPage - 1) : (currentPage + 1));
        }
    })
});