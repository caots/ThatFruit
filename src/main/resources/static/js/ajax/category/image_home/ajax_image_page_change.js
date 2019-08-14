$(document).ready(function () {
    clickBtnImagePageChangeSubmit();

});

function clickBtnImagePageChangeSubmit() {
    const urlImagePage = window.location.href;
    var str = urlImagePage.split("=");
    const id = str[str.length - 1];
    if ((id - 1) >= 0) {
        findImagePageById(id);
    }
}

function findImagePageById(id) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/image-page/find-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            updateImagePage(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function updateImagePage(imagePage) {

    $('#url-image-page').attr('src', imagePage.url);

    var formData;
    $("#change-product").change(function () {
        formData = new FormData($("form")[0]);
    });


    $('#btn-ok-image-page').click(function () {
            uploadFile(formData).then(function (data) {
                imagePage.url = data.data.display_url;
                $.ajax({
                    type: "PUT",
                    contentType: "application/json",
                    headers: {
                        "Authorization": tokenHeader_value,
                    },
                    url: "api/v1/admin/home-image",
                    data: JSON.stringify(imagePage),
                    cache: false,
                    timeout: 300000,
                    success: function (data) {
                        alert('Chỉnh ảnh thành công');
                        $('#url-image-page').attr('src', imagePage.url);
                        $('#btn-ok-image-page').prop("disabled", true);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        errMess(jqXHR, textStatus, errorThrown);
                    }
                })
            });
        }
    );

}