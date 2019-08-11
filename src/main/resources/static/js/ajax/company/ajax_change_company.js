$(document).ready(function () {
    clickBtnCompanyChangeSubmit();
});

function createCompany() {

    $('#btn-ok-company').click(function () {
        var name = $('#name-company').val();
        var phone = $('#phone-company').val();
        var email = $('#email-company').val();
        var address = $('#address-company').val();
        var description = $('#description-company').val();
        const company = {
            "name": name,
            "phone": phone,
            "email": email,
            "address": address,
            "description": description,
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/company",
            data: JSON.stringify(company),
            cache: false,
            timeout: 300000,
            success: function () {
                alert("Thêm thành công");
                $("#btn-ok-company").prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("Thêm thất bại ");
            }
        })
    });
}

//============ Find Big Category By Id ===================

function findCompanyById(id) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/company/find-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            updateCompany(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function updateCompany(data) {

    $('#name-company').val(data.name);
    $('#phone-company').val(0 + '' + data.phone);
    $('#email-company').val(data.email);
    $('#address-company').val(data.address);
    $('#description-company').val(data.description);
    $('#btn-ok-company').click(function () {
        data.name = $('#name-company').val();
        data.phone = $('#phone-company').val();
        data.email = $('#email-company').val();
        data.address = $('#address-company').val();
        data.description = $('#description-company').val();
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/company",
            data: JSON.stringify(data),
            timeout: 30000,
            success: function () {
                alert('Chỉnh sửa thành công');
                $("#btn-ok-company").prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("Chỉnh sửa thất bại ");
            }
        });
    });
}

function clickBtnCompanyChangeSubmit() {
    const urlCreateCompany = window.location.href;
    console.log(urlCreateCompany);
    var str = urlCreateCompany.split("=");
    const id = str[str.length - 1];
    if ((id - 1) >= 0) {
        findCompanyById(id)
    } else {
        createCompany();
    }

}