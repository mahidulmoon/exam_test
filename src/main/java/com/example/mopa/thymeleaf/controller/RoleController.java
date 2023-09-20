package com.example.mopa.thymeleaf.controller;

import com.example.mopa.thymeleaf.domain.RoleName;
import com.example.mopa.thymeleaf.dto.request.RoleRequest;
import com.example.mopa.thymeleaf.dto.response.RoleResponse;
import com.example.mopa.thymeleaf.dto.response.UserEntityResponseDTO;
import com.example.mopa.thymeleaf.services.RoleService;
import com.example.mopa.thymeleaf.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RoleService service;
    private final UserService loginRegistrationService;

    @GetMapping
    public ModelAndView showTelephoneView() {
        List<RoleResponse> responseDTOS = service.getAllRoleListList();
        return new ModelAndView("view-role").addObject("responseDTOS", responseDTOS);
    }

    @GetMapping("/add")
    public ModelAndView showCreateForm() {

        ModelAndView modelAndView = new ModelAndView();
        List<UserEntityResponseDTO> responseObject = loginRegistrationService.getAllUserList();
        List<RoleName[]> roleNames = new ArrayList<>();
        roleNames.add(RoleName.values());
        modelAndView.addObject("roleNameList", roleNames);
        modelAndView.addObject("responseObject", responseObject);
        modelAndView.addObject("addRole", new RoleRequest());
        modelAndView.setViewName("add-role");

        return modelAndView;
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("addRole") @Valid RoleRequest requestDTO, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "add-role";
        }

        service.createRole(requestDTO);
        attributes.addFlashAttribute("message", "Create Role.. successfully!");
        return "redirect:/role";
    }

    @GetMapping("/{id}")
    public ModelAndView showUpdateForm(@PathVariable String id) {
        RoleResponse responseDTO = service.getAllRoleById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("responseDTO", responseDTO);
        modelAndView.setViewName("edit-role");

        return modelAndView;
    }


    @PostMapping("/{id}")
    public String updateRole(@PathVariable String id, @ModelAttribute("responseDTO") @Valid RoleRequest roleRequest, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "edit-role";
        }

        service.updateRoleById(id, roleRequest);
        attributes.addFlashAttribute("message", "Role updated successfully!");
        return "redirect:/role";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes attributes) {
        service.deleteById(id);
        attributes.addFlashAttribute("message", "Role Info deleted successfully!");
        return "redirect:/role";
    }

}
