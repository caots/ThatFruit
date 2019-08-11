<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@include file="../../library/library_css.jsp" %>
<!-- Font-icon css-->
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<link rel="stylesheet" type="text/css"
      href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<title>login</title>
<!-- ajax login -->
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js">

</script>
<script src="resources/js/ajax/common.js"></script>

<section class="material-half-bg">
    <div class="cover"></div>
</section>
<section class="lockscreen-content">
    <div class="logo">
        <img src="https://admin.haphatsmarthome.com/resources/img/login/bksoftwareLogo.png"
             alt="" class="img-fluid">
    </div>
    <div class="lock-box"><img class="rounded-circle user-image" src="https://avatars3.githubusercontent.com/u/44569407?s=460&v=4">
        <h4 class="text-center user-name">Ông chủ</h4>
        <p class="text-center text-muted">Account Locked</p>
        <div class="unlock-form">
            <div class="form-group">
                <label class="control-label">PASSWORD</label>
                <input class="form-control" id="password-unlock" type="password" placeholder="Password" autofocus>
            </div>
            <div class="form-group btn-container">
                <button class="btn btn-primary btn-block" type="submit"><i class="fa fa-unlock fa-lg"></i>UNLOCK
                </button>
            </div>
        </div>
        <p><a href="login">Not Boss ? Login Here.</a></p>
    </div>
</section>
<%@include file="../../library/libraby_js.jsp" %>
<script>
    $(document).ready(function () {
        var passwordX = $('#password-unlock').val();
        $(".btn-block").click(function () {
            if (passwordX === passwordAdmin) {
                location.href = "home";
            }
        })
    });
</script>

