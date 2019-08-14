<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="resources/js/ajax/product/product/ajax_image_product.js"></script>
<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-edit"></i> Danh mục ảnh sản phẩm</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item"><a href="#">product image</a></li>
        </ul>
    </div>
    <!-- TABLE -->
    <div class="table-responsive" style="overflow-x:auto;overflow-y: auto">
        <ul class="app-nav">

            <li style="margin-top: 10px;">
                <button class="btn btn-primary" type="button">
                    <a href="create-image-product" style="color: white">
                        <i class="fa fa-fw fa-lg fa-check-circle"></i>
                        Thêm
                    </a>
                </button>
            </li>
            <li class="app-search" style="margin: auto">
                <input class="app-search__input" id="name-product" type="search" placeholder="Tìm kiếm">
                <button class="app-search__button" id="btn-search-product"><i class="fa fa-search"></i></button>
            </li>

        </ul>
        <div class="title-table"></div>
        <table class="table text-center">
            <thead>
            <tr id="column-product-image" style="font-weight: 600"></tr>
            </thead>
            <tbody id="row-product-image"></tbody>
        </table>
    </div>
</main>


