
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="resources/js/ajax/company/ajax_partner.js"></script>

<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> Bảng đối tác</h1>
        </div>
        <h3 class="title-body">
            Tổng số lượng : <span id="total-record"></span>
        </h3>
        <ul class="app-breadcrumb breadcrumb side">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item active"><a href="#">Partner</a></li>
        </ul>

    </div>
    <div class="table-responsive" style="overflow-x:auto;overflow-y: auto">
        <div class="title-table"></div>
        <table class="table text-center">
            <thead>
            <tr id="column-partner" style="font-weight: 600"></tr>
            </thead>
            <tbody id="row-partner"></tbody>
        </table>
    </div>
</main>

