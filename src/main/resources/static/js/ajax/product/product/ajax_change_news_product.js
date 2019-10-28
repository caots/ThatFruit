$(document).ready(function () {
    clickBtnProductNewsChangeSubmit();
});

function clickBtnProductNewsChangeSubmit() {
    const urlChangeProduct = window.location.href;
    var str = urlChangeProduct.split("=");
    const id = str[str.length - 1];
    if ((id - 1) >= 0) {
        findProductNewsById(id)
    }
}

//============ Find Product By Id ===================

function findProductNewsById(id) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/product/find-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            updateProductNews(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

// ============ update  Product  News========================
function updateProductNews(product) {

    $('.nicEdit-main').text(product.productInfo);

    $('#btn-ok-news-product').click(function () {
        product.productInfo = $('.nicEdit-main').text();
        console.log(product);
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/product/news",
            data: JSON.stringify(product),
            timeout: 30000,
            success: function () {
                alert('Chỉnh sửa thành công');
                $("#btn-ok-news-product").prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("Chỉnh sửa thất bại ");
            }
        });
    });
}