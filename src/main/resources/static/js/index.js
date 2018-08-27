const DEFAULT_PAGE_SIZE = 4;

/**
 * @param filter {object}
 * @param successEvent {function(object)}
 * @param errorHandler {function(object)}
 */
function loadPage(filter, successEvent, errorHandler) {
    $.ajax({
        type: 'POST',
        url: '/api/media/list/byFilter',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(filter),
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            console.log('Accepted Data:', data);
            successEvent(data)
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('Accepted Error Data:', jqXHR.responseJSON);
            errorHandler(jqXHR.responseJSON);
        }
    });
}

const NOT_FOUND_COMPONENT =
    "<div class=\"col-md-12 text-center mt-md-5 mt-sm-0\">\n" +
    "<span class=\"display-4 d-block\">Ничего не найдено</span>\n" +
    "</div>\n";

const SHOWCASE_COMPONENT = "<ul id=\"showcase\" class=\"card-deck\"></ul>\n";

/**
 * @param item {object}
 */
function cardComponent(item) {
    return "<li class=\"card\">\n" +
        "<img class=\"card-img-top\" src=\"" + item.pictureURI + "\" alt=\"" + item.album + "\">\n" +
        "<div class=\"card-body\">\n" +
        "<h5 class=\"card-title\"><a href=\"#\">" + item.album + "</a></h5>\n" +
        "<h6 class=\"card-title\"><a href=\"#\">" + item.singer + "</a></h6>\n" +
        "<h6 class=\"card-title\"><a href=\"#\"><small>" + item.label + "</small></a></h6>\n" +
        "</div>\n" +
        "<div class=\"card-footer\">\n" +
        "<p class=\"card-text\"><b>$</b><span>" + (item.price / 100) + " <small>(" + item.type + ")</small>" + "</span></p>\n" +
        "<a href=\"\" class=\"btn btn-primary\">В корзину</a>\n" +
        "</div>\n" +
        "</li>\n"
}

/**
 * @param content {object}
 */
function showCards(content) {
    const container = $('#showcase-container').empty();
    if (content.length > 0) {
        container.append(SHOWCASE_COMPONENT);
        const showcase = $('#showcase').empty();
        content.forEach(function (item, i, arr) {
            showcase.append(cardComponent(item));
        });
    } else {
        container.append(NOT_FOUND_COMPONENT);
    }
}

const PREV_BUTTON_COMPONENT =
    "<li class=\"page-item page-item-prev\">\n" +
    "<a id=\"link-prev\" class=\"page-link\" href=\"\">Предыдущая</a>\n" +
    "</li>";

const NEXT_BUTTON_COMPONENT =
    "<li class=\"page-item page-item-next\">\n" +
    "<a id=\"link-next\" class=\"page-link\" href=\"\">Следующая</a>\n" +
    "</li>";

/**
 * @param index {number}
 * @param current {boolean}
 */
function paginationComponent(index, current) {
    const content = current ? '<b>' + (index + 1) + '</b>' : (index + 1);
    return "<li class=\"page-item\"><a id=\"link-" + index + "\" class=\"page-link\" href=\"\">" + content + "</a></li>\n"
}

/**
 * @param number {number}
 * @param total {number}
 */
function showPagination(number, total) {
    const pagination = $('#pagination').empty();
    if (total > 1) {
        pagination.append(PREV_BUTTON_COMPONENT);
        for (let i = 0; i < total; i++) {
            pagination.append(paginationComponent(i, (i === number)));
        }
        pagination.append(NEXT_BUTTON_COMPONENT);
        if (number === 0) {
            $('.page-item-prev').addClass('disabled');
            $('#link-prev').attr('tabindex', '-1');
        } else if (number === (total - 1)) {
            $('.page-item-next').addClass('disabled');
            $('#link-next').attr('tabindex', '-1');
        }
    }
}

/**
 * @param index {number}
 */
function showPage(index) {
    currentPage = index;

    const queryJson = {
        pageSize: DEFAULT_PAGE_SIZE,
        pageNum: index
    };

    if ((searchType !== null) && (searchString !== null)) {
        queryJson.searchType = searchType;
        queryJson.searchString = searchString;
    }

    if (mediaTypes.length > 0) {
        queryJson.typeCodes = mediaTypes;
    }

    if (genres.length > 0) {
        queryJson.genreCodes = genres;
    }

    loadPage(queryJson, function (response) {
        showCards(response.content);
        showPagination(response.pageNumber, response.totalPages);
    }, function (response) {

    });
}

/**
 * @param dictionaryName {string}
 * @param successEvent {function(object)}
 * @param errorHandler {function(object)}
 */
function loadDictionary(dictionaryName, successEvent, errorHandler) {
    $.ajax({
        type: 'POST',
        url: '/api/' + dictionaryName + '/list',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({pageNum: 0, pageSize: 0}),
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            console.log('Accepted Data:', data);
            successEvent(data)
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('Accepted Error Data:', jqXHR.responseJSON);
            errorHandler(jqXHR.responseJSON);
        }
    });
}

function liCheckboxComponent(value, text) {
    return "<li><label><input value=\"" + value + "\" type=\"checkbox\">" + text + "</label></li>";
}

function showFilter(container, content) {
    container.empty();
    content.forEach(function (item, i, arr) {
        container.append(liCheckboxComponent(item.code, item.name));
    });
}

function initFilter() {
    loadDictionary('mediatype', function (response) {
        showFilter($('#dropdown-media-type .dropdown-menu'), response.content)
    }, function () {
    });
    loadDictionary('genre', function (response) {
        showFilter($('#dropdown-genre .dropdown-menu'), response.content)
    }, function () {
    });
}

let currentPage = null,
    searchType = "byAlbum",
    searchString = null,
    mediaTypes = [],
    genres = [];

$(function () {
    initFilter();
    showPage(0);
    $(document).on('click', '.page-link', function () {
        event.preventDefault();
        const tmpArr = $(this).attr('id').split('-');
        const index = tmpArr[tmpArr.length - 1];
        if (!isNaN(index)) {
            showPage(+index);
        } else {
            showPage((index === "next") ? (currentPage + 1) : (currentPage - 1));
        }
    });
    $(document).on('click', '#dropdown-search-type .dropdown-item', function () {
        event.preventDefault();
        searchType = ($(this).attr('id'));
        $('#dropdown-search-type .dropdown-toggle').text($(this).text());
        $('#query').val('');
    });
    $(document).on('submit', '#search', function () {
        event.preventDefault();
        searchString = ($('#query').val());
        showPage(0);
    });
    $(document).on('change', '#dropdown-media-type .dropdown-menu input[type=checkbox]', function () {
        let val = ($(this).attr('value'));
        let idx = mediaTypes.indexOf(val);
        if ((this.checked) && (idx < 0)) {
            mediaTypes.push(val);
        } else {
            mediaTypes.splice(idx, 1);
        }
    });
    $(document).on('change', '#dropdown-genre .dropdown-menu input[type=checkbox]', function () {
        let val = ($(this).attr('value'));
        let idx = genres.indexOf(val);
        if ((this.checked) && (idx < 0)) {
            genres.push(val);
        } else {
            genres.splice(idx, 1);
        }
    });
    $(document).on('click', '.checkbox-dropdown .dropdown-menu', function (e) {
        e.stopPropagation();
    });
});