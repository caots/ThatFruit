$(document).ready(function () {
    clickBtnProductChangeSubmit();
});

//============ Create Product ========================
function createProduct() {

    let idSmallCategory = '';
    $('#small-category-value').click(function () {
        idSmallCategory = $(this).val();
    });
    let idSTypeProduct = '';
    $('#product-type-value').click(function () {
        idSTypeProduct = $(this).val();
    });
    let productStatus = '';
    $('#product-status').click(function () {
        productStatus = $(this).val();
    });

    $('#btn-ok-product').click(function () {
        const listtag = $('#tag-product').val();
        const nameProduct = $("#name-product").val();
        const codeProduct = $("#code-product").val();
        const originCost = $("#origin-cost").val();
        const saleCost = $("#sale-cost").val();
        const originProduct = $("#origin-product").val();
        const unitProduct = $("#unit-product").val();
        const product = {
            "name": nameProduct,
            "productStatus": productStatus,
            "productCode": codeProduct,
            "saleCost": originCost,
            "originCost": saleCost,
            "origin": originProduct,
            "unit": unitProduct,
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/product?small-category-id=" + idSmallCategory +
                "&product-type-id=" + idSTypeProduct +
                "&tag=" + listtag,
            data: JSON.stringify(product),
            cache: false,
            timeout: 300000,
            success: function () {
                alert("Thêm thành công");
                $("#btn-ok-product").prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("Thêm thất bại ");
            }
        })
    });
}

//============ Find Product By Id ===================

function findProductById(id) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/product/find-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            updateProduct(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

// ============ update Product ========================
function updateProduct(data) {

    var listTag = '';
    console.log(data);
    data.tags.map(function (tag) {
        listTag += '@'+tag.name + ' ';
    });

    $("#tag-product").val(listTag);
    $("#name-product").val(data.name);
    $("#code-product").val(data.productCode);
    $("#origin-cost").val(data.originCost);
    $("#sale-cost").val(data.saleCost);
    $("#origin-product").val(data.origin);
    $("#unit-product").val(data.unit);
    $('#product-status').val(data.productStatus);


    $("#small-category-value").prop("disabled", true);
    $("#product-type-value").prop("disabled", true);
    $('#btn-ok-product').click(function () {

        data.name = $("#name-product").val();
        data.productCode = $("#code-product").val();
        data.originCost = $("#origin-cost").val();
        data.saleCost = $("#sale-cost").val();
        data.origin = $("#origin-product").val();
        data.unit = $("#unit-product").val();
        if ($('#product-status').val() == null) $('#product-status').val('true');
        data.productStatus = $('#product-status').val();

        var listTagAfter = $('#tag-product').val();
        console.log(listTagAfter);
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/product?list-tag=" + listTagAfter,
            data: JSON.stringify(data),
            timeout: 30000,
            success: function () {
                alert('Chỉnh sửa thành công');
                $("#btn-ok-product").prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("Chỉnh sửa thất bại ");
            }
        });
    });
}

function clickBtnProductChangeSubmit() {
    const urlChangeProduct = window.location.href;
    var str = urlChangeProduct.split("=");
    const id = str[str.length - 1];
    if ((id - 1) >= 0) {
        findProductById(id)
    } else {
        createProduct();
    }

}