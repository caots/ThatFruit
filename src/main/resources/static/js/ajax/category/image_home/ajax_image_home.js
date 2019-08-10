$(document).ready(function () {
    findAllImagePage();
});

//============ Get All Image Page ========================
function findAllImagePage() {
    $.ajax({
        type: "GET",
        headers: {
            'adminbksoftwarevn': value_token_public,
        },
        url: "api/v1/public/image-page/all",
        success: function (imagePages) {

            $("#column-image-home").html(
                "<td> STT</td>" +
                "<td> Tên</td>" +
                "<td> Url</td>" +
                "<td> Chức năng</td>"
            );
            const listSize = Object.keys(imagePages).length;
            if (listSize > 0) {
                $('#total-record').text(listSize);
                let contentRow = '';
                var index = 1;
                imagePages.map(function (imagePage) {

                    contentRow += `
                        <tr>
                        <td style="width: 10%"> ${index} </td>
                        <td style="width: 30%">  ${imagePage.name} </td>
                        <td style="width: 30%">  ${imagePage.url} </td>
                        <td style="width: 30%">
                            <div class="btn-group">
                                   <a class="btn btn-primary" href="update-image-page?id=${imagePage.id}" name="${imagePage.id}"><i class="fa fa-lg fa-edit"></i></a>
                            </div>
                        </td>
                        </tr>
                    `;
                    index++;
                });
                $("#row-image-home").html(contentRow);
                //============ delete =============
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

