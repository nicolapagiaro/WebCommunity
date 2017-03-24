package springmvc;

import dao.MembriDao;
import hibernate.HibernateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Classe MainController
 *
 * @author Matteo Parlato
 */
@Controller
public class MainController
{

    public MainController()
    {
        //
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map)
    {
        map.put("membriList", MembriDao.retrieveAll());
        return "membriList";
    }
}
