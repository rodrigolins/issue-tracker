package ag.pinguin.issuetracker.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tracker")
public class TrackerController {
	
	public String index(Model model) {
		return "redirect:/tracker/developer";
	}

}
