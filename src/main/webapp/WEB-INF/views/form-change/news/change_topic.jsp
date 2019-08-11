<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="resources/js/ajax/news/topic/ajax_change_topic.js"></script>
<main class="app-content">

    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> Chỉnh sửa bài viết</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb side">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item active"><a href="#">chỉnh sửa news</a></li>
        </ul>
    </div>

    <div class="col-md-12">
        <div class="tile">
            <div class="tile-body">
                <form class="row">
                    <div class="col-md-3" style="padding-top: 25px">
                        <h3 class="tile-title title-topic" style="color: #009688">Danh mục bài viết </h3>
                    </div>
                    <div class="form-group col-md-6">
                        <label class="control-label">Tên danh mục </label>
                        <input class="form-control" id="name-topic" type="text" placeholder="Enter your name">
                    </div>
                    <div class="form-group col-md-3 align-self-end">
                        <button id="btn-ok-topic" class="btn btn-primary" type="button"><i
                                class="fa fa-fw fa-lg fa-check-circle"></i>Đồng ý
                        </button>
                        <button id="btn-back-menu" class="btn btn-warning" type="button">
                            <a href="topic" style="color: white">
                                <i class="far fa-caret-square-left"></i> Trở về </a>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</main>
