<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="resources/js/ajax/company/ajax_change_contact.js"></script>
<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-edit"></i> Thông tin Liên hệ</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item"><a href="#">Contact</a></li>
        </ul>
    </div>
    <div class="row">
        <div class="col-sm-3"></div>
        <div class="col-md-6">
            <div class="tile">
                <h3 class="tile-title">Thông tin liên hệ công ty</h3>
                <div class="tile-body">
                    <form>
                        <div class="form-group ">
                            <label class="control-label">Tên công ty</label>
                            <select class="form-control" id="company-value">
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Facebook</label>
                            <input id="facebook-company" class="form-control" type="text"
                                   placeholder="Enter your facebook">
                        </div>
                        <div class="form-group">
                            <label class="control-label">Zalo</label>
                            <input id="zalo-company" class="form-control" type="text" placeholder="Enter your zalo">
                        </div>
                        <div class="form-group">
                            <label class="control-label">Instagram</label>
                            <textarea id="instagram-company" class="form-control" rows="4"
                                      placeholder="Enter your instagram"></textarea>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Youtube</label>
                            <textarea id="youtube-company" class="form-control" rows="4"
                                      placeholder="Enter your youtube"></textarea>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Google Map</label>
                            <textarea id="map-company" class="form-control" rows="4"
                                      placeholder="Enter your localtion"></textarea>
                        </div>

                    </form>
                </div>
                <div class="tile-footer">
                    <button class="btn btn-primary " id="btn-ok-contact" type="button"><i
                            class="fa fa-fw fa-lg fa-check-circle"></i>Xác nhận
                    </button>&nbsp;&nbsp;&nbsp;<a class="btn btn-secondary" href="contact"><i
                        class="fa fa-fw fa-lg fa-times-circle"></i>Quay lại</a>
                </div>
            </div>
        </div>
        <div class="col-sm-3"></div>
    </div>
</main>