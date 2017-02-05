package ro.fmi.rpg.to.charts;

import java.util.List;

/**
 * Created by User on 2/4/2017.
 */
public class BarChartData {

    private List<Integer> data;
    private String label;

    public BarChartData(String label) {
        this.label = label;
    }

    public BarChartData(List<Integer> onTime, String label) {
        this.data = onTime;
        this.label = label;
    }

    public BarChartData() {
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
