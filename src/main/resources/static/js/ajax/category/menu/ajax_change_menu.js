$(document).ready(function () {
    clickBtnMenuChangeSubmit();
});


// ============ Find Menu By Id ===================

function findMenuById(id) {
    $.ajax({
        type: "GET",
        dataType: "json",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/menu/find-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            updateMenuCategory(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

//============ update Menu ========================
function updateMenuCategory(data) {
    $('#name-menu').val(data.name);
    $('#btn-ok-menu').click(function () {
        data.name = $('#name-menu').val();
        console.log(data);
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: "api/v1/admin/category/menu",
            headers: {
                "Authorization": tokenHeader_value,
            },
            data: JSON.stringify(data),
            timeout: 30000,
            success: function () {
                alert('Chỉnh sửa thành công');
                $("#btn-ok-menu").prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("Chỉnh sửa thất bại ");
                $("#btn-ok-menu").prop("disabled", true);
            }
        });
    });
}

function clickBtnMenuChangeSubmit() {
    const urlCreateCategory = window.location.href;
    console.log(urlCreateCategory);
    if(urlCreateCategory.indexOf('menu')>=0){
        $('.title-big').css('color','gray');
        $('.title-small').css('color','gray');
        $("#btn-ok-big").prop("disabled", true);
        $("#btn-ok-small").prop("disabled", true);
    }
    var str = urlCreateCategory.split("=");
    const id = str[str.length - 1];
    if ((id - 1) >= 0) {
        findMenuById(id)
    }
}