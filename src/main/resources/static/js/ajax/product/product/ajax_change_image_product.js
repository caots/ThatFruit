$(document).ready(function () {

    clickBtnImageProductChangeSubmit();

});

var idProduct = sessionStorage.getItem("product-id");

var uploadFile = async (file) => {
    let data;
    await $.ajax({
        type: "POST",
        headers: {
            "Authorization": tokenHeader_value,
        },
        url: "/api/v1/public/upload-file",
        enctype: 'multipart/form-data',
        data: file,
        cache: false,
        processData: false,
        contentType: false,
        success: function (result) {
            data = result
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
    return data;
};


function clickBtnImageProductChangeSubmit() {
    const urlImagePage = window.location.href;
    var str = urlImagePage.split("=");
    const id = str[str.length - 1];
    if ((id - 1) >= 0) {
        findImageProductById(id);
    } else createImageProduct();
}

function createImageProduct() {
    $('#btn-ok-image-product').click(function () {

            var formImg = $('#btn-img-product')[0];
            var formData = new FormData(formImg);

            uploadFile(formData).then(function (data) {
                var imageProduct = {
                    "url": data
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

    $('#btn-ok-image-product').click(function () {

            var formImg = $('#btn-img-product')[0];
            var formData = new FormData(formImg);

            uploadFile(formData).then(function (data) {
                result.url = data;
                $.ajax({
                    type: "POST",
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