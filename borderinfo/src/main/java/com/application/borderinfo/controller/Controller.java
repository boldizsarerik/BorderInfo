package com.application.borderinfo.controller;

import com.application.borderinfo.dto.UserDataDto;
import com.application.borderinfo.dto.UserDto;
import com.application.borderinfo.entity.Border;
import com.application.borderinfo.entity.User;
import com.application.borderinfo.repository.UserRepository;
import com.application.borderinfo.security.UserDetailsServiceImpl;
import com.application.borderinfo.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value="/login")
    public String Login_Register()
    {
        final UserDto userDto = UserDetailsServiceImpl.getLoggedInUserDetails();
        if (userDto != null) {
            return "redirect:/index";
        }
        return "login";
    }
    @GetMapping(value="/admin")
    public String admin()
    {
        return "admin";
    }
    @GetMapping(value="my_borders")
    public String my_borders()
    {
        return "my_borders";
    }
    @GetMapping(value="/statistics")
    public String statistics()
    {
        return "statistics";
    }
    @GetMapping(value="/my_profile")
    public String my_profile()
    {
        return "my_profile";
    }
    @GetMapping("/registration")
    public String registration(Model model){
        return "registration";
    }
    @GetMapping(value="/")
    public String index()
    {
        final UserDto userDto = UserDetailsServiceImpl.getLoggedInUserDetails();
        if (userDto != null) {
            return "redirect:/index";
        }
        return "redirect:/login";
    }
    @GetMapping(value="/index")
    public String Index(Model model)
    {
        final UserDto userDto = UserDetailsServiceImpl.getLoggedInUserDetails();
        final UserDataDto userDataDto = new UserDataDto(userDto.getUsername());

        model.addAttribute("userData", userDataDto);
        return "index";
    }
    @PostMapping(value = "/register")
    public String registration(@RequestParam(value = "username", required = true) final String username,
                               @RequestParam(value = "password", required = true) final String password,
                               Model model) {
        if (userRepository.findByUsername(username).isPresent()) {
            model.addAttribute("exists","Username already exists!");
            return "/login";
        }
        final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        final String pass = passwordEncoder.encode(password);
        User user = new User(username, pass, "ROLE_USER", new Date(), true);
        userRepository.save(user);
        model.addAttribute("registered","Registered successfuly, please log in");

        return "/login";
    }
    @GetMapping(value="/delete")
    public String Delete()
    {
        final UserDto userDto = UserDetailsServiceImpl.getLoggedInUserDetails();
        if (userDto != null)
        {
            final Optional<User> optionalUser = userRepository.findByUsername(userDto.getUsername());
            final User user = optionalUser.get();
            user.setActive(false);
            userRepository.save(user);
        }
        return "redirect:/logout";
    }


    @GetMapping(value="/border")
    public String border(Model model)
    {
        model.addAttribute(new Border());
        return "border";
    }
    @PostMapping("/border")
    public String border(@ModelAttribute Border border, Model model) {
        model.addAttribute("border", border);
        return "border";
    }
}
