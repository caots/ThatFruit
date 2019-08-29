<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="resources/js/ajax/category/big/ajax_big.js"></script>

<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> Bảng danh mục sản phẩm</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb side">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item active"><a href="#">Danh mục sản phẩm</a></li>
        </ul>

    </div>
    <!-- TABLE -->
    <div class="table-responsive" >
        <div class="title-table">
            <ul class="app-nav">

                <li style="margin-top: 10px;">
                    <button class="btn btn-primary" type="button">
                        <a href="create-big" style="color: white">
                            <i class="fa fa-fw fa-lg fa-check-circle"></i>
                            Thêm
                        </a>
                    </button>
                </li>
                <li class="app-search" style="margin: auto"></li>


            </ul>
        </div>

        <table class="table text-center">
            <thead>
            <tr id="column-big" style="font-weight: 600"></tr>
            </thead>
            <tbody id="row-big"></tbody>
        </table>
        <div class="pageable">
            <ul class="pagination"></ul>
        </div>
    </div>
</main>
