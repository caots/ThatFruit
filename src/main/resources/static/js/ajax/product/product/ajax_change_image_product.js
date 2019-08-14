$(document).ready(function () {

    clickBtnImageProductChangeSubmit();

});

var idProduct = sessionStorage.getItem("product-id");


function clickBtnImageProductChangeSubmit() {
    const urlImagePage = window.location.href;
    var str = urlImagePage.split("=");
    const id = str[str.length - 1];
    if ((id - 1) >= 0) {
        findImageProductById(id);
    } else createImageProduct();
}

function createImageProduct() {

    var formData;
    $("#change-product").change(function () {
        formData = new FormData($("form")[0]);
    });


    $('#btn-ok-image-product').click(function () {

            uploadFile(formData).then(function (data) {
                var imageProduct = {
                    "url": data.data.display_url
                };

                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    headers: {
                        "Authorization": tokenHeader_value,
                    },
                    url: "api/v1/admin/product-image?product_id=" + idProduct,
                    data: JSON.stringify(imageProduct),
                    cache: false,
                    timeout: 300000,
                    success: function () {
                        alert('Chỉnh sửa thành công');
                        $('#url-image-product').attr('src', data.data.display_url);
                        $('#btn-ok-image-product').prop("disabled", true);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        errMess(jqXHR, textStatus, errorThrown);
                    }
                })
            });
        }
    );
}

function findImageProductById(id) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/product-image/find-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            updateImageProduct(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function updateImageProduct(result) {

    $('#url-image-product').attr('src', result.url);

    var formData;
    $("#change-product").change(function () {
        formData = new FormData($("form")[0]);
    });


    $('#btn-ok-image-product').click(function () {
            uploadFile(formData).then(function (data) {
                result.url = data.data.display_url;
                console.log(result);
                $.ajax({
                    type: "PUT",
                    contentType: "application/json",
                    headers: {
                        "Authorization": tokenHeader_value,
                    },
                    url: "api/v1/admin/product-image",
                    data: JSON.stringify(result),
                    cache: false,
                    timeout: 300000,
                    success: function () {
                        alert('Chỉnh ảnh thành công');
                        $('#url-image-product').attr('src', result.url);
                        $('#btn-ok-image-product').prop("disabled", true);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        errMess(jqXHR, textStatus, errorThrown);
                    }
                })
            });
        }
    );

}