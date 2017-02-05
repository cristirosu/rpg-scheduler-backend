package ro.fmi.rpg.to.charts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by User on 2/5/2017.
 */
public class LineChartData {

    private List<Integer> values;

    public LineChartData() {
        values = new ArrayList<Integer>(Collections.nCopies(12, 0));
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }
}
