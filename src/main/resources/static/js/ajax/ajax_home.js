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
        url: "api/v1/admin/record/all",
        success: function (records) {

            const listSize = Object.keys(records).length;
            if (listSize > 0) {
                records.map(function (record) {
                    if (record.name === 'user') {
                        $('#user-number').text(record.number);
                    }
                    if (record.name === 'news') {
                        $('#news-number').text(record.number);
                    }
                    if (record.name === 'product') {
                        $('#product-number').text(record.number);
                    }
                    if (record.name === 'small-category') {
                        $('#small-number').text(record.number);
                    }
                    if(record.name ==='product'){
                        $('#total-record-product').text(record.number);
                    }
                    if(record.name ==='user'){
                        $('#total-record-user').text(record.number);
                    }

                    if(record.name ==='buy-form'){
                        $('#total-record-buy-form').text(record.number);
                    }

                    if(record.name ==='news'){
                        $('#total-record-news').text(record.number);
                    }
                    if(record.name ==='small-category'){
                        $('#total-record-small-category').text(record.number);
                    }
                    if(record.name ==='contact-form'){
                        $('#total-record-contact-form').text(record.number);
                    }
                });
            }


        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    })
}
