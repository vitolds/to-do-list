package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.springJPA.UserRepository;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.CoinService;
import lv.javaguru.java2.service.UserDTOService;
import lv.javaguru.java2.service.security.SecurityService;
import lv.javaguru.java2.servlet.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Vitolds on 12/23/2016.
 */
@Controller
public class ProfileController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDTOService userDTOService;

    @Autowired
    private CoinService coinService;

    @RequestMapping(value={"profile", "/"}, method={RequestMethod.GET})
    public ModelAndView processGet(HttpServletRequest req) {

        User user = userRepository.findByUsername(securityService.findLoggedInUsername());

        UserDTO userDTO = userDTOService.getUserDTO(user);

        return new ModelAndView("profilePage", "data", userDTO);
    }

    @RequestMapping(value={"profile", "/"}, method={RequestMethod.POST})
    public ModelAndView processPost(HttpServletRequest req) {
        String strSlotCount = req.getParameter("slotsToBuy");

        User user = userRepository.findByUsername(securityService.findLoggedInUsername());

        int slots;
        try {
            slots = Integer.parseInt(strSlotCount);
            coinService.buyTaskSlots(user, slots);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserDTO userDTO = userDTOService.getUserDTO(user);
        return new ModelAndView("profilePage", "data", userDTO);
    }
}
