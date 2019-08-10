<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<aside class="app-sidebar">
    <div class="app-sidebar__user">
        <img class="app-sidebar__user-avatar" src="https://avatars3.githubusercontent.com/u/44569407?s=460&v=4"
             alt="User Image" width="30%">
        <div>
            <p class="app-sidebar__user-name">Cao Trần</p>
            <p class="app-sidebar__user-designation">Developer</p>
        </div>
    </div>
    <ul class="app-menu">
        <li><a class="app-menu__item" href="home"><i class="app-menu__icon fas fa-home"></i><span
                class="app-menu__label"> Trang chủ</span></a></li>

        <li><a class="app-menu__item" href="#"><i class="fas fa-shopping-cart"></i><span
                class="app-menu__label">&nbsp; Danh sách đơn hàng</span></a></li>

        <li><a class="app-menu__item" href="user"><i class="fas fa-users"></i></i><span
                class="app-menu__label">&nbsp; Quản lý user</span></a></li>


        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i
                class="app-menu__icon fab fa-product-hunt"></i><span class="app-menu__label">Sản phẩm</span><i
                class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li>
                    <a class="treeview-item" href="product">Thông tin sản phẩm</a>
                </li>

                <li>
                    <a class="treeview-item" href="tag"> Thẻ tag</a>
                </li>

            </ul>
        </li>

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
    </ul>
</aside>