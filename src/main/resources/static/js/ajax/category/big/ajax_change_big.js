$(document).ready(function () {
    clickBtnBigChangeSubmit();
});

//============ Create Big Category ========================
function createBigCategory() {

    let idMenu = '';
    $('#menu-value').change(function () {
        idMenu = $(this).val();
    });
    $('#btn-ok-big').click(function () {
        const nameBigCategory = $("#name-big-category").val();
        const bigCategory = {
            "name": nameBigCategory,
        };
        console.log(bigCategory);
        console.log(idMenu);
        $.ajax({
            type: "POST",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/category/big?menu-id=" + idMenu,
            data: JSON.stringify(bigCategory),
            cache: false,
            timeout: 300000,
            success: function () {
                alert("Thêm thành công");
                $("#btn-ok-big").prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("Thêm thất bại ");
            }
        })
    });
}

//============ Find Big Category By Id ===================

function findBigCategoryById(id) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/big-category/find-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            updateBigCategory(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

// ============ UPDATE Big Category ========================
function updateBigCategory(data) {

    $('#name-big-category').val(data.name);
    $("#menu-value").prop("disabled", true);
    $('#btn-ok-big').click(function () {
        data.name = $('#name-big-category').val();
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/category/big",
            data: JSON.stringify(data),
            timeout: 30000,
            success: function () {
                alert('Chỉnh sửa thành công');
                $("#btn-ok-big").prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("Chỉnh sửa thất bại ");
            }
        });
    });
}

function clickBtnBigChangeSubmit() {
    const urlCreateCategory = window.location.href;
    console.log(urlCreateCategory);
    if (urlCreateCategory.indexOf('big') >= 0) {
        $('.title-menu').css('color', 'gray');
        $('.title-small').css('color', 'gray');
        $("#btn-ok-menu").prop("disabled", true);
        $("#btn-ok-small").prop("disabled", true);
    }
    var str = urlCreateCategory.split("=");
    const id = str[str.length - 1];
    if ((id - 1) >= 0) {
        findBigCategoryById(id)
    } else {
        createBigCategory();
    }

}