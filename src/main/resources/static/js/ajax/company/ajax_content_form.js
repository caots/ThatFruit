$(document).ready(function () {
    clickBtnContentChangeSubmit();
});

function findContentFormById(id) {

    $.ajax({
        type: "GET",
        dataType: "json",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/contact-form/find-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            updateCheckForm(result);
            $('#title-form').val(result.title);
            $('#content-form').val(result.content);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}


function updateCheckForm(result) {
    result.checked = 'true';
    $.ajax({
        type: "PUT",
        contentType: "application/json",
        headers: {
            "Authorization": tokenHeader_value,
        },
        url: "api/v1/admin/contact-form",
        data: JSON.stringify(result),
        cache: false,
        timeout: 300000,
        success: function () {
            console.log('checked');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    })
}


function clickBtnContentChangeSubmit() {
    const urlContentForm = window.location.href;
    var str = urlContentForm.split("=");
    const id = str[str.length - 1];
    console.log(id);
    if ((id - 1) >= 0) {
        findContentFormById(id)
    }
}