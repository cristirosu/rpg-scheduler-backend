package ro.fmi.rpg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fmi.rpg.dao.repository.CategoryRepository;
import ro.fmi.rpg.service.auth.SessionService;
import ro.fmi.rpg.to.task.CategoryModel;

import java.util.List;

/**
 * Created by User on 12/19/2016.
 */
@Service
public class CategoryService {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryModel> getCategories(){
        List<CategoryModel> categories = categoryRepository.findByUser(sessionService.getUser().getId());
        return categories;
    }

}
