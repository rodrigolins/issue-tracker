package ag.pinguin.issuetracker.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ag.pinguin.issuetracker.business.entities.Developer;
import ag.pinguin.issuetracker.business.repositories.DeveloperRepository;

@Controller
@RequestMapping("/tracker/developer")
public class DeveloperController {
	
	@Autowired
	DeveloperRepository developerRepository;
	
	@RequestMapping("")
	public String developers(Model model) {
		model.addAttribute("developers", developerRepository.findAll());
		return "developer/list";
	}

	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String newDeveloper(Model model) {
		model.addAttribute("developer", new Developer());
		return "developer/new";
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String newDeveloper(@Valid Developer developer, BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("errorMessage", "Fail to create a new developer. Check error messages");
			return "developer/new";
		}
		developerRepository.save(new Developer(developer.getName()));
		return "redirect:/tracker/developer";
	}
	
	@RequestMapping(value="{id}/update", method=RequestMethod.GET)
	public String updateDeveloper(@PathVariable Long id, Model model) {
		Developer developer = developerRepository.findOne(id);
		model.addAttribute("developer", developer);
		return "developer/update";
	}
	
	@RequestMapping(value="/{id}/update", method=RequestMethod.POST)
	public String updateDeveloper(@PathVariable Long id, @Valid @ModelAttribute("developer") Developer developer, 
			BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes){
		if(bindingResult.hasErrors()) {
			model.addAttribute("errorMessage", "Fail to update developer");
			return "developer/update";
		}
		redirectAttributes.addFlashAttribute("successMessage", "Developer updated successfully");
		System.out.println(developer);
		Developer aDeveloper = developerRepository.findOne(developer.getDeveloperId());
		aDeveloper.setName(developer.getName());
		developerRepository.save(aDeveloper);
		return "redirect:/tracker/developer/" + id.toString();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String developerDetail(@PathVariable Long id, Model model){
		Developer developer = developerRepository.findOne(id);
		model.addAttribute("developer", developer);
		return "developer/detail";
	}
	
	@RequestMapping(value="/{id}/delete", method=RequestMethod.GET)
	public String developerDelete(@PathVariable Long id, Model model) {
		Developer developer = developerRepository.findOne(id);
		model.addAttribute("developer", developer);
		return "developer/delete";
	}
	
	@RequestMapping(value="/{id}/delete", method=RequestMethod.POST)
	public String developerDelete(@PathVariable Long id, final RedirectAttributes redirectAttributes) {
		developerRepository.delete(id);
		redirectAttributes.addFlashAttribute("successMessage", "Developer deleted successfully");
		return "redirect:/tracker/developer";
	}
}
