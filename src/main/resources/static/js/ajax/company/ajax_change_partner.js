$(document).ready(function () {
    clickBtnPartnerChangeSubmit();
});


function createPartner() {
    $('#btn-ok-partner').click(function () {

        const namePartner = $('#name-partner').val();
        const partner = {
            "name": namePartner
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/partner",
            data: JSON.stringify(partner),
            cache: false,
            timeout: 300000,
            success: function () {
                alert("Thêm thành công ");
                $('#btn-ok-partner').prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("Thêm thất bại");
            }
        })
    });
}


function findPartnerById(id) {

    $.ajax({
        type: "GET",
        dataType: "json",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/partner/find-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            updatePartner(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function updatePartner(data) {
    $('#name-partner').val(data.name);
    $('#btn-ok-partner').click(function () {
        data.name = $('#name-partner').val();
        console.log(data);
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/partner",
            data: JSON.stringify(data),
            timeout: 30000,
            success: function () {
                alert('Chỉnh sửa thành công');
                $('#btn-ok-partner').prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("Chỉnh sửa thất bại");

            }
        });
    });
}

function clickBtnPartnerChangeSubmit() {
    const urlCreatePartner = window.location.href;
    var str = urlCreatePartner.split("=");
    const id = str[str.length - 1];
    console.log(id);
    if ((id - 1) >= 0) {
        findPartnerById(id)
    } else createPartner();

}