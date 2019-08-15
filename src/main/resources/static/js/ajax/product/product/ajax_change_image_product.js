$(document).ready(function () {
    clickBtnImageProductChangeSubmit();
});

function clickBtnImageProductChangeSubmit() {
    const urlImagePage = window.location.href;
    var str = urlImagePage.split("=");
    const id = str[str.length - 1];
    if ((id - 1) >= 0) {
        findProductImageById(id);
    }
}

function findProductImageById(id) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/product/find-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            $('#url-image-product1').attr('src', result.imageOne);
            $('#url-image-product2').attr('src', result.imageTwo);
            $('#url-image-product3').attr('src', result.imageThree);
            updateImageProduct(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function updateImageProduct(product) {


    $("#change-product1").change(function () {
        var formDataOne = new FormData($('#btn-img-request1')[0]);
        uploadFile(formDataOne).then(function (data) {
            product.imageOne = data.data.display_url;
            updateProduct(product);
            $('#url-image-product1').attr('src', product.imageOne);
        });
    });

    $("#change-product2").change(function () {
        var formDataTwo = new FormData($('#btn-img-request2')[0]);
        uploadFile(formDataTwo).then(function (data) {
            product.imageTwo = data.data.display_url;
            updateProduct(product);
            $('#url-image-product2').attr('src', product.imageOne);
        });
    });

    $("#change-product3").change(function () {
        var formDataThree = new FormData($('#btn-img-request3')[0]);
        uploadFile(formDataThree).then(function (data) {
            product.imageThree = data.data.display_url;
            updateProduct(product);
            $('#url-image-product3').attr('src', product.imageOne);
        });
    });

/*    $('#btn-ok-image-product').click(function () {
        alert('Chỉnh sửa thành công');
        $(this).prop("disabled", true);
    });*/
}

function updateProduct(product) {
    $.ajax({
        type: "PUT",
        contentType: "application/json",
        headers: {
            "Authorization": tokenHeader_value,
        },
        url: "api/v1/admin/product/image",
        data: JSON.stringify(product),
        timeout: 30000,
        success: function () {

        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
            alert("Chỉnh sửa thất bại ");
        }
    });
}
