$(document).ready(function () {
    findAllCompanyContact();

});

//============ Get All Company Contact========================
function findAllCompanyContact() {

    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/company/contact/all",
        success: function (contacts) {
            $("#column-contact").html(
                "<td style='text-align: center'> STT</td>" +
                "<td>Tên công ty </td>" +
                "<td> Facebook </td>" +
                "<td> Zalo </td>" +
                "<td> Instagram </td>" +
                "<td> Youtube </td>" +
                "<td> Chức Năng</td>"
            );
            const listSize = Object.keys(contacts).length;
            if (listSize > 0) {
                $('#total-record').text(listSize);
                let contentRow = '';
                var index = 1;
                contacts.map(function (contact) {
                    contentRow += `
                        <tr>
                        <td width="10%"> ${index} </td>
                        <td width="10%"> ${contact.company.name} </td>
                        <td width="10%"> ${contact.facebook} </td>
                        <td width="10%"> ${contact.zalo} </td>
                        <td width="10%"> ${contact.instagram} </td>
                        <td width="10%"> ${contact.youtube} </td>
                        <td width="15%"> 
                              <div class="btn-group">
                                   <a class="btn btn-primary" href="create-contact"><i class="fa fa-lg fa-plus"></i></a>
                                   <a class="btn btn-primary" href="update-contact?id=${contact.id}" name="${contact.id}"><i class="fa fa-lg fa-edit"></i></a>
                                   <a class="btn btn-primary delete-contact" name="${contact.id}" ><i class="fa fa-lg fa-trash" style="color: white"></i></a>
                              </div>
                        </td>
                        </tr>
                    `;
                    index++;
                });
                $("#row-contact").html(contentRow);
                $(".body-main .table-responsive tr td").css({
                    "max-width": "260px",
                    "overflow": "-webkit-paged-y"
                });
                //============ delete =============
                deleteContact();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    })
}

//============ Delete Company ========================
function deleteContact() {

    $('.delete-contact').click(function () {
        const id = $(this).attr("name");
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/company/contact/delete?id=" + id,
            timeout: 30000,
            success: function () {
                alert('Xóa thành công');
                location.href = "company";
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Xóa thất bại");
                errMess(jqXHR, textStatus, errorThrown);
            }
        });
    });

}