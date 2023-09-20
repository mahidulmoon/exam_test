package com.example.mopa.thymeleaf.controller;

import com.example.mopa.thymeleaf.dto.request.ContractRequestDTO;
import com.example.mopa.thymeleaf.dto.request.ContractUpdateDTO;
import com.example.mopa.thymeleaf.dto.response.ContractResponseDTO;
import com.example.mopa.thymeleaf.dto.response.OrgResponseDTO;
import com.example.mopa.thymeleaf.services.ContractService;
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
@RequestMapping("/contract")
public class ContractController {

    private final ContractService contractService;
    private final OrgService orgService;

    @GetMapping
    public ModelAndView showTelephoneView() {
        List<ContractResponseDTO> responseDTOList = contractService.getAllContractList();
        return new ModelAndView("view-contract").addObject("addContract", responseDTOList);
    }

    @GetMapping("/add")
    public ModelAndView showCreateForm() {

        ModelAndView modelAndView = new ModelAndView();
        List<OrgResponseDTO> orgRequestDTOList = orgService.getAllOrgList();

        modelAndView.addObject("orgRequestDTOList", orgRequestDTOList);
        modelAndView.addObject("addContract", new ContractRequestDTO());
        modelAndView.setViewName("add-contract");

        return modelAndView;
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("addContract") @Valid ContractRequestDTO requestDTO, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "add-contract";
        }

        contractService.createContract(requestDTO);
        attributes.addFlashAttribute("message", "Create Org.. successfully!");
        return "redirect:/contract";
    }

    @GetMapping("/{id}")
    public ModelAndView showUpdateForm(@PathVariable String id) {
        ContractResponseDTO responseDTO = contractService.getAllContractById(id);

        ModelAndView modelAndView = new ModelAndView();
        List<OrgResponseDTO> orgRequestDTOList = orgService.getAllOrgList();

        modelAndView.addObject("orgRequestDTOList", orgRequestDTOList);
        modelAndView.addObject("responseDTO", responseDTO);
        modelAndView.setViewName("edit-contract");


        return modelAndView;
    }

    @PostMapping("/{id}")
    public String updateContract(@PathVariable String id, @ModelAttribute("responseDTO") @Valid ContractUpdateDTO contractUpdateDTO, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "edit-contract";
        }

        contractService.updateOrganizationById(id, contractUpdateDTO);
        attributes.addFlashAttribute("message", "Contract Information updated successfully!");
        return "redirect:/contract";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes attributes) {
        contractService.deleteById(id);
        attributes.addFlashAttribute("message", "Contract Information deleted successfully!");
        return "redirect:/contract";
    }
}
