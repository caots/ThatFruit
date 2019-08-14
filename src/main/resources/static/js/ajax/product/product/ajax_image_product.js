$(document).ready(function () {
    clickBtnProductImageChangeSubmit();
});


function clickBtnProductImageChangeSubmit() {
    const urlChangeProduct = window.location.href;
    var str = urlChangeProduct.split("=");
    const id = str[str.length - 1];
    sessionStorage.setItem("product-id", id);
    if ((id - 1) >= 0) {
        findProductImageById(id)
    }
}

//============ Find Product By Id ===================

function findProductImageById(id) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/product-image/find-by-product?product-id=" + id,
        timeout: 30000,
        success: function (result) {
            showImageProduct(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

// ============ Show Product  Image========================
function showImageProduct(productImages) {
    $("#column-product-image").html(
        "<td style='text-align: center'> STT</td>" +
        "<td> Link ảnh </td>" +
        "<td> Chức Năng</td>"
    );
    const listSize = Object.keys(productImages).length;
    if (listSize > 0) {
        let contentRow = '';
        var index = 1;
        productImages.map(function (productImage) {
            contentRow += `
                        <tr>
                        <td style='text-align: center' > ${index} </td>
                        <td> ${productImage.url} </td>
                        <td> 
                              <div class="btn-group">
                                   <a class="btn btn-primary" href="create-image-product"><i class="fa fa-lg fa-plus"></i></a>
                                   <a class="btn btn-primary" href="update-image-product?id=${productImage.id}"><i class="fa fa-lg fa-edit"></i></a>
                                   <a class="btn btn-primary delete-image-product" name="${productImage.id}" ><i class="fa fa-lg fa-trash" style="color: white"></i></a>
                              </div>
                        </td>
                        </tr>
                    `;
            index++;
        });
        $("#row-product-image").html(contentRow);
        //============ delete =============
        deleteImageProduct();
    }
}

//============ Delete Image Product ========================
function deleteImageProduct() {

    $('.delete-image-product').click(function () {
        const id = $(this).attr("name");
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/product-image/delete?id=" + id,
            timeout: 30000,
            success: function () {
                alert('Xóa thành công');
                location.href = "image-product?product-id=" + idProduct;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Xóa thất bại");
                errMess(jqXHR, textStatus, errorThrown);
            }
        });
    });

}
