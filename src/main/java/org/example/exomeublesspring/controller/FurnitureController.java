package org.example.exomeublesspring.controller;

import org.example.exomeublesspring.dao.FurnitureRepository;
import org.example.exomeublesspring.entity.Furniture;
import org.example.exomeublesspring.service.FurnitureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FurnitureController {

    private final FurnitureService furnitureService;

    public FurnitureController(FurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("furnitures", furnitureService.findAll());
        return "home";
    }

    @GetMapping("/addFurniture")
    public String addFurniture(Model model) {
        model.addAttribute("furniture", new Furniture());
        return "formFurniture";
    }

    @PostMapping("/addFurniture")
    public String addFurniture(@ModelAttribute("furniture") Furniture furniture, Model model) {
        furnitureService.save(furniture);
        model.addAttribute("furnitures", furnitureService.findAll());
        return "redirect:/";
    }

    @GetMapping("/update")
    public String updateFurniture(@RequestParam("id") int id, Model model) {
        Furniture furniture = furnitureService.findById(id);
        model.addAttribute("furniture", furniture);
        return "formFurniture";
    }

    @GetMapping("/deletefourniture")
    public String deleteFurniture(@RequestParam("id") int id) {
//        furnitureService.findById(id);
        furnitureService.deleteById(id);
        return "redirect:/";
    }
}