package com.example.mopa.thymeleaf.controller;

import com.example.mopa.thymeleaf.dto.request.OrgRequestDTO;
import com.example.mopa.thymeleaf.dto.request.OrgUpdateDTO;
import com.example.mopa.thymeleaf.dto.response.OrgResponseDTO;
import com.example.mopa.thymeleaf.services.OrgService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/organization")
public class OrganizationController {

    private final OrgService orgService;

    @GetMapping
    public ModelAndView showTelephoneView() {
        List<OrgResponseDTO> responseDTOS = orgService.getAllOrgList();
        return new ModelAndView("view-organization").addObject("responseDTOS", responseDTOS);
    }

    @GetMapping("/add")
    public ModelAndView showCreateForm() {
        return new ModelAndView("add-organization").addObject("addOrg", new OrgRequestDTO());
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("addContract") @Valid OrgRequestDTO requestDTO, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "add-organization";
        }

        orgService.createOrg(requestDTO);
        attributes.addFlashAttribute("message", "Create Org.. successfully!");
        return "redirect:/view-organization";
    }

    @GetMapping("/{id}")
    public ModelAndView showUpdateForm(@PathVariable String id) {
        OrgResponseDTO responseDTO = orgService.getAllOrgById(id);
        return new ModelAndView("edit-organization").addObject("responseDTO", responseDTO);
    }

    @PostMapping("/{id}")
    public String updateOrganization(@PathVariable String id, @ModelAttribute("responseDTO") @Valid OrgUpdateDTO orgUpdateDTO, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "edit-organization";
        }

        orgService.updateOrganizationById(id, orgUpdateDTO);
        attributes.addFlashAttribute("message", "Org.. updated successfully!");
        return "redirect:/view-organization";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes attributes) {
        orgService.deleteById(id);
        attributes.addFlashAttribute("message", "Org.. deleted successfully!");
        return "redirect:/view-organization";
    }

}
