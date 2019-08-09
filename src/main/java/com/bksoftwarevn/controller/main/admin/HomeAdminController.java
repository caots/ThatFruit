package com.bksoftwarevn.controller.main.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeAdminController {

    private String token;

    public String getToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            System.out.println(cookies[i].getName());
            if (cookies[i].getName().equals("token")) {
                token = cookies[i].getValue();
            }
        }

        return token;
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String pageLogin(HttpServletRequest request) {
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookies[i].setMaxAge(0);
            }
        }
        return "login";
    }

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String homePage(HttpServletRequest request) {

        String token = getToken(request);

        if (token == null) {
            return "login";
        }
        return "home";
    }

    @RequestMapping(value = {"/menu-category"}, method = RequestMethod.GET)
    public String menuPage(HttpServletRequest request) {

        String token = getToken(request);

        if (token == null) {
            return "login";
        }
        return "menu_category";
    }

    @RequestMapping(value = {"/big-category"}, method = RequestMethod.GET)
    public String bigCategoryPage(HttpServletRequest request) {

        String token = getToken(request);

        if (token == null) {
            return "login";
        }
        return "big-category";
    }

    @RequestMapping(value = {"/small-category"}, method = RequestMethod.GET)
    public String smallCategoryPage(HttpServletRequest request) {

        String token = getToken(request);

        if (token == null) {
            return "login";
        }
        return "small-category";
    }

}
