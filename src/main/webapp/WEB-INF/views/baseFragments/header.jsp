<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Navbar-->
<header class="app-header"><a class="app-header__logo" href="home">
    <%--<img src="resources/img/bksoftwareLogo.png" alt="" class="img-fluid">--%>
    BkSoftware
</a>
    <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar"
                                    aria-label="Hide Sidebar"></a>
    <!-- Navbar Right Menu-->
    <ul class="app-nav">
        <!-- User Menu-->
        <li class="dropdown"><a class="app-nav__item" href="#" data-toggle="dropdown" aria-label="Open Profile Menu"><i
                class="fa fa-user fa-lg"></i></a>
            <ul class="dropdown-menu settings-menu dropdown-menu-right">
                <li><a class="dropdown-item" href="page-user.html"><i class="fa fa-cog fa-lg"></i> Settings</a></li>
                <li><a class="dropdown-item" href="page-user.html"><i class="fa fa-user fa-lg"></i> Profile</a></li>
                <li><a class="dropdown-item" href="login"><i class="fa fa-sign-out fa-lg"></i> Logout</a></li>
            </ul>
        </li>
    </ul>
</header>
<script src="resources/js/template/jquery-3.2.1.min.js"></script>
<script src="resources/js/template/popper.min.js"></script>
<script src="resources/js/template/bootstrap.min.js"></script>
<script src="resources/js/template/main.js"></script>
<!-- The javascript plugin to display page loading on top-->
<script src="resources/js/template/plugins/pace.min.js"></script>
<script type="text/javascript" src="resources/js/template/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="resources/js/template/plugins/dataTables.bootstrap.min.js"></script>
