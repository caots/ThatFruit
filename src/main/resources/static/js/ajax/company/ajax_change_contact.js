$(document).ready(function () {
    findAllNameCompany();
    clickBtnContactCompanyChangeSubmit();
});

function createContactCompany() {

    let idCompany = '';
    $('#company-value').click(function () {
        idCompany = $(this).val();
    });
    $('#btn-ok-contact').click(function () {
        var facebook = $('#facebook-company').val();
        var zalo = $('#zalo-company').val();
        var instagram = $('#instagram-company').val();
        var youtube = $('#youtube-company').val();
        var map = $('#map-company').val();
        const contactCompany = {
            "facebook": facebook,
            "zalo": zalo,
            "instagram": instagram,
            "youtube": youtube,
            "map": map
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/company/contact?company-id=" + idCompany,
            data: JSON.stringify(contactCompany),
            cache: false,
            timeout: 300000,
            success: function () {
                alert("Thêm thành công");
                $("#btn-ok-contact").prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("Thêm thất bại ");
            }
        })
    });
}


function findContactCompanyById(id) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/company/contact/find-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            updateContactCompany(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function findAllNameCompany() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/company",
        timeout: 30000,
        success: function (result) {
            let contentRow = '';
            result.map(function (company) {
                contentRow += `
                         <option value="none"></option>
                       <option value="${company.id}">${company.name}</option>
                    `;
            });
            $("#company-value").html(contentRow);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function updateContactCompany(data) {
    $("#company-value").prop("disabled", true);
    $('#facebook-company').val(data.facebook);
    $('#zalo-company').val(data.zalo);
    $('#instagram-company').val(data.instagram);
    $('#youtube-company').val(data.youtube);
    $('#map-company').val(data.map);
    $('#btn-ok-contact').click(function () {
        data.facebook = $('#facebook-company').val();
        data.zalo = $('#zalo-company').val();
        data.instagram = $('#instagram-company').val();
        data.youtube = $('#youtube-company').val();
        data.map = $('#map-company').val();
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/company/contact",
            data: JSON.stringify(data),
            timeout: 30000,
            success: function () {
                alert('Chỉnh sửa thành công');
                $("#btn-ok-contact").prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("Chỉnh sửa thất bại ");
            }
        });
    });
}

function clickBtnContactCompanyChangeSubmit() {
    const urlCreateCompany = window.location.href;
    console.log(urlCreateCompany);
    var str = urlCreateCompany.split("=");
    const id = str[str.length - 1];
    if ((id - 1) >= 0) {
        findContactCompanyById(id)
    } else {
        createContactCompany();
    }

}