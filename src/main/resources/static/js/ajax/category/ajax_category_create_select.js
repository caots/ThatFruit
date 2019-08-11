$(document).ready(function () {
    findAllNameMenuCategory();
    findAllNameBigCategory();
    findAllNameSmallCategory();
});

function findAllNameBigCategory() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/big-category/all",
        success: function (bigCategories) {
            const listSize = Object.keys(bigCategories).length;
            if (listSize > 0) {

                let contentRow = '';
                bigCategories.map(function (bigCategory) {
                    contentRow += `
                       <option value ="${bigCategory.id}" >${bigCategory.name}</option>
                    `;
                });
                $("#big-category-value").html(contentRow);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function findAllNameMenuCategory() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/menu/all",
        success: function (mediumCategories) {
            const listSize = Object.keys(mediumCategories).length;
            if (listSize > 0) {
                let contentRow = '';
                mediumCategories.map(function (mediumCategory) {
                    contentRow += `
                       <option value="${mediumCategory.id}">${mediumCategory.name}</option>
                    `;
                });
                $("#menu-value").html(contentRow);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function findAllNameSmallCategory() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/small-category/all",
        success: function (smallCategories) {
            const listSize = Object.keys(smallCategories).length;
            /* if (listSize >= 0) {
                 let contentRow = '';
                 smallCategories.map(function (smallCategory) {
                     contentRow += `
                        <option value="${smallCategory.id}">${smallCategory.name}</option>
                     `;
                 });
                 $("#small-category-value").html(contentRow);
             }*/
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}