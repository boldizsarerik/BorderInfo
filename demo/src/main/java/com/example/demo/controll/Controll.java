package com.example.demo.controll;
import ch.qos.logback.classic.Logger;
import com.example.demo.domain.Border;
import com.example.demo.domain.User;
import com.example.demo.services.Services;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class Controll
{
    private final Logger log = (Logger) LoggerFactory.getLogger(this.getClass());

    private Services borderService;

    private Controll(Services borderService) {}

    @Autowired
    public void setBorderService(Services borderService)
    {
        this.borderService = borderService;
    }

    @GetMapping(value="/login")
    public String login_register()
    {
        return "login";
    }
    @GetMapping(value="/admin")
    public String admin()
    {
        return "admin";
    }
    @GetMapping(value="/")
    public String index(Model model)
    {
        return "index";
    }
    @GetMapping(value="/my_borders")
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
    @GetMapping(value="/border")
    public String border(Model model)
    {
        model.addAttribute("border_infos",borderService.getBorder());
        return "border";
    }
    // Nem mindegy, ha name volt, akkor mindenütt name kell legyen tovább is.
    @RequestMapping("/title/{name}")
    public String searchForUser(@PathVariable(value = "name") String name, Model model) throws Exception
    {
        if (name == null)
            throw new Exception("Nincs ilyen nevű határ!");
        model.addAttribute("border_infos", borderService.getSpecificBorder(name));
        return "one_border";
    }

    @RequestMapping("/user/{name}")
    public String searchForUsersBorders(@PathVariable(value = "name") String name, Model model) throws Exception
    {
        if (name == null)
            throw new Exception("Nincs ilyen nevű felhasználó!");
        model.addAttribute("border_infos", borderService.getBordersByUserName(name));
        return "border";
    }

    @RequestMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    //	@RequestMapping(value = "/reg", method = RequestMethod.POST)
    @PostMapping("/reg")
    public String greetingSubmit(@ModelAttribute User user) {
        System.out.println("UJ USER");
        log.info("Uj user!");
		log.debug(user.getUsername());
		log.debug(user.getPassword());
        return "auth/login";
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(HttpServletRequest rA, Exception ex, Model model)
    {
        model.addAttribute("errMessage", ex.getMessage());
        return "exceptionHandler";
    }
}
