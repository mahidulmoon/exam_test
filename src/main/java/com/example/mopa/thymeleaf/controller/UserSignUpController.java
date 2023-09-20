package com.example.mopa.thymeleaf.controller;

import com.example.mopa.thymeleaf.dto.request.RoleRequest;
import com.example.mopa.thymeleaf.dto.request.UserEntityRequestDTO;
import com.example.mopa.thymeleaf.dto.response.RoleResponse;
import com.example.mopa.thymeleaf.dto.response.UserEntityResponseDTO;
import com.example.mopa.thymeleaf.repository.UserRepository;
import com.example.mopa.thymeleaf.services.RoleService;
import com.example.mopa.thymeleaf.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/create-user")
public class UserSignUpController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping
    public ModelAndView showTelephoneView() {
        List<UserEntityResponseDTO> responseDTOList = userService.getAllUserList();
        return new ModelAndView("view-user").addObject("responseDTOS", responseDTOList);
    }

    @GetMapping("/sign-up")
    public ModelAndView showRegistrationView() {

        ModelAndView modelAndView = new ModelAndView();
        List<RoleResponse> responseDTOList = roleService.getAllRoleListList();

        modelAndView.addObject("roleRequestDTOList", responseDTOList);
        modelAndView.addObject("createUser", new UserEntityRequestDTO());
        modelAndView.setViewName("add-user");
        return modelAndView;
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("createUser") @Valid UserEntityRequestDTO requestDTO, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "add-user";
        }

        userService.createRegistration(requestDTO);
        attributes.addFlashAttribute("message", "Registration successfully!");
        return "redirect:/create-user";
    }

    @GetMapping("/{id}")
    public ModelAndView showUpdateForm(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();
        UserEntityResponseDTO responseDTO = userService.getAllUserByBuserId(id);
        List<RoleResponse> responseDTOList = roleService.getAllRoleListList();

        modelAndView.addObject("roleRequestDTOList", responseDTOList);
        modelAndView.addObject("responseDTO", responseDTO);
        modelAndView.setViewName("edit-user");

        return modelAndView;
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable String id, @ModelAttribute("responseDTO") @Valid UserEntityRequestDTO requestDTO, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "edit-user";
        }

        userService.updateUserByUserId(id, requestDTO);
        attributes.addFlashAttribute("message", "Role updated successfully!");
        return "redirect:/create-user";
    }


}
