<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="resources/js/ajax/product/product/ajax_product.js"></script>

<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> Bảng sản phẩm</h1>
        </div>
        <h3 class="title-body">
            Tổng số lượng : <span id="total-record-product"></span>
        </h3>
        <ul class="app-breadcrumb breadcrumb side">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item active"><a href="#">Sản phẩm</a></li>
        </ul>
    </div>
    <!-- TABLE -->
    <div class="table-responsive" style="overflow-x:auto;overflow-y: auto">
        <div class="title-table">
            <ul class="app-nav">

                <li style="margin-top: 10px;">
                    <button class="btn btn-primary" type="button">
                        <a href="create-product" style="color: white">
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
        </div>

        <table class="table text-center">
            <thead>
            <tr id="column-product" style="font-weight: 600"></tr>
            </thead>
            <tbody id="row-product"></tbody>
        </table>
        <div class="pageable">
            <ul class="pagination"></ul>
        </div>
    </div>
</main>
