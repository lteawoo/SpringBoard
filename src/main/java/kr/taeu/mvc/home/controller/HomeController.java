package kr.taeu.mvc.home.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

        @RestController
        @RequestMapping(value="/", method = RequestMethod.GET)
        public class HomeController {
            @GetMapping("")
            public Map<String, String> rootGET() {
                Map<String, String> result = new HashMap<>();
                result.put("a", "abc");
                return result;
    }
}
