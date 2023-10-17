package com.example.servingwebcontent;
import jakarta.servlet.http.HttpServletRequest;
import mongo.User;
import mongo.UserItem;
import mongo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class LoginController {
    @Autowired
    UserRepository userItemRepo;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/loginHandle")
    public String  longinHandle(@RequestParam() String username, String password, Model model, HttpServletRequest request) {
        UserItem user = userItemRepo.findUserByUsername(username);

        if (user == null) {
            model.addAttribute("failed", "account does not exists");
           return  "login";
        } else if (!Objects.equals(user.getPassword(), password)) {
            model.addAttribute("failed", "Incorrect password");
            return  "login";

        } else if (Objects.equals(user.getPassword(), password)) {
            User.username=user.getUsername();

            return "redirect:/todo";
        }

        //simply
        return "/";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/registerHandle")
    public String registerHandle(@RequestParam(defaultValue = "nagaraj") String FName, String username, String password, Model model) {
        UserItem user = userItemRepo.findUserByUsername(username);
        if (user == null) {
            long id = userItemRepo.count();
            user = createUser(FName, username, password, id);
            System.out.println("user registration success");

            //
            User.username=user.getUsername();
            model.addAttribute("Name",user.getName());
            return "todo";
        } else {
            System.out.println("user already exist");
            model.addAttribute("failed", "User already exists");
            return "register";
        }


    }

    @GetMapping("/memories")
    public String memories() {
        return "memories";
    }


    @GetMapping

    public UserItem createUser(String name, String username, String password, long id) {
        System.out.println("User creation started..........");
        UserItem user_ = new UserItem(name, password, username, id);
        userItemRepo.save(user_);
        System.out.println("User creation done........");
        return user_;
    }


}
