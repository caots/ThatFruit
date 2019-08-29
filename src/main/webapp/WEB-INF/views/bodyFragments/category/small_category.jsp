<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="resources/js/ajax/category/small/ajax_small.js"></script>

<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> Bảng loại mục sản phẩm</h1>
        </div>
        <h3 class="title-body">
            Tổng số lượng : <span id="total-record-small-category"></span>
        </h3>
        <ul class="app-breadcrumb breadcrumb side">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item active"><a href="#">Loại sản phẩm</a></li>
        </ul>

    </div>

    <!-- TABLE -->
   <div class="table-responsive">
        <ul class="app-nav">

            <li style="margin-top: 10px;">
                <button class="btn btn-primary" type="button">
                    <a href="create-small" style="color: white">
                        <i class="fa fa-fw fa-lg fa-check-circle"></i>
                        Thêm
                    </a>
                </button>
            </li>
            <li class="app-search" style="margin: auto"></li>


        </ul>
        <table class="table text-center">
            <thead>
            <tr id="column-small" style="font-weight: 600"></tr>
            </thead>
            <tbody id="row-small"></tbody>
        </table>
        <div class="pageable">
            <ul class="pagination"></ul>
        </div>
    </div>
</main>
