$(document).ready(function () {
    findAllBuyForm(1);
    findAllPageBuyFormNumber();
});

function pageBuyFom(size) {
    let contentRow = '';
    for (let i = 1; i <= size; i++) {
        contentRow += `<li><a href="#" class="page" id="_${i}" name="${i}" >${i}</a></li>`;
    }
    $(".pagination").html(
        `<li><a href="#" class="prev" id="prev">&laquo</a></li>`
        + contentRow +
        `<li><a href="#" class="next" id="next">&raquo;</a></li>`
    );
    $("#_1").addClass("active");
}

function findAllPageBuyFormNumber() {
    $.ajax({
        type: "GET",
        headers: {
            'adminbksoftwarevn': value_token_public,
        },
        url: "api/v1/public/buy-form/size",
        success: function (size) {

            pageBuyFom(size);

            $('.page').click(function () {
                const page = $(this).attr("name");
                for (let i = 1; i <= size; i++) {
                    var id = "#_" + i;
                    $(id).removeClass("active");
                }
                $(this).addClass("active");
                findAllBuyForm(page);
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function findAllBuyForm(page) {
    $.ajax({
        type: "GET",
        headers: {
            'adminbksoftwarevn': value_token_public,
        },
        url: "api/v1/public/buy-form?page=" + page,
        success: function (buyforms) {
            $("#column-buyform").html(
                "<td>Kiểm tra</td>" +
                "<td>Tên</td>" +
                "<td>Số điện thoại</td>" +
                "<td>email</td>" +
                "<td>Địa chỉ</td>" +
                "<td>Ghi chú</td>" +
                "<td>Tên sản phẩm</td>" +
                "<td>Số lượng</td>" +
                "<td>Giá</td>" +
                "<td>Chức năng</td>"
            );
            const listSize = Object.keys(buyforms).length;
            if (listSize > 0) {
                let contentRow = '';
                var index = 1;
                buyforms.map(function (buyform) {

                    var price = formatNumber(buyform.price, '.', '.');
                    var phoneNumber = 0 + '' + buyform.phoneNumber;
                    console.log(buyform.checked);

                    var checked = buyform.checked;
                    if (checked === true) {
                        checked = 'Đã kiểm';
                    } else {
                        checked = 'Chưa kiểm';
                    }

                    var products = buyform.products + '';
                    var quantity = buyform.quantity + '';


                    contentRow += `
                            <tr>
                            <td style="color: red">${checked} </td>
                            <td style="text-align: left">${buyform.name} </td>
                            <td>${phoneNumber} </td>
                            <td>${buyform.email} </td>
                            <td style="text-align: left">${buyform.address} </td>
                            <td style="text-align: left">${buyform.note} </td>
                            <td style="text-align: left">${products.replaceAllll(',', '\n-  ')} </td>
                            <td style="text-align: center">${quantity.replaceAllll(',', ' - ')} </td>
                            <td>${price} </td>
                            <td>
                                  <a class="btn btn-primary" href="send-mail-product?buyform-id=${buyform.id}" name="${buyform.id}"><i class="fa fa-lg fa-edit" style="margin-right: 1px;"></i></a>
                                   <a class="btn btn-primary delete-buy-form" name="${buyform.id}" ><i class="fa fa-lg fa-trash" style="color: white"></i></a>
                            </td>
                            </tr>
                        `;
                    index++;
                });
                $("#row-buyform").html(contentRow);
                $(".body-main .table-responsive tr td").css({
                    "max-width": "260px",
                    "overflow": "-webkit-paged-y"
                });
                //============ delete =============
                deleteBuyForm();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    })
}

function deleteBuyForm() {

    $('.delete-buy-form').click(function () {
        const id = $(this).attr("name");
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/buy-form/delete?buy-form-id=" + id,
            timeout: 30000,
            success: function () {
                alert('Xóa thành công');
                location.href = "buy-form";
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Xóa thất bại");
                errMess(jqXHR, textStatus, errorThrown);
            }
        });
    });

}