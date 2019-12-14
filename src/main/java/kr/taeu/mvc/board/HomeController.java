package kr.taeu.mvc.board;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HomeController {

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        return "Hello";
    }

    @RequestMapping(value = "/show")
    @ResponseBody
    public String show() {
        return "<h1>show</h1>";
    }
}
