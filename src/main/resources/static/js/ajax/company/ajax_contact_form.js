$(document).ready(function () {
    findAllContactForm(1);
    findAllPageContactFormNumber();

});

//==================================page=============================.unbind('click')
function pageContactFormCategory(size) {
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

function findAllPageContactFormNumber() {
    $.ajax({
        type: "GET",
        headers: {
            "Authorization": tokenHeader_value,
        },
        url: "api/v1/admin/contact-form/size",
        success: function (size) {

            pageContactFormCategory(size);

            $('.page').click(function () {
                const page = $(this).attr("name");
                for (let i = 1; i <= size; i++) {
                    var id = "#_" + i;
                    $(id).removeClass("active");
                }
                $(this).addClass("active");
                findAllContactForm(page);
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function findAllContactForm(page) {
    //============ Get All Big Category ========================
    $.ajax({
        type: "GET",
        headers: {
            "Authorization": tokenHeader_value,
        },
        url: "api/v1/admin/contact-form/page?page=" + page,
        success: function (contactForms) {
            $("#column-form-contact").html(
                "<td style='text-align: center'> STT</td>" +
                "<td> Tên</td>" +
                "<td> Email </td>" +
                "<td> Số điện thoại</td>" +
                "<td> Nội dung</td>" +
                "<td> Check</td>" +
                "<td> Chức năng</td>"
            );
            const listSize = Object.keys(contactForms).length;
            if (listSize > 0) {
                $('#total-record').text(listSize);
                let contentRow = '';
                var index = 1;


                contactForms.map(function (contactForm) {
                    var checked = contactForm.checked;
                    if (checked === true) {
                        checked = 'Đã Check';
                    } else {
                        checked = 'Chưa check';
                    }
                    contentRow += `
                        <tr>
                        <td> ${index} </td>
                        <td> ${contactForm.fullName} </td>
                        <td> ${contactForm.email} </td>
                        <td> 0${contactForm.phone} </td>
                        <td> 
                             <a href="content-form-contact?id=${contactForm.id}" style="cursor: pointer;color: green">Nội dung </a>&nbsp;<br> </td>
                        </td>
                        <td> ${checked} </td>
                        <td> 
                              <div class="btn-group">
                                   <a class="btn btn-primary" href="form-send-email?id=${contactForm.id}" name="${contactForm.id}"><i class="fa fa-lg fa-edit"></i></a>
                                   <a class="btn btn-primary delete-form" name="${contactForm.id}" ><i class="fa fa-lg fa-trash" style="color: white"></i></a>
                              </div>
                        </td>
                        </tr>
                    `;
                    index++;
                });
                $("#row-form-contact").html(contentRow);
                //============ delete =============
                deleteContactForm();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    })
}

//============ Delete Small Category ========================
function deleteContactForm() {

    $('.delete-form').click(function () {
        const id = $(this).attr("name");
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/contact-form/delete?id=" + id,
            timeout: 30000,
            success: function () {
                alert('Xóa thành công');
                location.href = "form-contact";
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Xóa thất bại");
                errMess(jqXHR, textStatus, errorThrown);
            }
        });
    });

}