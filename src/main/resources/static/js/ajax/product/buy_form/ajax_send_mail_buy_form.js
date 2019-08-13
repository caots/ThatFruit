$(document).ready(function () {
    clickBtnSendMailBuyFormChangeSubmit();
});

function findBuyFormById(id) {

    $.ajax({
        type: "GET",
        dataType: "json",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/buy-form/find-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            $('#name-email-bf').val(result.email);
            sendEmailBuyFormContactForm();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function updateCheckBuyForm(buyFormId) {
    console.log(buyFormId);
    $.ajax({
        type: "PUT",
        dataType: "json",
        headers: {
            "Authorization": tokenHeader_value,
        },
        url: "api/v1/admin/buy-form/check?buy-form-id=" + buyFormId,
        timeout: 30000,
        success: function () {
            console.log("check thành công");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("check thất bại");
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function sendEmailBuyFormContactForm() {

    $("#btn-ok-email-bf").click(function () {

        var email = $("#name-email-bf").val();
        var title = $("#title-email-bf").val();
        var content = $("#content-email-bf").val();

        $.ajax({
            type: "GET",
            dataType: "json",
            headers: {
                "adminbksoftwarevn": value_token_public,
            },
            url: "api/v1/public/send-email?email=" + email + "&title=" + title + "&content=" + content,
            timeout: 30000,
            success: function () {
                alert("Gửi email thành công");
                $('#btn-ok-email-bf').prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Gửi email không thành công");
                errMess(jqXHR, textStatus, errorThrown);
            }
        });
    })
}
function clickBtnSendMailBuyFormChangeSubmit() {
    const urlBuyForm = window.location.href;
    var str = urlBuyForm.split("=");
    const id = str[str.length - 1];
    console.log(id);
    updateCheckBuyForm(id);
    if ((id - 1) >= 0) {
        findBuyFormById(id);
    }
}