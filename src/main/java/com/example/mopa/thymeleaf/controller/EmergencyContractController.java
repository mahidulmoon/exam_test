package com.example.mopa.thymeleaf.controller;

import com.example.mopa.thymeleaf.dto.request.EmergencyContractRequestDTO;
import com.example.mopa.thymeleaf.dto.request.EmergencyContractUpdateDTO;
import com.example.mopa.thymeleaf.dto.response.EmergencyContractResponseDTO;
import com.example.mopa.thymeleaf.services.EmergencyContractService;
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
@RequestMapping("/emergency-contract")
public class EmergencyContractController {

    private final EmergencyContractService emergencyContractService;

    @GetMapping
    public ModelAndView showTelephoneView() {
        List<EmergencyContractResponseDTO> emergencyContractList = emergencyContractService.getAllEmergencyContractList();
        return new ModelAndView("view-emergency-contract").addObject("emergencyContract", emergencyContractList);
    }

    @GetMapping("/add")
    public ModelAndView showCreateForm() {
        return new ModelAndView("add-emergency-contract").addObject("emergencyContract", new EmergencyContractRequestDTO());
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("emergencyContract") @Valid EmergencyContractRequestDTO emergencyContractRequestDTO, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "add-emergency-contract";
        }

        emergencyContractService.createEmergencyContract(emergencyContractRequestDTO);
        attributes.addFlashAttribute("message", "Create Emergency Contract successfully!");
        return "redirect:/view-emergency-contract";
    }

    @GetMapping("/{id}")
    public ModelAndView showUpdateForm(@PathVariable String id) {
        EmergencyContractResponseDTO responseDTO = emergencyContractService.getAllEmergencyContractById(id);
        return new ModelAndView("edit-emergency-contract").addObject("responseDTO", responseDTO);
    }

    @PostMapping("/{id}")
    public String updateEmergencyContract(@PathVariable String id, @ModelAttribute("responseDTO") @Valid EmergencyContractUpdateDTO orgUpdateDTO, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "edit-emergency-contract";
        }

        emergencyContractService.updateEmergencyContractById(id, orgUpdateDTO);
        attributes.addFlashAttribute("message", "Emergency Contract updated successfully!");
        return "redirect:/view-emergency-contract";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes attributes) {
        emergencyContractService.deleteById(id);
        attributes.addFlashAttribute("message", "Emergency Contract deleted successfully!");
        return "redirect:/view-emergency-contract";
    }
}
