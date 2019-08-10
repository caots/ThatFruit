<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="resources/js/ajax/category/menu/ajax_change_menu.js"></script>
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

    <div class="clearix"></div>
    <div class="col-md-12">
        <div class="tile">
            <div class="tile-body">
                <form class="row">
                    <div class="col-md-3" style="padding-top: 25px">
                        <h3 class="tile-title" style="color: #009688">Danh mục </h3>
                    </div>
                    <div class="form-group col-md-6">
                        <label class="control-label">Tên danh mục </label>
                        <input class="form-control" id="name-menu" type="text" placeholder="Enter your name">
                    </div>
                    <div class="form-group col-md-3 align-self-end">
                        <button id="btn-ok-menu" class="btn btn-primary" type="button"><i
                                class="fa fa-fw fa-lg fa-check-circle"></i>Đồng ý
                        </button>
                    </div>
                </form>
            </div>



            <div class="tile-body">
                <form class="row">
                    <div class="col-md-3" style="padding-top: 25px">
                        <h3 class="tile-title" style="color: #009688">Danh mục sản phẩm </h3>
                    </div>
                    <div class="form-group col-md-3">
                        <label class="control-label">Tên danh mục sản phẩm</label>
                        <input class="form-control" id="name-big-category" type="text" placeholder="Enter your name">
                    </div>
                    <div class="form-group col-md-3">
                        <label class="control-label">Danh mục</label>
                        <select class="form-control" id="big-category-value">
                        </select>
                    </div>
                    <div class="form-group col-md-3 align-self-end">
                        <button id="btn-ok-big" class="btn btn-primary" type="button"><i
                                class="fa fa-fw fa-lg fa-check-circle"></i>Đồng ý
                        </button>
                    </div>
                </form>
            </div>

            <div class="tile-body">
                <form class="row">
                    <div class="col-md-3" style="padding-top: 25px">
                        <h3 class="tile-title" style="color: #009688">Loại sản phẩm </h3>
                    </div>
                    <div class="form-group col-md-3">
                        <label class="control-label">Tên loại mục sản phẩm</label>
                        <input class="form-control" id="name-small-category" type="text" placeholder="Enter your name">
                    </div>
                    <div class="form-group col-md-3">
                        <label class="control-label">Danh mục</label>
                        <select class="form-control" id="big-small-value"> </select>
                    </div>
                    <div class="form-group col-md-3 align-self-end">
                        <button id="btn-ok-small" class="btn btn-primary" type="button"><i
                                class="fa fa-fw fa-lg fa-check-circle"></i>Đồng ý
                        </button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</main>