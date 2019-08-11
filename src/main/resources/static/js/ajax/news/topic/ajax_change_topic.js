$(document).ready(function () {
    clickBtnTopicChangeSubmit();
});

//============ CREATE TOPIC ========================

function createTopic() {
    $('#btn-ok-topic').click(function () {

        const nameTopic = $('#name-topic').val();
        const topic = {
            "name": nameTopic
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/topic",
            data: JSON.stringify(topic),
            cache: false,
            timeout: 300000,
            success: function () {
                alert("Thêm thành công ");
                $('#btn-ok-topic').prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("Thêm thất bại");
            }
        })
    });
}

// ============ FIND TOPIC BY ID ===================

function findTopicById(id) {

    $.ajax({
        type: "GET",
        dataType: "json",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/topic/find-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            updateTopic(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

//============ UPDATE TOPIC ========================
function updateTopic(data) {
    $('#name-topic').val(data.name);
    $('#btn-ok-topic').click(function () {
        data.name = $('#name-topic').val();
        console.log(data);
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/topic",
            data: JSON.stringify(data),
            timeout: 30000,
            success: function () {
                alert('Chỉnh sửa thành công');
                $('#btn-ok-topic').prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("Chỉnh sửa thất bại");

            }
        });
    });
}

function clickBtnTopicChangeSubmit() {
    const urlCreateTopic = window.location.href;
    var str = urlCreateTopic.split("=");
    const id = str[str.length - 1];
    console.log(id);
    if ((id - 1) >= 0) {
        findTopicById(id)
    } else createTopic();

}