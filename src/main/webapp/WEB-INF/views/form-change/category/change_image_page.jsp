<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="resources/js/ajax/category/image_home/ajax_image_page_change.js"></script>
<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-edit"></i> Ảnh trang chủ</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item">Forms</li>
            <li class="breadcrumb-item"><a href="#">image page</a></li>
        </ul>
    </div>
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div class="tile">
                <h3 class="tile-title" style="text-align: center">Ảnh trang chủ</h3>
                <div class="tile-body">
                    <form class="form-horizontal">
                        <div class="form-group row" style="justify-content: center">
                            <img class="user-img"
                                 id="url-image-page"
                                 src="https://avatars3.githubusercontent.com/u/44569407?s=460&v=4"
                                 alt="image home page">
                        </div>

                        <div class="form-group row">
                            <label class=" col-md-3"></label>
                            <form method="POST" action="" enctype="multipart/form-data"
                                  id="btn-img-request">
                                <div>
                                    <td><input id="change-product" name="image" type="file" /></td>
                                    <%--<input type="file" class="form-control-file" name="image" multiple="multiple" id="change-product">--%>
                                </div>
                            </form>
                            <label class=" col-md-1"></label>
                        </div>
                    </form>
                </div>
                <%--button submit--%>
                <div class="tile-footer">
                    <div class="row" style="justify-content: center">
                        <div class="col-md-8 col-md-offset-3" style="text-align: center">
                            <button class="btn btn-primary" id="btn-ok-image-page" type="button"><i
                                    class="fa fa-fw fa-lg fa-check-circle"></i>Đồng ý
                            </button>&nbsp;&nbsp;&nbsp;<a class="btn btn-secondary" href="image-page"><i
                                class="fa fa-fw fa-lg fa-times-circle"></i>Cancel</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>
</main>
