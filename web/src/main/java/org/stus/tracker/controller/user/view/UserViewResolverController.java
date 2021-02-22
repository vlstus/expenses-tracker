package org.stus.tracker.controller.user.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.stus.tracker.controller.GenericController;
import org.stus.tracker.service.user.UserService;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserViewResolverController extends GenericController {

    private final UserService userService;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users", userService.getAll());
        return modelAndView;
    }

}
