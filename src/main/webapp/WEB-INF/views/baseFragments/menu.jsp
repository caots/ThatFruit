<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<aside class="app-sidebar">
    <div class="app-sidebar__user">
        <img class="app-sidebar__user-avatar"
             src="resources/img/logothatfruit.png"
             alt="User Image" width="30%" style="background: #eff7ff">
        <div>
            <p class="app-sidebar__user-name" style="padding-left: 20px;">Thật Fruit</p>
        </div>
    </div>
    <ul class="app-menu">
        <li><a class="app-menu__item" href="home"><i class="app-menu__icon fas fa-home"></i><span
                class="app-menu__label"> Trang chủ</span></a></li>

        <li><a class="app-menu__item" href="buy-form"><i class="fas fa-shopping-cart"></i><span
                class="app-menu__label">&nbsp; Danh sách đơn hàng</span></a></li>

        <li><a class="app-menu__item" href="user"><i class="fas fa-users"></i></i><span
                class="app-menu__label">&nbsp; Quản lý user</span></a></li>

        <li><a class="app-menu__item" href="product"><i class="fab fa-product-hunt"></i></i><span
                class="app-menu__label">&nbsp; Sản phẩm</span></a></li>

        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i
                class="app-menu__icon fas fa-sitemap"></i><span class="app-menu__label">Chuyên mục</span><i
                class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li>
                    <a class="treeview-item" href="menu-category"> Danh mục</a>
                </li>
                <li>
                    <a class="treeview-item" href="big-category" rel="noopener"></i> Danh mục sản phẩm</a>
                </li>
                <li>
                    <a class="treeview-item" href="small-category"></i> Loại sản phẩm</a>
                </li>
                <li>
                    <a class="treeview-item" href="image-page"></i> Ảnh trang chủ</a>
                </li>

            </ul>
        </li>

        <li><a class="app-menu__item" href="form-contact"><i class="fab fa-wpforms"></i><span
                class="app-menu__label">&nbsp; Form khách hàng</span></a></li>

        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview">
            <i class="far fa-newspaper"></i><span class="app-menu__label">&nbsp;Bài viết</span><i
                class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li>
                    <a class="treeview-item" href="topic">Danh mục tiêu đề</a>
                </li>
                <li>
                    <a class="treeview-item" href="news" rel="noopener"></i> Danh mục bài viết</a>
                </li>
            </ul>
        </li>

        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i
                class="app-menu__icon fab fas fa-building"></i><span class="app-menu__label">Công ty</span><i
                class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li>
                    <a class="treeview-item" href="company"> Thông tin công ty</a>
                </li>

                <li>
                    <a class="treeview-item" href="contact"> Thông tin liên kết</a>
                </li>

                <li>
                    <a class="treeview-item" href="partner"> Thông tin đối tác</a>
                </li>

            </ul>
        </li>

        <li><a class="app-menu__item" href="unlock"><i class="fas fa-unlock"></i><span
                class="app-menu__label">&nbsp; Khóa màn hình</span></a></li>
    </ul>
</aside>