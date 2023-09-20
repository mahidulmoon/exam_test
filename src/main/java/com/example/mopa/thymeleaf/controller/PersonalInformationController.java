package com.example.mopa.thymeleaf.controller;

import com.example.mopa.thymeleaf.dto.request.*;
import com.example.mopa.thymeleaf.dto.response.*;
import com.example.mopa.thymeleaf.services.ContractService;
import com.example.mopa.thymeleaf.services.OrgService;
import com.example.mopa.thymeleaf.services.PersonalInfoService;
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
@RequestMapping("/personal-info")
public class PersonalInformationController {

    private final PersonalInfoService service;

    @GetMapping
    public ModelAndView showTelephoneView() {
        List<PersonalInfoResponseDTO> responseDTOList = service.getAllInformation();
        return new ModelAndView("view-personal-info").addObject("addPersonalInfo", responseDTOList);
    }

    @GetMapping("/add")
    public ModelAndView showCreateForm() {
        return new ModelAndView("add-personal-info").addObject("addPersonalInfo", new PersonalInfoRequestDTO());
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("addPersonalInfo") @Valid PersonalInfoRequestDTO requestDTO, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "add-personal-info";
        }

        service.createPersonalInfo(requestDTO);
        attributes.addFlashAttribute("message", "Create Personal Information.. successfully!");
        return "redirect:/personal-info";
    }

    @GetMapping("/{id}")
    public ModelAndView showUpdateForm(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();
        PersonalInfoResponseDTO responseDTO = service.getPersonalInfoBy(id);
        modelAndView.addObject("responseDTO", responseDTO);
        modelAndView.setViewName("edit-personal-info");
        return modelAndView;
    }

    @PostMapping("/{id}")
    public String updatePersonalInfoBy(@PathVariable String id, @ModelAttribute("responseDTO") @Valid PersonalInfoRequestDTO requestDTO, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "edit-personal-info";
        }

        service.updatePersonalInfoBy(id, requestDTO);
        attributes.addFlashAttribute("message", "Personal Information updated successfully!");
        return "redirect:/personal-info";
    }

    @GetMapping("/fixeduser/{id}")
    public ModelAndView showFixedUserUpdateForm(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();
        PersonalInfoResponseDTO responseDTO = service.getPersonalInfoBy(id);
        modelAndView.addObject("responseDTO", responseDTO);
        modelAndView.setViewName("edit-personal-info-comment");
        return modelAndView;
    }


    @PostMapping("/fixeduser/{id}")
    public String updatefixeduserPersonalInfoBy(@PathVariable String id, @ModelAttribute("responseDTO") @Valid PersonalInfoRequestDTO requestDTO, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "edit-personal-info-comment";
        }

        service.updatePersonalInfoBy(id, requestDTO);
        attributes.addFlashAttribute("message", "Personal Information updated successfully!");
        return "redirect:/personal-info";
    }

    @GetMapping("/view-prl")
    public ModelAndView showPRLForm() {
        List<PersonalInfoResponseDTO> responseDTOList = service.getAllPRLInformation();
        return new ModelAndView("view-prl-info").addObject("addPersonalInfo", responseDTOList);
    }


}
