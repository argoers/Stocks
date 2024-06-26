package stocks.service;

import stocks.model.Day;
import stocks.dao.DayDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayService {

    @Autowired
    DayDao dayDao;

    public List<Day> getAllDays() {
        return dayDao.getAllDays();
    }
}
