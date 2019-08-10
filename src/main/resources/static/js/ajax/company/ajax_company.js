$(document).ready(function () {
    findAllCompany();

});

//============ Get All Company ========================
function findAllCompany() {

    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/company",
        success: function (companies) {
            $("#column-company").html(
                "<td style='text-align: center'> STT</td>" +
                "<td> Tên Công ty </td>" +
                "<td> Số điện thoại </td>" +
                "<td> Email </td>" +
                "<td> Địa chỉ </td>" +
                "<td> Mô tả </td>" +
                "<td> Chức Năng</td>"
            );
            const listSize = Object.keys(companies).length;
            if (listSize > 0) {
                $('#total-record').text(listSize);
                let contentRow = '';
                var index = 1;
                companies.map(function (company) {
                    contentRow += `
                        <tr>
                        <td> ${index} </td>
                        <td> ${company.name} </td>
                        <td> 0${company.phone} </td>
                        <td> ${company.email} </td>
                        <td> ${company.address} </td>
                        <td> ${company.description} </td>
                        <td > 
                              <div class="btn-group">
                                   <a class="btn btn-primary" href="create-company"><i class="fa fa-lg fa-plus"></i></a>
                                   <a class="btn btn-primary" href="update-company?id=${company.id}" name="${company.id}"><i class="fa fa-lg fa-edit"></i></a>
                                   <a class="btn btn-primary delete-company" name="${company.id}" ><i class="fa fa-lg fa-trash" style="color: white"></i></a>
                              </div>
                        </td>
                        </tr>
                    `;
                    index++;
                });
                $("#row-company").html(contentRow);
                //============ delete =============
                deleteCompany();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    })
}

//============ Delete Company ========================
function deleteCompany() {

    $('.delete-company').click(function () {
        const id = $(this).attr("name");
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/company/delete?id=" + id,
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