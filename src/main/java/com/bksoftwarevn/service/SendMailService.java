package com.bksoftwarevn.service;

import com.bksoftwarevn.entities.user.UserMail;

public interface SendMailService {

    boolean sendEmail(UserMail userMail);
}