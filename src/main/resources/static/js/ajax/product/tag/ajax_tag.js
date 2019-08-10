$(document).ready(function () {
    findAllTag(1);
    findAllPageTagNumber();

});

//==================================page=============================.unbind('click')
function pageTag(size) {
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

function findAllPageTagNumber() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/tag/size",
        success: function (size) {

            pageTag(size);

            $('.page').click(function () {
                const page = $(this).attr("name");
                for (let i = 1; i <= size; i++) {
                    var id = "#_" + i;
                    $(id).removeClass("active");
                }
                $(this).addClass("active");
                findAllTag(page);
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function findAllTag(page) {
    //============ Get All Tag ========================
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/tag/page?page=" + page,
        success: function (tags) {
            $("#column-tag").html(
                "<td style='text-align: center'> STT</td>" +
                "<td> Tên tag </td>" +
                "<td> Chức Năng</td>"
            );
            const listSize = Object.keys(tags).length;
            if (listSize > 0) {
                $('#total-record').text(listSize);
                let contentRow = '';
                var index = 1;
                tags.map(function (tag) {
                    contentRow += `
                        <tr>
                        <td style='text-align: center' width="20%"> ${index} </td>
                        <td  width="50%"> ${tag.name} </td>
                        <td  width="30%"> 
                              <div class="btn-group">
                                   <a class="btn btn-primary" href="create-tag"><i class="fa fa-lg fa-plus"></i></a>
                                   <a class="btn btn-primary" href="update-tag?id=${tag.id}" name="${tag.id}"><i class="fa fa-lg fa-edit"></i></a>
                                   <a class="btn btn-primary delete-tag" name="${tag.id}" ><i class="fa fa-lg fa-trash" style="color: white"></i></a>
                              </div>
                        </td>
                        </tr>
                    `;
                    index++;
                });
                $("#row-tag").html(contentRow);
                //============ delete =============
                deleteTag();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    })
}

//============ Delete Tag ========================
function deleteTag() {

    $('.delete-tag').click(function () {
        const id = $(this).attr("name");
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/tag/delete?id=" + id,
            timeout: 30000,
            success: function () {
                alert('Xóa thành công');
                location.href = "tag";
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Xóa thất bại");
                errMess(jqXHR, textStatus, errorThrown);
            }
        });
    });

}