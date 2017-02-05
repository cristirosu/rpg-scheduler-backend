package ro.fmi.rpg.web.controllers.test;

/**
 * Created by User on 1/23/2017.
 */
public class Test {

    private String color;
    private String description;
    private String stats;
    private String icon;
    private int percentage;

    public Test(String color, String description, String stats, String icon, int percentage) {
        this.color = color;
        this.description = description;
        this.stats = stats;
        this.icon = icon;
        this.percentage = percentage;
    }

    public Test() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
