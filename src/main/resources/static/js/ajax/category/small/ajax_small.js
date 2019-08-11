$(document).ready(function () {
    findAllSmallCategory(1);
    findAllPageSmallNumber();

});

//==================================page=============================.unbind('click')
function pageSmallCategory(size) {
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

function findAllPageSmallNumber() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/small-category/size",
        success: function (size) {

            pageSmallCategory(size);

            $('.page').click(function () {
                const page = $(this).attr("name");
                for (let i = 1; i <= size; i++) {
                    var id = "#_" + i;
                    $(id).removeClass("active");
                }
                $(this).addClass("active");
                findAllSmallCategory(page);
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function findAllSmallCategory(page) {
    //============ Get All Big Category ========================
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/small-category/page?page=" + page,
        success: function (smallCategories) {
            $("#column-small").html(
                "<td style='text-align: center'> STT</td>" +
                "<td> Tên Loại sản phẩm </td>" +
                "<td> Danh mục Sản Phẩm</td>" +
                "<td> Chức Năng</td>"
            );
            const listSize = Object.keys(smallCategories).length;
            if (listSize > 0) {
                $('#total-record').text(listSize);
                let contentRow = '';
                var index = 1;
                smallCategories.map(function (smallCategory) {
                    contentRow += `
                        <tr>
                        <td style='text-align: center' width="15%"> ${index} </td>
                        <td  width="30%"> ${smallCategory.name} </td>
                        <td  width="30%" > ${smallCategory.bigCategory.name} </td>
                        <td  width="25%"> 
                              <div class="btn-group">
                                   <a class="btn btn-primary" href="create-small"><i class="fa fa-lg fa-plus"></i></a>
                                   <a class="btn btn-primary" href="update-category?small-id=${smallCategory.id}" name="${smallCategory.id}"><i class="fa fa-lg fa-edit"></i></a>
                                   <a class="btn btn-primary delete-small" name="${smallCategory.id}" ><i class="fa fa-lg fa-trash" style="color: white"></i></a>
                              </div>
                        </td>
                        </tr>
                    `;
                    index++;
                });
                $("#row-small").html(contentRow);
                //============ delete =============
                deleteSmallCategory();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    })
}

//============ Delete Small Category ========================
function deleteSmallCategory() {

    $('.delete-small').click(function () {
        const id = $(this).attr("name");
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/category/delete/small?id=" + id,
            timeout: 30000,
            success: function () {
                alert('Xóa thành công');
                location.href = "small-category";
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Xóa thất bại");
                errMess(jqXHR, textStatus, errorThrown);
            }
        });
    });

}