package ro.fmi.rpg.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.fmi.rpg.exception.RPGException;
import ro.fmi.rpg.service.TaskService;
import ro.fmi.rpg.to.charts.BarChartData;
import ro.fmi.rpg.to.charts.LineChartData;

import java.util.List;

/**
 * Created by User on 2/4/2017.
 */

@RestController
@RequestMapping("/api")
public class ChartController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(path = "/chart/bar", method = RequestMethod.GET)
    public List<BarChartData> getBarChartData() throws RPGException {
        return taskService.getBarChartData();
    }

    @RequestMapping(path = "/chart/line", method = RequestMethod.GET)
    public List<LineChartData> getLineChartData() throws RPGException {
        return taskService.getLineChartData();
    }

    @RequestMapping(path = "/chart/pie", method = RequestMethod.GET)
    public LineChartData getPieChartData() throws RPGException {
        return taskService.getPieChartData();
    }

    @RequestMapping(path = "/chart/doughnut", method = RequestMethod.GET)
    public LineChartData getDoughnutChartData() throws RPGException {
        return taskService.getDoughnotData();
    }

}