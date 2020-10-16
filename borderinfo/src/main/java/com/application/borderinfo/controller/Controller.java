package com.application.borderinfo.controller;

import com.application.borderinfo.dto.UserDataDto;
import com.application.borderinfo.dto.UserDto;
import com.application.borderinfo.entity.Border;
import com.application.borderinfo.entity.User;
import com.application.borderinfo.repository.BorderRepository;
import com.application.borderinfo.repository.UserRepository;
import com.application.borderinfo.security.UserDetailsServiceImpl;
import com.application.borderinfo.service.BorderDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private BorderDataService borderDataService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BorderRepository borderRepository;

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
    public String registration(){
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
    @PostMapping(value="/filterUa")
    public String filterUa(Model model)
    {
        final UserDto userDto = UserDetailsServiceImpl.getLoggedInUserDetails();
        final UserDataDto userDataDto = new UserDataDto(userDto.getUsername());

        model.addAttribute("userData", userDataDto);
        model.addAttribute("borders", borderDataService.indexUaBorders());
        return "index";
    }
    @PostMapping(value="/filterHu")
    public String filterHu(Model model)
    {
        final UserDto userDto = UserDetailsServiceImpl.getLoggedInUserDetails();
        final UserDataDto userDataDto = new UserDataDto(userDto.getUsername());

        model.addAttribute("userData", userDataDto);
        model.addAttribute("borders", borderDataService.indexHuBorders());
        return "index";
    }
    @GetMapping(value="/index")
    public String Index(Model model)
    {
        final UserDto userDto = UserDetailsServiceImpl.getLoggedInUserDetails();
        final UserDataDto userDataDto = new UserDataDto(userDto.getUsername());

        model.addAttribute("borders", borderDataService.indexBorders());
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
        model.addAttribute("borders",borderDataService.checkBorders());
        return "border";
    }

    @PostMapping(value = "/addborder")
    public String registration(@RequestParam(value = "name", required = true) final String name,
                               @RequestParam(value = "cars", required = true) final int cars,
                               @RequestParam(value = "waiting_time", required = true)  String waiting_time,
                               Model model) {
        final UserDto userDto = UserDetailsServiceImpl.getLoggedInUserDetails();
        final Optional<User> optionalUser = userRepository.findByUsername(userDto.getUsername());
        final User user = optionalUser.get();
        boolean hu = false; // ha false, akkor UA felé, ha true, akkor HU felé
        if(name.equals("Beregsurány -> Asztély") || name.equals("Barabás -> Kaszony") || name.equals("Lónya -> Harangláb")
                || name.equals("Záhony -> Csap") || name.equals("Tiszabecs -> Tiszaújlak"))
        {
            hu = true;
        }
        Border border = new Border(user.getId(),user.getUsername(), name, cars, waiting_time,new Date(),hu);
        borderRepository.save(border);
        model.addAttribute("border",border);
        model.addAttribute("info","Köszönjük az információt!");
        model.addAttribute("borders", borderDataService.indexBorders());

        return "index";
    }
    @PostMapping(value="/borderinfos")
    public String borderinfos(@RequestParam(value = "bordername", required = true) final String bordername,
                              Model model)
    {
        if(!borderDataService.checkBorders().contains(bordername))
        {
            return "/index";
        }
        else
            {
            List<Border> borders = borderRepository.findbyBorderNamebyDate(bordername);
            if(borders.size() >= 5) {
                borders = borders.subList(0, 5);
            }

            model.addAttribute("borders", borders);
            return "/borderinfos";
        }
    }
    @GetMapping(value="my_borders")
    public String my_borders(Model model)
    {

        return "my_borders";
    }
}
