<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="resources/js/ajax/company/ajax_change_partner.js"></script>
<main class="app-content">

    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> Chỉnh sửa Đối tác</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb side">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item active"><a href="#">partner</a></li>
        </ul>
    </div>

    <div class="clearix"></div>
    <div class="col-md-12">
        <div class="tile">
            <div class="tile-body">
                <form class="row">
                    <div class="col-md-3" style="padding-top: 25px">
                        <h3 class="tile-title" style="color: #009688;text-align: center;">Đối tác </h3>
                    </div>
                    <div class="form-group col-md-6">
                        <label class="control-label">Tên đối tác </label>
                        <input class="form-control" id="name-partner" type="text" placeholder="Enter your partner">
                    </div>
                    <div class="form-group col-md-3 align-self-end">
                        <button id="btn-ok-partner" class="btn btn-primary" type="button"><i
                                class="fa fa-fw fa-lg fa-check-circle"></i>Đồng ý
                        </button>
                        <button id="btn-back-menu" class="btn btn-warning" type="button">
                            <a href="partner" style="color: white">
                                <i class="far fa-caret-square-left"></i> Trở về </a>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</main>