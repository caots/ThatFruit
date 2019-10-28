$(document).ready(function () {
    clickBtnSmallChangeSubmit();
});

//============ Create Small Category ========================
function createSmallCategory() {

    let idBigCategory = '';
    $('#big-category-value').change(function () {
        idBigCategory = $(this).val();
    });
    $('#btn-ok-small').click(function () {
        const nameSmallCategory = $("#name-small-category").val();
        const smallCategory = {
            "name": nameSmallCategory,
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/category/small?big-id=" + idBigCategory,
            data: JSON.stringify(smallCategory),
            cache: false,
            timeout: 300000,
            success: function (data) {
                alert("Thêm thành công");
                $("#btn-ok-small").prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("Thêm thất bại ");
            }
        })
    });
}

//============ Find Small Category By Id ===================

function findSmallCategoryById(id) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/small-category/find-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            updateSmallCategory(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

// ============ update Small Category ========================
function updateSmallCategory(data) {

    $('#name-small-category').val(data.name);
    $("#big-category-value").prop("disabled", true);
    $('#btn-ok-small').click(function () {
        data.name = $('#name-small-category').val();
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/category/small",
            data: JSON.stringify(data),
            timeout: 30000,
            success: function () {
                alert('Chỉnh sửa thành công');
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("Chỉnh sửa thất bại ");
            }
        });
    });
}

function clickBtnSmallChangeSubmit() {
    const urlCreateCategory = window.location.href;
    if (urlCreateCategory.indexOf('small') >= 0) {
        $('.title-menu').css('color', 'gray');
        $('.title-big').css('color', 'gray');
        $("#btn-ok-menu").prop("disabled", true);
        $("#btn-ok-big").prop("disabled", true);
    }
    var str = urlCreateCategory.split("=");
    const id = str[str.length - 1];
    if ((id - 1) >= 0) {
        findSmallCategoryById(id)
    } else {
        createSmallCategory();
    }

}