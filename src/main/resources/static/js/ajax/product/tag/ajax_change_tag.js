$(document).ready(function () {
    clickBtnTagChangeSubmit();
});

//============ CREATE TAG ========================

function createTag() {
    $('#btn-ok-tag').click(function () {

        const nameTag = $('#name-tag').val();
        const tag = {
            "name": nameTag
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/tag",
            data: JSON.stringify(tag),
            cache: false,
            timeout: 300000,
            success: function () {
                alert("Thêm thành công ");
                $('#btn-ok-tag').prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("Thêm thất bại");
            }
        })
    });
}

// ============ FIND TAG BY ID ===================

function findTagById(id) {

    $.ajax({
        type: "GET",
        dataType: "json",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/tag/find-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            console.log(result);
            updateTag(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

//============ UPDATE TAG ========================
function updateTag(data) {
    $('#name-tag').val(data.name);
    $('#btn-ok-tag').click(function () {
        data.name = $('#name-tag').val();
        console.log(data);
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/tag",
            data: JSON.stringify(data),
            timeout: 30000,
            success: function () {
                alert('Chỉnh sửa thành công');
                $('#btn-ok-tag').prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("Chỉnh sửa thất bại");

            }
        });
    });
}

function clickBtnTagChangeSubmit() {
    const urlCreatePartner = window.location.href;
    var str = urlCreatePartner.split("=");
    const id = str[str.length - 1];
    console.log(id);
    if ((id - 1) >= 0) {
        findTagById(id)
    } else createTag();

}