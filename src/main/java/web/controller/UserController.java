package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import web.service.UserService;
import web.model.User;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    private String allUsers(Model model) {
        model.addAttribute("userList", userService.allUsers());
        return "users";
    }

    @GetMapping(value = "/edit")
    public String GetEditPage(@RequestParam(name="id",required = true) int id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "editPage";
    }

    @PostMapping(value = "/edit")
    public String PostEditPage(@ModelAttribute("user") User user) {
        userService.edit(user);
        return "redirect:/";
    }

    @GetMapping(value = "/delete")
    private String GetDelete(@RequestParam(name="id",required = true) int id) {
        userService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping(value = "/add")
    public String GetAdd(Model model) {
        User user = userService.add();
        model.addAttribute("user", user);
        return "addPage";
    }

}
