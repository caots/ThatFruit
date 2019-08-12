$(document).ready(function () {
    findAllPageProductNumber();
    searchProductByName(1);
});

//==================================page=====================================

function pageProduct(size) {
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

function findAllPageProductNumber() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/product/size",
        success: function (size) {

            findAllProductBySize(1);
            pageProduct(size);
            $('.page').click(function () {
                const page = $(this).attr("name");
                for (let i = 1; i <= size; i++) {
                    var id = "#_" + i;
                    $(id).removeClass("active");
                }
                $(this).addClass("active");
                findAllProductBySize(page);
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}


//============ FIND ALL PRODUCT ========================
function findAllProductBySize(page) {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/product/page?page=" + page,
        success: function (products) {
            displayOnTable(products);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    })
}


function displayOnTable(products) {
    $("#column-product").html(
        "<td>STT </td>" +
        "<td>Tên sản phẩm </td>" +
        "<td>Tình trạng </td>" +
        "<td>Giá Gốc</td>" +
        "<td>Giá Bán </td>" +
        "<td>Xuất xứ </td>" +
        "<td>Loại sản phẩm</td>" +
        "<td>Ảnh sản phẩm </td>" +
        "<td>Giới thiệu </td>" +
        "<td>Chức năng</td>"
    );
    const listSize = Object.keys(products).length;
    if (listSize > 0) {
        $('#total-record').text(listSize);
        let contentRow;
        var index = 1;
        products.map(function (product) {

            var productStatus = product.productStatus;
            console.log(productStatus);
            if (productStatus === true) {
                productStatus = 'Còn hàng';
            } else {
                productStatus = 'Hết hàng';
            }

            var originCost = formatNumber(product.originCost, '.', '.');
            var saleCost = formatNumber(product.saleCost, '.', '.');

            contentRow += `
                         <tr>
                         <td> ${index} </td>
                         <td> ${product.name} </td>
                         <td > ${product.productCode} </td>
                         <td> ${originCost} </td>
                         <td> ${saleCost} </td>
                         <td> ${product.origin} </td>
                         <td> ${product.productType.name} </td>
                         <td>   <a href="image-product?product-id=${product.id}" name="${product.id}" style="cursor: pointer;color: green">Kho ảnh </a>&nbsp;<br> </td>
                         <td>   <a href="update-news-product?id=${product.id}" name="${product.id}" style="cursor: pointer;color: green">Bài viết</a>&nbsp;<br> </td>
                         <td style="min-width: 160px">
                            <div class="btn-group">
                                   <a class="btn btn-primary" href="create-product"><i class="fa fa-lg fa-plus"></i></a>
                                   <a class="btn btn-primary" href="update-product?id=${product.id}" name="${product.id}"><i class="fa fa-lg fa-edit"></i></a>
                                   <a class="btn btn-primary delete-product" name="${product.id}" ><i class="fa fa-lg fa-trash" style="color: white"></i></a>
                             </div>
                        </td>
                        </tr>
                       `;
            index++;
        });
        $("#row-product").html(contentRow);
        $(".body-main .table-responsive tr td").css({
            "max-width": "260px",
            "overflow": "-webkit-paged-y"
        });
//===== delete =======
        deleteProduct();
    }
}

//============ Delete PRODUCT ========================
function deleteProduct() {
    $('.delete-product').click(function () {
        const id = $(this).attr("name");
        console.log("id-product " + id);
        $.ajax({
            type: "PUT",
            headers: {
                "Authorization": tokenHeader_value,
            },
            contentType: "application/json",
            url: "api/v1/admin/product/delete?id=" + id,
            timeout: 30000,
            success: function () {
                alert('Xóa thành công');
                location.href = "product";
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
            }
        });
    });
}

//=========================== SEARCH BY NAME ===================================
function searchProductByName(page) {
    $("#btn-search-product").keypress(function (event) {
        const keycode = event.keycode ? event.keycode : event.which;
        if (keycode == 13) {
            const nameProduct = $('#name-product').val();
            $.ajax({
                type: "GET",
                headers: {
                    "adminbksoftwarevn": value_token_public,
                },
                url: "api/v1/public/product/name/page?name=" + nameProduct + "&page=" + page,
                success: function (products) {
                    findAllPageProductByNameNumber();
                    displayOnTable(products);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    errMess(jqXHR, textStatus, errorThrown);
                }
            })
        }
    });
}

function findAllPageProductByNameNumber() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/products/name/size",
        success: function (size) {
            pageProduct(size);
            $('.page').click(function () {
                    const page = $(this).attr("name");
                    searchProductByName(page);
                }
            );
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}
