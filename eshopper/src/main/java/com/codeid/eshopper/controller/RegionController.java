package com.codeid.eshopper.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.codeid.eshopper.Service.RegionService;
import com.codeid.eshopper.model.Regions;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/region")
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping("/all")
    public ModelAndView getAll() {

        ModelAndView view = new ModelAndView("region");
        view.addObject("regions", regionService.findAllCategory());
        return view;
    }

}
