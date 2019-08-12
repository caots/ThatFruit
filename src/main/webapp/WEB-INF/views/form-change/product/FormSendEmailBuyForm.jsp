<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="resources/js/ajax/product/buy_form/ajax_send_mail_buy_form.js"></script>
<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-edit"></i> Form gửi email khách hàng</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item"><a href="#">buy form</a></li>
        </ul>
    </div>
    <div class="row">
        <div class="col-sm-3"></div>
        <div class="col-md-6">
            <div class="tile">
                <div class="tile-body">
                    <form>
                        <div class="form-group">
                            <label class="control-label">Email</label>
                            <input id="name-email-bf" class="form-control" type="email"
                                   placeholder="Enter to email">
                        </div>
                        <div class="form-group">
                            <label class="control-label">Tiêu đề</label>
                            <input id="title-email-bf" class="form-control" type="text"
                                   placeholder="Enter your title">
                        </div>

                        <div class="form-group">
                            <label class="control-label">Nội dung</label>
                            <textarea id="content-email-bf" class="form-control" rows="4"
                                      placeholder="Enter your content"></textarea>
                        </div>

                    </form>
                </div>
                <div class="tile-footer">
                    <button class="btn btn-primary " id="btn-ok-email-bf" type="button"><i
                            class="fa fa-fw fa-lg fa-check-circle"></i>Gửi email
                    </button>&nbsp;&nbsp;&nbsp;<a class="btn btn-secondary" href="buy-form"><i
                        class="fa fa-fw fa-lg fa-times-circle"></i>Quay lại</a>
                </div>
            </div>
        </div>
        <div class="col-sm-3"></div>
    </div>
</main>