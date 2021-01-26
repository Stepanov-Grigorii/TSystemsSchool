package ru.grandstep.logiweb.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.grandstep.logiweb.model.Admin;
import ru.grandstep.logiweb.model.User;
import ru.grandstep.logiweb.service.UserDetailServiceImpl;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String login() {
        return "common/login";
    }

    @GetMapping("/main")
    public RedirectView mainPage() {
        User user = ((UserDetailServiceImpl.UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        return user instanceof Admin ? new RedirectView("admin/drivers/list") : new RedirectView("user/drivers/info/" + user.getId());
    }
}
