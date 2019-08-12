$(document).ready(function () {
    findAllNameProductType();
    findAllNameSmallCategory();

});

function findAllNameProductType() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/product-type/all",
        success: function (productType) {
            const listSize = Object.keys(productType).length;
            if (listSize > 0) {

                let contentRow = '';
                productType.map(function (product) {
                    contentRow += `

                       <option  value ="${product.id}" >${product.name}</option>
                    `;
                });
                $("#product-type-value").html(contentRow);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

/*function findAllNameTag() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/tag/all",
        success: function (tags) {
            const listSize = Object.keys(tags).length;
            if (listSize > 0) {
                let contentRow = '';
                tags.map(function (tag) {
                    contentRow += `
                       <option data-fas="laptop blue-text" value="${tag.id}">${tag.name}</option>
                    `;
                });
                $("#tag-value").html(contentRow);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}*/

function findAllNameSmallCategory() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/small-category/all",
        success: function (smallCategories) {
            const listSize = Object.keys(smallCategories).length;
            if (listSize >= 0) {
                let contentRow = '';
                smallCategories.map(function (smallCategory) {
                    contentRow += `
                        <option value="${smallCategory.id}">${smallCategory.name}</option>
                     `;
                });
                $("#small-category-value").html(contentRow);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}