$(document).ready(function () {
    findAllPartner();

});

//============ Get All Partner ========================
function findAllPartner() {

    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/partner/all",
        success: function (partners) {
            $("#column-partner").html(
                "<td style='text-align: center'> STT</td>" +
                "<td> Tên đối tác </td>" +
                "<td> Chức Năng</td>"
            );
            const listSize = Object.keys(partners).length;
            if (listSize > 0) {
                $('#total-record').text(listSize);
                let contentRow = '';
                var index = 1;
                partners.map(function (partner) {
                    contentRow += `
                        <tr>
                        <td> ${index} </td>
                        <td> ${partner.name} </td>
                        <td > 
                              <div class="btn-group">
                                   <a class="btn btn-primary" href="create-partner"><i class="fa fa-lg fa-plus"></i></a>
                                   <a class="btn btn-primary" href="update-partner?id=${partner.id}" name="${partner.id}"><i class="fa fa-lg fa-edit"></i></a>
                                   <a class="btn btn-primary partner" name="${partner.id}" ><i class="fa fa-lg fa-trash" style="color: white"></i></a>
                              </div>
                        </td>
                        </tr>
                    `;
                    index++;
                });
                $("#row-partner").html(contentRow);
                //============ delete =============
                deletePartner();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    })
}

//============ Delete Partner ========================
function deletePartner() {

    $('.delete-partner').click(function () {
        const id = $(this).attr("name");
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/partner/delete?id=" + id,
            timeout: 30000,
            success: function () {
                alert('Xóa thành công');
                location.href = "partner";
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Xóa thất bại");
                errMess(jqXHR, textStatus, errorThrown);
            }
        });
    });

}