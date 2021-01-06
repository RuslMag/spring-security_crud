package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "/login";
    }

    @GetMapping(value = "/")
    public String getMainPage() {
        return "/index";
    }

}
