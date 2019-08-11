$(document).ready(function () {
    findAllNews(1);
    findAllPageNewsNumber();

});

//==================================page=============================.unbind('click')
function pageNews(size) {
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

function findAllPageNewsNumber() {
    $.ajax({
        type: "GET",
        headers: {
            'adminbksoftwarevn': value_token_public,
        },
        url: "api/v1/public/news/size",
        success: function (size) {

            pageNews(size);

            $('.page').click(function () {
                const page = $(this).attr("name");
                for (let i = 1; i <= size; i++) {
                    var id = "#_" + i;
                    $(id).removeClass("active");
                }
                $(this).addClass("active");
                findAllNews(page);
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function findAllNews(page) {
    $.ajax({
        type: "GET",
        headers: {
            'adminbksoftwarevn': value_token_public,
        },
        url: "api/v1/public/news/page?page=" + page,
        success: function (news) {
            $("#column-news").html(
                "<td style='text-align: center'> STT</td>" +
                "<td> Tiêu đề </td>" +
                "<td> Nội dung</td>" +
                "<td> Lượt xem</td>" +
                "<td> Thời gian đăng</td>" +
                "<td> Chức năng</td>"
            );
            const listSize = Object.keys(news).length;
            if (listSize > 0) {
                $('#total-record').text(listSize);
                let contentRow = '';
                var index = 1;
                news.map(function (newss) {
                    contentRow += `
                        <tr>  
                        <td> ${index} </td>
                        <td style="text-align: left"> ${newss.title} </td>
                        <td>  <a href="update-news?news-id=${newss.id}"  style="cursor: pointer;color: green">Nội dung </a>&nbsp;<br> </td> </td>
                        <td> ${newss.view} </td>
                        <td> ${newss.time} </td>
                        <td> 
                              <div class="btn-group">
                                   <a class="btn btn-primary" href="create-news"><i class="fa fa-lg fa-plus"></i></a>
                                   <a class="btn btn-primary" href="update-news?news-id=${newss.id}" name="${newss.id}"><i class="fa fa-lg fa-edit"></i></a>
                                   <a class="btn btn-primary delete-news" name="${newss.id}" ><i class="fa fa-lg fa-trash" style="color: white"></i></a>
                              </div>
                        </td>
                        </tr>
                    `;
                    index++;
                    console.log(typeof (newss.time.prototype));
                });
                $("#row-news").html(contentRow);
                //============ delete =============
                deleteNews();

            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    })
}

//============ Delete News ========================
function deleteNews() {

    $('.delete-news').click(function () {
        const id = $(this).attr("name");
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/news/delete?id=" + id,
            timeout: 30000,
            success: function () {
                alert('Xóa thành công');
                location.href = "news";
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Xóa thất bại");
                errMess(jqXHR, textStatus, errorThrown);
            }
        });
    });

}