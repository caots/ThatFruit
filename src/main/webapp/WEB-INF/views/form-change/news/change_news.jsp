<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="resources/js/ajax/news/news/ajax_change_news.js"></script>
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
                <div class="row">
                    <div class="col-md-3" style="padding-top: 25px">
                        <h3 class="tile-title title-topic" style="color: #009688">Danh mục bài viết </h3>
                    </div>
                    <div class="form-group col-md-5">
                        <label class="control-label">Danh mục</label>
                        <select class="form-control" id="topic-value">
                        </select>
                    </div>
                    <div class="form-group col-md-3 align-self-end">
                        <button id="btn-ok-news" class="btn btn-primary" type="button"><i
                                class="fa fa-fw fa-lg fa-check-circle"></i>Đồng ý
                        </button>
                        <button id="btn-back-menu" class="btn btn-warning" type="button">
                            <a href="news" style="color: white">
                                <i class="far fa-caret-square-left"></i> Trở về </a>
                        </button>
                    </div>
                </div>
            </div>
            <div class="tile-body">
                <div class="row">
                    <div class="col-md-3" style="padding-top: 25px"></div>
                    <div class="form-group col-5">
                        <label class="control-label">Tiêu đề </label>
                        <input class="form-control" id="name-title" type="text" placeholder="Enter your title">
                    </div>
                    <div class="col-md-3" style="padding-top: 25px"></div>
                </div>
            </div>
            <div class="tile-body">
                <div class="row">
                    <div class="col-md-3" style="padding-top: 25px"></div>
                    <div class="form-group col-5">
                        <label class="control-label">Mô tả bài viết </label>
                        <textarea class="form-control" id="name-description"></textarea>
                    </div>
                    <div class="col-md-3" style="padding-top: 25px"></div>
                </div>
            </div>
            <div class="tile-body">
                <div class="row">
                    <div class="col-md-3" style="padding-top: 25px"></div>
                    <div class="col-md-5">
                        <div class="form-group row" style="justify-content: center">
                            <img class="user-img"
                                 id="url-image-news"
                                 src="">
                        </div>
                        <form id="upload-image-news" method="POST" action="" enctype="multipart/form-data">
                            <div>
                                <input id="change-news" name="image" type="file"/>
                            </div>
                        </form>
                        <span style="color: #ff0000">Ảnh hiển thị tốt nhất trong khoảng 330x180(px)</span>
                    </div>
                    <div class="col-md-3" style="padding-top: 50px"></div>
                </div>
            </div>

            <div class="tile-body" style="padding-top: 50px">
                <div class="row">
                    <div id="sample col-11" style="margin-left: 130px ">
                        <script type="text/javascript" src="http://js.nicedit.com/nicEdit-latest.js"></script>
                        <script type="text/javascript">
                            //<![CDATA[
                            bkLib.onDomLoaded(function () {
                                new nicEditor({fullPanel: true}).panelInstance('area2');
                            });
                            //]]>
                        </script>
                        <textarea cols="120" id="area2"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>

</main>
