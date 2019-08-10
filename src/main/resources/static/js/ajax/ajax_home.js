$(document).ready(function () {
    findAllRecord();

});

function findAllRecord() {
    //============ Get All Record ========================
    $.ajax({
        type: "GET",
        headers: {
            "Authorization": tokenHeader_value,
        },
        url: "api/v1/admin/record",
        success: function (records) {
            const listSize = Object.keys(records).length;
            if (listSize > 0) {
                records.map(function (record) {
                    if (record.name === '') {

                    }
                });
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    })
}
