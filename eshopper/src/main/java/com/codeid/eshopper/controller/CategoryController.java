package com.codeid.eshopper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.codeid.eshopper.Service.CategoryService;
import com.codeid.eshopper.model.Category;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/index")
    public ModelAndView getAll() {
        ModelAndView view = new ModelAndView("/category/index");
        view.addObject("categories", categoryService.getDataCategory());
        return view;

    }

    @GetMapping("/addForm")
    public ModelAndView addForm() {
        ModelAndView view = new ModelAndView("/category/addForm");
        view.addObject("category", new Category());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute Category category) {
        categoryService.addCategory(category);
        return new ModelAndView("redirect:/category/index");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView view = new ModelAndView("/category/addForm");
        categoryService.findCategoryById(id).ifPresent(categories -> view.addObject("category", categories));
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {

        categoryService.deleteCategoryById(id);
        return new ModelAndView("redirect:/category/index");
    }

}
