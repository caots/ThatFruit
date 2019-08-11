$(document).ready(function () {
    findAllMenuCategory();
});

//============ Get All Menu ========================
function findAllMenuCategory() {
    $.ajax({
        type: "GET",
        headers: {
            'adminbksoftwarevn': value_token_public,
        },
        url: "api/v1/public/menu/all",
        success: function (menus) {

            console.log(menus);
            $("#column-menu").html(
                "<td> STT</td>" +
                "<td> Tên</td>" +
                "<td> Chức năng</td>"
            );
            const listSize = Object.keys(menus).length;
            if (listSize > 0) {
                $('#total-record').text(listSize);
                let contentRow = '';
                var index = 1;
                menus.map(function (menu) {

                    contentRow += `
                        <tr>
                        <td style="width: 20%"> ${index} </td>
                        <td style="width: 50%">  ${menu.name} </td>
                        <td style="width: 30%">
                            <div class="btn-group">
                                   <a class="btn btn-primary" href="update-category?menu-id=${menu.id}" name="${menu.id}"><i class="fa fa-lg fa-edit"></i></a>
                            </div>
                        </td>
                        </tr>
                    `;
                    index++;
                });
                $("#row-menu").html(contentRow);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}
