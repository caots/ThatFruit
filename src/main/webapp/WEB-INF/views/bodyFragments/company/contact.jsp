<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="resources/js/ajax/company/ajax_contact.js"></script>

<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> Bảng thông tin liên hệ công ty </h1>
        </div>

        <ul class="app-breadcrumb breadcrumb side">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item active"><a href="#">Contact</a></li>
        </ul>

    </div>
   <div class="table-responsive">
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

        <table class="table text-center">
            <thead>
            <tr id="column-contact" style="font-weight: 600"></tr>
            </thead>
            <tbody id="row-contact"></tbody>
        </table>
    </div>
</main>


