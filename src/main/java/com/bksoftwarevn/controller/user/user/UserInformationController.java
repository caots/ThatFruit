package com.bksoftwarevn.controller.user.user;

import com.bksoftwarevn.commom.MD5;
import com.bksoftwarevn.commom.Token;
import com.bksoftwarevn.entities.Record;
import com.bksoftwarevn.entities.category.BigCategory;
import com.bksoftwarevn.entities.product.BuyForm;
import com.bksoftwarevn.entities.product.BuyFormCart;
import com.bksoftwarevn.entities.product.BuyFormHasProduct;
import com.bksoftwarevn.entities.user.AppUser;
import com.bksoftwarevn.service.product.BuyFormService;
import com.bksoftwarevn.service.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user/user")
@RolesAllowed("ROLE_USER")
public class UserInformationController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private BuyFormService buyFormService;

    @GetMapping("/my-profile")
    public ResponseEntity<AppUser> myProfile(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String username = request.getUserPrincipal().getName();
        if (username == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        AppUser appUser = appUserService.findByEmail(username);
        if (appUser == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }

    @GetMapping("/find-by-email")
    public ResponseEntity<AppUser> myProfileByEmail(
            HttpServletResponse response,
            @RequestParam("email") String email
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        AppUser appUser = appUserService.findByEmail(email);
        if (appUser != null) {
            return new ResponseEntity<>(appUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<Object> changePassword(
            @RequestParam("old") String oldPassword,
            @RequestParam("new") String newPassword,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String username = request.getUserPrincipal().getName();
        if (username == null) return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        AppUser appUser = appUserService.findByEmail(username);
        if (appUser == null) return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        if (!appUser.getPassword().equals(MD5.encode(oldPassword)))
            return new ResponseEntity<>("password is not correct", HttpStatus.BAD_REQUEST);
        appUser.setPassword(MD5.encode(newPassword));
        if (appUserService.saveAppUser(appUser))
            return new ResponseEntity<>(appUser, HttpStatus.OK);
        else return new ResponseEntity<>("change password error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/change-profile")
    public ResponseEntity<Object> changeProfile(
            @RequestBody AppUser appUser,
            HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        if (appUserService.saveAppUser(appUser))
            return new ResponseEntity<>(appUser, HttpStatus.OK);
        else return new ResponseEntity<>("change profile error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<Object> getProfile(
            @PathVariable int id,
            HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        AppUser appUser = appUserService.findById(id);
        if (appUser == null) return new ResponseEntity<>("no user found", HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(appUser, HttpStatus.OK);
    }

    @GetMapping("/buy-form")
    public ResponseEntity<List<BuyFormCart>> findAllBuyFormByUser(
            @RequestParam("user-id") int userId,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size
    ) {
        if (page < 1) page = 1;
        if (size < 0) size = 0;
        Pageable pageable = PageRequest.of(page - 1, size);
        List<BuyFormCart> buyForms = buyFormService.findAllBuyFormByUserPage(userId, pageable);
        if (buyForms != null) {
            return new ResponseEntity<>(buyForms, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/size")
    public ResponseEntity<Double> pageNumberMediumCategory(
            @RequestParam("user-id") int userId,
            HttpServletResponse response
    ) {
        double result = Math.ceil((double) buyFormService.sizeOfBuyFormByUser(userId) / 10);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
