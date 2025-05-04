package com.codeid.eshopper.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import com.codeid.eshopper.Service.ShipperService;
import com.codeid.eshopper.model.Category;
import com.codeid.eshopper.model.Shippers;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("shipper")
public class ShipperController {

    private final ShipperService shipperService;

    public ShipperController(ShipperService shipperService) {
        this.shipperService = shipperService;
    }

    @GetMapping("/index")
    public ModelAndView showShipper() {
        ModelAndView view = new ModelAndView("/shipper/index");
        view.addObject("shippers", shipperService.getAllShipper());
        return view;
    }

    @GetMapping("/add")
    public ModelAndView addForm() {
        ModelAndView view = new ModelAndView("/shipper/addForm");
        view.addObject("shipper", new Shippers());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("shipper") Shippers shippers, BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return new ModelAndView("/shipper/addForm");
        }
        if (shippers.getShipperId() == null) {

            shipperService.addShippers(shippers);
            redirectAttributes.addFlashAttribute("message", "data shipper berhasil ditambahkan");
        } else {

            Optional<Shippers> existing = shipperService.findShipperById(shippers.getShipperId());
            if (existing.isPresent()) {
                Shippers updated = existing.get();
                updated.setCompanyName(shippers.getCompanyName());
                updated.setPhone(shippers.getPhone());
                shipperService.addShippers(updated);
                redirectAttributes.addFlashAttribute("message", "data shipper berhasul di update");
            }
        }
        return new ModelAndView("redirect:/shipper/index");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView view = new ModelAndView("/shipper/addForm");
        shipperService.findShipperById(id).ifPresent(shipper -> view.addObject("shipper", shipper));
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        shipperService.deleteShipperById(id);

        redirectAttributes.addFlashAttribute("message", "data shipper berhasil dihapus");
        return new ModelAndView("redirect:/shipper/index");
    }

}
