package com.bksoftwarevn.controller.main.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = {"/", "/login", "/logout"}, method = RequestMethod.GET)
    public String pageLogin(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
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

    @RequestMapping(value = {"/product"}, method = RequestMethod.GET)
    public String productPage(HttpServletRequest request) {

        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "product";
    }

    @RequestMapping(value = {"/tag"}, method = RequestMethod.GET)
    public String tagPage(HttpServletRequest request) {

        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "tag";
    }

    @RequestMapping(value = {"/company"}, method = RequestMethod.GET)
    public String companyPage(HttpServletRequest request) {

        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "company";
    }

    @RequestMapping(value = {"/contact"}, method = RequestMethod.GET)
    public String contactPage(HttpServletRequest request) {

        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "contact";
    }

    @RequestMapping(value = {"/partner"}, method = RequestMethod.GET)
    public String partnerPage(HttpServletRequest request) {

        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "partner";
    }

    @RequestMapping(value = {"/form-contact"}, method = RequestMethod.GET)
    public String formContactPage(HttpServletRequest request) {

        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "form-contact";
    }

    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public String userPage(HttpServletRequest request) {

        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "user";
    }

    @RequestMapping(value = {"/image-page"}, method = RequestMethod.GET)
    public String imagePagePage(HttpServletRequest request) {

        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "image-page";
    }

    @RequestMapping(value = {"/profile"}, method = RequestMethod.GET)
    public String profilePage(HttpServletRequest request) {

        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "profile-admin";
    }

    @RequestMapping(value = {"/topic"}, method = RequestMethod.GET)
    public String topicPage(HttpServletRequest request) {

        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "topic";
    }

    @RequestMapping(value = {"/news"}, method = RequestMethod.GET)
    public String newsPage(HttpServletRequest request) {

        String username = getToken(request);
        if (username == null) {
            return "login";
        }
        return "news";
    }

    @RequestMapping(value = {"/unlock"}, method = RequestMethod.GET)
    public String unlockPage(HttpServletRequest request) {

        String username = getToken(request);
        if (username == null) {
            return "login";
        }
        return "unlock";
    }

    @RequestMapping(value = {"/image-product"}, method = RequestMethod.GET)
    public String imageProductPage(HttpServletRequest request, @RequestParam("product-id") int id) {

        String username = getToken(request);
        if (username == null) {
            return "login";
        }
        return "image-product";
    }

    @RequestMapping(value = {"/send-mail-product"}, method = RequestMethod.GET)
    public String sendEmailPage(HttpServletRequest request, @RequestParam("buyform-id") int id) {

        String username = getToken(request);
        if (username == null) {
            return "login";
        }
        return "send-mail-product";
    }


    @GetMapping("/form-send-email")
    public String sendMailFormPage(
            @RequestParam("id") int id,
            HttpServletRequest request
    ) {
        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "form-send-email";
    }

    @GetMapping("/content-form-contact")
    public String contentFormMailFormPage(
            @RequestParam("id") int id,
            HttpServletRequest request
    ) {
        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "content-form-contact";
    }

    @GetMapping("/buy-form")
    public String buyFormPage(
            HttpServletRequest request
    ) {
        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "buy-form";
    }


}
