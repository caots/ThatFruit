<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="resources/js/ajax/product/tag/ajax_tag.js"></script>

<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> Bảng Tag</h1>
        </div>
        <h3 class="title-body">
            Tổng số lượng : <span id="total-record"></span>
        </h3>
        <ul class="app-breadcrumb breadcrumb side">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item active"><a href="#">Tag</a></li>
        </ul>

    </div>
    <!-- TABLE -->
   <div class="table-responsive">
        <div class="title-table"></div>
        <table class="table text-center">
            <thead>
            <tr id="column-tag" style="font-weight: 600"></tr>
            </thead>
            <tbody id="row-tag"></tbody>
        </table>
        <div class="pageable">
            <ul class="pagination"></ul>
        </div>
    </div>
</main>

