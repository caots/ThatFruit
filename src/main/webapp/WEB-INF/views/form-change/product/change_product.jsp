<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="resources/js/ajax/product/product/ajax_product_change.js"></script>
<script src="resources/js/ajax/product/product/ajax_show_relationship.js"></script>
<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-edit"></i> Thông tin Sản phẩm</h1>
            <p>Product forms</p>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item"><a href="#">product</a></li>
        </ul>
    </div>
    <div class="row">
        <div class="col-sm-3"></div>
        <div class="col-md-6">
            <div class="tile">
                <h3 class="tile-title">Thông tin sản phẩm</h3>
                <div class="tile-body">
                    <form>
                        <div class="form-group">
                            <label class="control-label">Tên sản phẩm</label>
                            <input id="name-product" class="form-control" type="text"
                                   placeholder="Enter name product">
                        </div>

                        <div class="form-group">
                            <label class="control-label">Mã sản phẩm</label>
                            <input id="code-product" class="form-control" type="text"
                                   placeholder="Enter code product">
                        </div>
                        <div class="form-group">
                            <label class="control-label">Giá gốc bán lẻ</label>
                            <input id="origin-cost-Retail" class="form-control" type="number"
                                   placeholder="Enter origin cost">
                        </div>
                        <div class="form-group">
                            <label class="control-label">Giá bán lẻ</label>
                            <input id="sale-cost-Retail" class="form-control" type="number"
                                   placeholder="Enter sale cost">
                        </div>
                        <div class="form-group">
                            <label class="control-label">Giá gốc bán buôn</label>
                            <input id="origin-cost-wholesale" class="form-control" type="number"
                                   placeholder="Enter origin cost">
                        </div>
                        <div class="form-group">
                            <label class="control-label">Giá bán buôn</label>
                            <input id="sale-cost-wholesale" class="form-control" type="number"
                                   placeholder="Enter sale cost">
                        </div>
                        <div class="form-group">
                            <label class="control-label">Xuất xứ</label>
                            <input id="origin-product" class="form-control" type="text"
                                   placeholder="Enter origin product">
                        </div>

                        <div class="form-group">
                            <label class="control-label">Ngày hết Sale</label>
                            <input class="form-control" id="demoDate" type="text" placeholder="Select Date">
                        </div>

                        <div class="form-group ">
                            <label class="control-label">Thẻ tag</label>
                            <input id="tag-product" class="form-control" type="text"
                                   placeholder="'@tag' viết liền không cách và không trùng tag cũ">
                            <label class="control-label" id="list-tag" style="background: #80808036"></label>
                        </div>
                        <div class="form-group ">
                            <label class="control-label">Tình trạng</label>
                            <select class="form-control" id="product-status" name="product-status-value">
                                <option value="true">Còn hàng</option>
                                <option value="false">Hết hàng</option>
                            </select>
                        </div>

                        <div class="form-group ">
                            <label class="control-label">Loại sản phẩm</label>
                            <select class="form-control" id="small-category-value">
                            </select>
                        </div>
                        <div class="form-group ">
                            <label class="control-label">Ảnh sản phẩm</label>
                            <form method="POST" action="" enctype="multipart/form-data"
                                  id="btn-img-request">
                                <div class="form-group row" style="justify-content: center">
                                    <img class="user-img"
                                         id="url-image-product"
                                         src=""
                                         width="40%" height="40%">
                                </div>
                                <div>
                                    <td><input id="change-product" name="image" type="file"/></td>
                                    <%--<input type="file" class="form-control-file" name="image" multiple="multiple" id="change-product">--%>
                                </div>
                            </form>
                        </div>

                    </form>
                </div>
                <div class="tile-footer">
                    <button class="btn btn-primary " id="btn-ok-product" type="button"><i
                            class="fa fa-fw fa-lg fa-check-circle"></i>Xác nhận
                    </button>&nbsp;&nbsp;&nbsp;<a class="btn btn-secondary" href="product"><i
                        class="fa fa-fw fa-lg fa-times-circle"></i>Quay lại</a>
                </div>
            </div>
        </div>
        <div class="col-sm-3"></div>
    </div>
</main>
<!-- Page specific javascripts-->
<script type="text/javascript" src="resources/js/template/plugins/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="resources/js/template/plugins/select2.min.js"></script>
<script type="text/javascript" src="resources/js/template/plugins/bootstrap-datepicker.min.js"></script>
<script type="text/javascript">
    $('#sl').click(function () {
        $('#tl').loadingBtn();
        $('#tb').loadingBtn({text: "Signing In"});
    });

    $('#el').click(function () {
        $('#tl').loadingBtnComplete();
        $('#tb').loadingBtnComplete({html: "Sign In"});
    });

    $('#demoDate').datepicker({
        format: "dd/mm/yyyy",
        autoclose: true,
        todayHighlight: true
    });

    $('#demoSelect').select2();
</script>