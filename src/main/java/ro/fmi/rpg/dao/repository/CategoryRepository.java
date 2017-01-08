package ro.fmi.rpg.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.fmi.rpg.dao.entity.Category;
import ro.fmi.rpg.to.task.CategoryModel;

import java.util.List;

/**
 * Created by User on 12/11/2016.
 */
public interface CategoryRepository extends JpaRepository<Category, String> {

    @Query(" SELECT c FROM Category c " +
            "INNER JOIN c.user u " +
            "WHERE u.id = :userId AND c.name = :categoryName")
    Category findByName(@Param("categoryName") String categoryName, @Param("userId") int userId);

    @Query(" SELECT new ro.fmi.rpg.to.task.CategoryModel(c.id, c.name, c.description) FROM Category c " +
            "INNER JOIN c.user u " +
            "WHERE u.id = :userId")
    List<CategoryModel> findByUser(@Param("userId") int id);
}
