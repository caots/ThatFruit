<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                            <label class="control-label">Giá gốc</label>
                            <input id="origin-cost" class="form-control" type="number"
                                   placeholder="Enter origin cost">
                        </div>
                        <div class="form-group">
                            <label class="control-label">Giá bán</label>
                            <input id="sale-cost" class="form-control" type="number"
                                   placeholder="Enter sale cost">
                        </div>
                        <div class="form-group">
                            <label class="control-label">Xuất xứ</label>
                            <input id="origin-product" class="form-control" type="text"
                                   placeholder="Enter origin product">
                        </div>

                        <div class="form-group">
                            <label class="control-label">Đơn vị bán</label>
                            <input id="unit-product" class="form-control" type="text"
                                   placeholder="Enter unit product">
                        </div>
                        <div class="form-group ">
                            <label class="control-label">Tình trạng</label>
                            <select class="form-control" id="product-status" name="product-status-value">
                                <option value="true" >Còn hàng</option>
                                <option value="false">Hết hàng</option>
                            </select>
                        </div>

                        <div class="form-group ">
                            <label class="control-label">Loại sản phẩm</label>
                            <select class="form-control" id="small-category-value">
                            </select>
                        </div>
                        <div class="form-group ">
                            <label class="control-label">Phương thức</label>
                            <select class="form-control" id="product-type-value">
                            </select>
                        </div>
                        <div class="form-group ">
                            <label class="control-label">Thẻ tag</label>
                            <select class="form-control" id="tag-value">
                            </select>
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