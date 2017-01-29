package lv.javaguru.java2.service;

import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.dto.UserDTO;

/**
 * Created by vitol on 29/01/2017.
 */
public interface UserDTOService {

    UserDTO getUserDTO(User user);
    User getUser(UserDTO userDTO);
}
