<%@ page contentType="text/html;charset=UTF-8" %>
<script src="resources/js/ajax/news/news/ajax_news.js"></script>
<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> Bảng danh mục bài viết</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb side">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item active"><a href="#">News</a></li>
        </ul>

    </div>
    <!-- TABLE -->
    <div class="table-responsive" style="overflow-x:auto;overflow-y: auto">
        <div class="title-table">

        </div>

        <table class="table text-center">
            <thead>
            <tr id="column-news" style="font-weight: 600"></tr>
            </thead>
            <tbody id="row-news"></tbody>
        </table>
        <div class="pageable">
            <ul class="pagination"></ul>
        </div>
    </div>
</main>
