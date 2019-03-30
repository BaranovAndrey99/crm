package crm.controller;

import crm.dao.Role;
import crm.dao.User;
import crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    UserRepository userRepository;

    /*
     * Get mapping for registration page.
     */
    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }

    /*
     * Handling of registration form and addind of new user. Regirect to login page.
     */
    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDb = userRepository.findUserByUsernickname(user.getUsernickname());
        if(userFromDb != null){
            model.put("message", "Пользователь с таким никнеймом уже существует!");
            return "registration";
        }
        user.setUseractive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }
}
