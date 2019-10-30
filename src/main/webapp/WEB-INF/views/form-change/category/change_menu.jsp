<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="resources/js/ajax/category/menu/ajax_change_menu.js"></script>
<script src="resources/js/ajax/category/ajax_category_create_select.js"></script>
<main class="app-content">

    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> Chỉnh sửa Danh mục</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb side">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item active"><a href="#">chỉnh sửa danh mục</a></li>
        </ul>

    </div>

    <div class="col-md-12">
        <div class="tile">
            <div class="tile-body" id="menu">
                <form class="row">
                    <div class="col-md-3" style="padding-top: 25px">
                        <h3 class="tile-title title-menu" style="color: #009688">Danh mục </h3>
                    </div>
                    <div class="form-group col-md-6">
                        <label class="control-label">Tên danh mục </label>
                        <input class="form-control" id="name-menu" type="text" placeholder="Enter your name">
                    </div>
                    <div class="form-group col-md-3 align-self-end">
                        <button id="btn-ok-menu" class="btn btn-primary" type="button"><i
                                class="fa fa-fw fa-lg fa-check-circle"></i>Đồng ý
                        </button>
                        <button id="btn-back-menu" class="btn btn-warning" type="button">
                            <a href="menu-category" style="color: white">
                                <i class="far fa-caret-square-left"></i> Trở về </a>
                        </button>
                    </div>
                </form>
            </div>




        </div>
    </div>
</main>