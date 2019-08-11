package com.bksoftwarevn.controller.main.change;

import com.bksoftwarevn.controller.main.admin.HomeAdminController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ChangePageController {

    public String getToken(HttpServletRequest request) {
        HomeAdminController homeAdminController = new HomeAdminController();
        String token = homeAdminController.getToken(request);
        return token;
    }

    //=========================Category=================================
    @GetMapping({"/create-menu", "/create-big", "/create-small", "/create-category"})
    public String createCategoryPage(HttpServletRequest request) {


        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "create-category";
    }

    @GetMapping("/update-category")
    public String updateCategoryPage(
            @RequestParam(value = "menu-id", required = false, defaultValue = "-1") int menuId,
            @RequestParam(value = "big-id", required = false, defaultValue = "-1") int bigId,
            @RequestParam(value = "small-id", required = false, defaultValue = "-1") int smallId,
            HttpServletRequest request
    ) {
        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "update-category";
    }


    //=========================Product=================================
    @GetMapping("/create-product")
    public String createProductPage(HttpServletRequest request) {


        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "create-product";
    }


    @GetMapping("/update-product")
    public String updateProductPage(
            @RequestParam("id") int id,
            HttpServletRequest request
    ) {
        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "update-product";
    }

    //=========================Tag=================================
    @GetMapping("/create-tag")
    public String createTagPage(HttpServletRequest request) {


        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "create-tag";
    }


    @GetMapping("/update-tag")
    public String updateTagPage(
            @RequestParam("id") int id,
            HttpServletRequest request
    ) {
        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "update-tag";
    }

    //=========================Company=================================
    @GetMapping("/create-company")
    public String createCompanyPage(HttpServletRequest request) {


        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "create-company";
    }


    @GetMapping("/update-company")
    public String updateCompanyPage(
            @RequestParam("id") int id,
            HttpServletRequest request
    ) {
        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "update-company";
    }

    //=========================Contact=================================
    @GetMapping("/create-contact")
    public String createContactPage(HttpServletRequest request) {


        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "create-contact";
    }


    @GetMapping("/update-contact")
    public String updateContactPage(
            @RequestParam("id") int id,
            HttpServletRequest request
    ) {
        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "update-contact";
    }

    //=========================Contact=================================

    @GetMapping("/send-mail")
    public String sendMailPage(HttpServletRequest request) {


        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "send-mail";
    }

    @GetMapping("/send-mail-form")
    public String sendMailFormPage(
            @RequestParam("id") int id,
            HttpServletRequest request
    ) {
        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "send-mail-form";
    }

    @GetMapping("/create-partner")
    public String createPartnerPage(HttpServletRequest request) {


        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "create-partner";
    }


    @GetMapping("/update-partner")
    public String updatePartnerPage(
            @RequestParam("id") int id,
            HttpServletRequest request
    ) {
        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "update-partner";
    }

    @GetMapping("/update-image-page")
    public String updateImagePage(
            @RequestParam("id") int id,
            HttpServletRequest request
    ) {
        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "update-image-page";
    }

    @GetMapping("/create-topic")
    public String createTopicPage(HttpServletRequest request) {


        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "create-topic";
    }


    @GetMapping("/update-topic")
    public String updateTopicPage(
            @RequestParam("topic-id") int id,
            HttpServletRequest request
    ) {
        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "update-topic";
    }

    @GetMapping("/create-news")
    public String createNewsPage(HttpServletRequest request) {


        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "create-news";
    }


    @GetMapping("/update-news")
    public String updateNewsPage(
            @RequestParam("news-id") int id,
            HttpServletRequest request
    ) {
        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "update-news";
    }


}
