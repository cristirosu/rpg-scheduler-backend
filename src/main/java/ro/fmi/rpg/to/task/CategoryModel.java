package ro.fmi.rpg.to.task;

/**
 * Created by User on 12/11/2016.
 */
public class CategoryModel {

    private int id;
    private String name;
    private String description;

    public CategoryModel() {
    }

    public CategoryModel(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
