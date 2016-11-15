package lv.javaguru.java2.service;

import lv.javaguru.java2.domain.User;

/**
 * Created by Vitolds on 11/14/2016.
 */
public interface LoginService {

    User authenticate(String login, String password);
}
