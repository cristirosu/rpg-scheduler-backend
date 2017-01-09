package ro.fmi.rpg.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.fmi.rpg.service.CategoryService;
import ro.fmi.rpg.to.task.CategoryModel;

import java.util.List;

/**
 * Created by User on 12/19/2016.
 */
@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @CrossOrigin
    @RequestMapping(path = "/categories", method = RequestMethod.GET)
    public List<CategoryModel> getCategories(){
        return categoryService.getCategories();
    }

}
