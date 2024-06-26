package stocks.controller;

import stocks.model.Day;
import stocks.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee/api")
public class DayController {

    @Autowired
    private DayService dayService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("getAllDays")
    public List<Day> getAllDays() {
        return dayService.getAllDays();
    }
}
