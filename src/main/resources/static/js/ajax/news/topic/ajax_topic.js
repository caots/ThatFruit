$(document).ready(function () {
    findAllTopic();
});

//============ Get All Menu ========================
function findAllTopic() {
    $.ajax({
        type: "GET",
        headers: {
            'adminbksoftwarevn': value_token_public,
        },
        url: "api/v1/public/topic/all",
        success: function (topics) {

            console.log(topics);
            $("#column-topic").html(
                "<td> STT</td>" +
                "<td> Tên</td>" +
                "<td> Mô tả</td>" +
                "<td> Chức năng</td>"
            );
            const listSize = Object.keys(topics).length;
            if (listSize > 0) {
                let contentRow = '';
                var index = 1;
                topics.map(function (topic) {

                    contentRow += `
                        <tr>
                        <td> ${index} </td>
                        <td>  ${topic.name} </td>
                        <td>  ${topic.description} </td>
                        <td>
                            <div class="btn-group">
                                   <a class="btn btn-primary" href="create-topic"><i class="fa fa-lg fa-plus"></i></a>
                                   <a class="btn btn-primary" href="update-topic?topic-id=${topic.id}" name="${topic.id}"><i class="fa fa-lg fa-edit"></i></a>
                                   <a class="btn btn-primary delete-topic" name="${topic.id}" ><i class="fa fa-lg fa-trash" style="color: white"></i></a>
                            </div>
                        </td>
                        </tr>
                    `;
                    index++;
                });
                $("#row-topic").html(contentRow);
                //============ delete =============
                deleteTopic();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function deleteTopic() {

    $('.delete-topic').click(function () {
        const id = $(this).attr("name");
        console.log(tokenHeader_value);
        $.ajax({
            type: "PUT",
            headers: {
                "Authorization": tokenHeader_value,
            },
            contentType: "application/json",
            url: "api/v1/admin/topic/delete?id=" + id,
            timeout: 30000,
            success: function () {
                alert('Xóa thành công');
                location.href = "topic";
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
            }
        });
    });
}
