/**
 * @param name {string}
 * @param id {number}
 * @returns {string}
 */
function genreComponent(name, id) {
    return "<a href=\"/genre/" + id + "\" class=\"badge badge-dark\">" + name + "</a>\n";
}

/**
 * @param type {object}
 * @returns {string}
 */
function typeComponent(type) {
    return "<a class=\"dropdown-item\" href=\"#\"><span class=\"type-name\">" + type.type + "</span>\n" +
        "<small class=\"badge badge-dark\"><b>$</b> <span class=\"type-price\">" + (type.mediaPrice / 100) + "</span></small>\n" +
        "</a>\n";
}

/**
 * @param composition {object}
 * @returns {string}
 */
function compositionComponent(composition) {
    return "<tr>\n" +
        "<th scope=\"row\">" + composition.position + "</th>\n" +
        "<td>" + composition.name + "</td>\n" +
        "<td>" + composition.duration + "</td>\n" +
        "</tr>\n"
}

/**
 * @param album {object}
 */
function renderAlbum(album) {
    $('title').text(album.name);
    $('#title-album').text(album.name);
    $('#title-singer').text(album.singer.name).attr('href', ('/singer/' + album.singer.id));
    $('#title-label').text(album.label.name).attr('href', ('/label/' + album.label.id));
    $('#album-img').attr('src', album.picture.uri);

    const genreBox = $('#genre-box').empty();
    album.genres.forEach(function (item, i, arr) {
        genreBox.append(genreComponent(item.name, item.id));
    });

    const compositionBox = $('#composition-box').empty();
    album.compositions.forEach(function (item, i, arr) {
        compositionBox.append(compositionComponent(item));
    });

    const typeBox = $('#type-box').empty();
    album.priceList.forEach(function (item, i, arr) {
        typeBox.append(typeComponent(item));
    });

    $(document).on('click', '#type-box .dropdown-item', function () {
        event.preventDefault();
        const typeName = ($(this).find('.type-name').text());
        const typePrice = ($(this).find('.type-price').text());
        $('#type-dropdown').text(typeName);
        $('#price').text(typePrice);
    });

    $('#type-box .dropdown-item:first-child').trigger('click');
}

/**
 * @param id {number}
 */
function loadAlbum(id) {
    $.ajax({
        type: 'GET',
        url: '/api/album/' + id,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            console.log('Accepted Data:', data);
            renderAlbum(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('Accepted Error Data:', jqXHR.responseJSON);
        }
    });
}

$(function () {
    setAuth();

    const container = $('.product-container').hide();
    const arr = $(location).attr('pathname').split('/');
    loadAlbum(arr[arr.length - 1]);
    container.show();
});