$(document).ready(function () {
    findAllBigCategory(1);
    findAllPageBigNumber();

});

//==================================page=============================.unbind('click')
function pageBigCategory(size) {
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

function findAllPageBigNumber() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/big-category/size",
        success: function (size) {

            pageBigCategory(size);

            $('.page').click(function () {
                const page = $(this).attr("name");
                for (let i = 1; i <= size; i++) {
                    var id = "#_" + i;
                    $(id).removeClass("active");
                }
                $(this).addClass("active");
                findAllBigCategory(page);
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function findAllBigCategory(page) {
    //============ Get All Big Category ========================
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/big-category/page?page=" + page,
        success: function (bigCategories) {
            $("#column-big").html(
                "<td style='text-align: center'> STT</td>" +
                "<td> Tên Danh mục sản phẩm </td> "+
                "<td> Chức Năng</td>"
            );
            const listSize = Object.keys(bigCategories).length;
            if (listSize > 0) {
                $('#total-record').text(listSize);
                let contentRow = '';
                var index = 1;
                bigCategories.map(function (bigCategory) {
                    contentRow += `
                        <tr>
                        <td style='text-align: center' width="15%"> ${index} </td>
                        <td  width="30%"> ${bigCategory.name} </td>
                        <td  width="25%"> 
                              <div class="btn-group">
                                   <a class="btn btn-primary" href="update-big?big-id=${bigCategory.id}" name="${bigCategory.id}"><i class="fa fa-lg fa-edit"></i></a>
                                   <a class="btn btn-primary delete-big" name="${bigCategory.id}" ><i class="fa fa-lg fa-trash" style="color: white"></i></a>
                              </div>
                        </td>
                        </tr>
                    `;
                    index++;
                });
                $("#row-big").html(contentRow);
                //============ delete =============
                deleteBigCategory();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    })
}

//============ Delete Big Category ========================
function deleteBigCategory() {

    $('.delete-big').click(function () {
        const id = $(this).attr("name");
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/category/big/delete?id=" + id,
            timeout: 30000,
            success: function () {
                alert('Xóa thành công');
                location.href = "big-category";
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Xóa thất bại");
                errMess(jqXHR, textStatus, errorThrown);
            }
        });
    });

}