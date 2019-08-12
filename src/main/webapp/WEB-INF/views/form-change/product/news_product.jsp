<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="resources/js/ajax/product/product/ajax_change_news_product.js"></script>
<main class="app-content">

    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> Bài viết sản phẩm</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb side">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item active"><a href="#">product news</a></li>
        </ul>
    </div>

    <div class="col-md-12">
        <div class="tile">
            <div class="tile-body">
                <form class="row">
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
                </form>
                <%--button submit--%>
                <div class="tile-footer">
                    <div class="row" style="justify-content: center">

                        <div class="col-md-8 col-md-offset-3" style="text-align: center">
                            <button class="btn btn-primary" id="btn-ok-news-product" type="button"><i
                                    class="fa fa-fw fa-lg fa-check-circle"></i>Đồng ý
                            </button>&nbsp;&nbsp;&nbsp;<a class="btn btn-secondary" href="product"><i
                                class="fa fa-fw fa-lg fa-times-circle"></i>Cancel</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>

</main>
