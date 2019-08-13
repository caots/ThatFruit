$(document).ready(function () {
    clickBtnProductChangeSubmit();
});

//============ Create Product ========================
function createProduct() {

    let idSmallCategory = '';
    $('#small-category-value').click(function () {
        idSmallCategory = $(this).val();
    });
    let productStatus = '';
    $('#product-status').click(function () {
        productStatus = $(this).val();
    });

    $('#btn-ok-product').click(function () {
        const listtag = $('#tag-product').val();
        const nameProduct = $("#name-product").val();
        const codeProduct = $("#code-product").val();
        const originCostRetail = $("#origin-cost-Retail").val();
        const saleCostRetail = $("#sale-cost-Retail").val();
        const saleCostWholesale = $("#origin-cost-wholesale").val();
        const originCostWholesale = $("#sale-cost-wholesale").val();
        const originProduct = $("#origin-product").val();
        var formImg = $('#btn-img-product-main')[0];
        var formData = new FormData(formImg);

        uploadFile(formData).then(function (data) {

            const product = {
                "name": nameProduct,
                "productStatus": productStatus,
                "image": data,
                "productCode": codeProduct,
                "saleCostRetail": saleCostRetail,
                "originCostRetail": originCostRetail,
                "saleCostWholesale": saleCostWholesale,
                "originCostWholesale": originCostWholesale,
                "origin": originProduct,
            };
            $.ajax({
                type: "POST",
                contentType: "application/json",
                headers: {
                    "Authorization": tokenHeader_value,
                },
                url: "api/v1/admin/product?small-category-id=" + idSmallCategory +
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
        listTag += '@' + tag.name + ' ';
    });

    $("#tag-product").val(listTag);
    $("#name-product").val(data.name);
    $("#code-product").val(data.productCode);
    $("#origin-cost-Retail").val(data.originCostRetail);
    $("#sale-cost-Retail").val(data.saleCostRetail);
    $("#origin-cost-wholesale").val(data.originCostWholesale);
    $("#sale-cost-wholesale").val(data.saleCostWholesale);
    $("#origin-product").val(data.origin);
    $('#product-status').val(data.productStatus);


    $("#tag-product").prop("disabled", true);
    $("#small-category-value").prop("disabled", true);
    $('#btn-ok-product').click(function () {

        data.name = $("#name-product").val();
        data.productCode = $("#code-product").val();
        data.originCostRetail = $("#origin-cost-Retail").val();
        data.saleCostRetail = $("#sale-cost-Retail").val();
        data.origin = $("#origin-product").val();
        data.saleCostWholesale = $("#sale-cost-wholesale").val();
        data.originCostWholesale = $("#origin-cost-wholesale").val();
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

var uploadFile = async (file) => {
    let data;
    await $.ajax({
        type: "POST",
        headers: {
            "Authorization": tokenHeader_value,
        },
        url: "/api/v1/public/upload-file",
        enctype: 'multipart/form-data',
        data: file,
        cache: false,
        processData: false,
        contentType: false,
        success: function (result) {
            data = result
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
    return data;
};