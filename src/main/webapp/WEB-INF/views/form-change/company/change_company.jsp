<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="resources/js/ajax/company/ajax_change_company.js"></script>
<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-edit"></i> Thông tin công ty</h1>
            <p>Sample forms</p>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item"><a href="#">Company</a></li>
        </ul>
    </div>
    <div class="row">
        <div class="col-sm-3"></div>
        <div class="col-md-6">
            <div class="tile">
                <h3 class="tile-title">Thông tin công ty</h3>
                <div class="tile-body">
                    <form>
                        <div class="form-group">
                            <label class="control-label">Tên công ty</label>
                            <input id="name-company" class="form-control" type="text" placeholder="Enter full name">
                        </div>
                        <div class="form-group">
                            <label class="control-label">Số điện thoại</label>
                            <input id="phone-company" class="form-control" type="number"
                                   placeholder="Enter your phone">
                        </div>
                        <div class="form-group">
                            <label class="control-label">Email</label>
                            <input id="email-company" class="form-control" type="email" placeholder="Enter your email">
                        </div>
                        <div class="form-group">
                            <label class="control-label">Đại chỉ</label>
                            <textarea id="address-company" class="form-control" rows="4"
                                      placeholder="Enter your address"></textarea>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Mô tả</label>
                            <textarea id="description-company" class="form-control" rows="4"
                                      placeholder="Enter your description"></textarea>
                        </div>

                    </form>
                </div>
                <div class="tile-footer">
                    <button class="btn btn-primary " id="btn-ok-company" type="button"><i
                            class="fa fa-fw fa-lg fa-check-circle"></i>Xác nhận
                    </button>&nbsp;&nbsp;&nbsp;<a class="btn btn-secondary" href="company"><i
                        class="fa fa-fw fa-lg fa-times-circle"></i>Quay lại</a>
                </div>
            </div>
        </div>
        <div class="col-sm-3"></div>
    </div>
</main>