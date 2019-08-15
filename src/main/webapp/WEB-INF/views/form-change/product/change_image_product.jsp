<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="resources/js/ajax/product/product/ajax_change_image_product.js"></script>
<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-edit"></i> Chỉnh sửa ảnh Sản phẩm</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item"><a href="#">update image product</a></li>
        </ul>
    </div>
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div class="tile">
                <h3 class="tile-title" style="text-align: center">Ảnh sản phẩm</h3>
                <div class="tile-body">
                    <div class="form-horizontal">
                        <div class="form-group row" style="justify-content: center">
                            <img class="user-img"
                                 id="url-image-product1"
                                 src="resources/img/logothatfruit.png"
                                 alt="image home page">
                        </div>

                        <div class="form-group row">
                            <label class=" col-md-3"></label>
                            <form method="POST" action="" enctype="multipart/form-data"
                                  id="btn-img-request1">
                                <div>
                                    <td><input id="change-product1" name="image" type="file"/></td>
                                    <%--<input type="file" class="form-control-file" name="image" multiple="multiple" id="change-product">--%>
                                </div>
                            </form>
                            <label class="col-md-1"></label>
                        </div>
                    </div>
                </div>
                <div class="tile-body">
                    <div class="form-horizontal">
                        <div class="form-group row" style="justify-content: center">
                            <img class="user-img"
                                 id="url-image-product2"
                                 src="resources/img/logothatfruit.png"
                                 alt="image home page">
                        </div>

                        <div class="form-group row">
                            <label class=" col-md-3"></label>
                            <form method="POST" action="" enctype="multipart/form-data"
                                  id="btn-img-request2">
                                <div>
                                    <td><input id="change-product2" name="image" type="file"/></td>
                                </div>
                            </form>
                            <label class="col-md-1"></label>
                        </div>
                    </div>
                </div>
                <div class="tile-body">
                    <div class="form-horizontal">
                        <div class="form-group row" style="justify-content: center">
                            <img class="user-img"
                                 id="url-image-product3"
                                 src="resources/img/logothatfruit.png"
                                 alt="image home page">
                        </div>

                        <div class="form-group row">
                            <label class=" col-md-3"></label>
                            <form method="POST" action="" enctype="multipart/form-data"
                                  id="btn-img-request3">
                                <div>
                                    <td><input id="change-product3" name="image" type="file"/></td>
                                </div>
                            </form>
                            <label class="col-md-1"></label>
                        </div>
                    </div>
                </div>
                <%--button submit--%>
                <div class="tile-footer">
                    <div class="row" style="justify-content: center">
                        <div class="col-md-8 col-md-offset-3" style="text-align: center">
                            </button>&nbsp;&nbsp;&nbsp;<a href="product" id="btn-image-product"
                                                          class="btn btn-primary ">
                            <i class="fa fa-fw fa-lg fa-check-circle"></i>Hoàn tất</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>
</main>