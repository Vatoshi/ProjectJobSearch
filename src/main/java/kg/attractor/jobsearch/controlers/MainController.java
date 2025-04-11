package kg.attractor.jobsearch.controlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class MainController {

    @GetMapping
    public String getMainPage(Model model) {
        model.addAttribute("world", "Job Search");
        return "main";
    }
}
