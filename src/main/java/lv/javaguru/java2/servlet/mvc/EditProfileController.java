package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.springJPA.UserRepository;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.EditUserService;
import lv.javaguru.java2.service.UserDTOService;
import lv.javaguru.java2.service.security.SecurityService;
import lv.javaguru.java2.service.security.validator.ValidatorMessage;
import lv.javaguru.java2.servlet.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by vitol on 28/01/2017.
 */

@Controller
public class EditProfileController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SecurityService securityService;

    @Autowired
    EditUserService editUserService;

    @Autowired
    UserDTOService userDTOService;

    @RequestMapping(value = "profile/edit", method = RequestMethod.GET)
    public ModelAndView processGet() {

        User user = userRepository.findByUsername(securityService.findLoggedInUsername());
        UserDTO userDTO = userDTOService.getUserDTO(user);

        return new ModelAndView("profile_edit", "data", userDTO);
    }

    @RequestMapping(value = "profile/edit", method = RequestMethod.POST)
    public ModelAndView processPost(HttpServletRequest req) {

        User user = new User();
        user.setUsername(req.getParameter("username"));
        user.setEmail(req.getParameter("email"));
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setPassword(req.getParameter("password"));
        if ("true".equals(req.getParameter("visible"))) user.setVisible(true);
            else user.setVisible(false);

        ValidatorMessage validatorMessage = editUserService.updateUser(user);

        if (!validatorMessage.isSuccess()) return new ModelAndView("redirect", "data", "/java2/profile/edit"  + validatorMessage.getMessage());
            else return new ModelAndView("redirect", "data", "/java2/logout");
    }
}
