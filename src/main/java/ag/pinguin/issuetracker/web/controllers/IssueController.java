package ag.pinguin.issuetracker.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ag.pinguin.issuetracker.business.entities.Bug;
import ag.pinguin.issuetracker.business.repositories.BugRepository;
import ag.pinguin.issuetracker.business.repositories.DeveloperRepository;
import ag.pinguin.issuetracker.business.repositories.StoryRepository;
import ag.pinguin.issuetracker.web.controllers.forms.IssueForm;

@Controller
@RequestMapping("/tracker/issue")
public class IssueController {
	
	@Autowired
	DeveloperRepository developerRepository;
	
	@Autowired
	StoryRepository storyRepository;
	
	@Autowired
	BugRepository bugRepository;
	
	@RequestMapping(value="")
	public String issues(Model model){
		model.addAttribute("stories", storyRepository.findAll());
		model.addAttribute("bugs", bugRepository.findAll());
		return "issue/list";
	}
	
	@RequestMapping(value="create", method=RequestMethod.GET)
	public String createIssue(Model model) {
		model.addAttribute("issue", new IssueForm());
		model.addAttribute("developers", developerRepository.findAll());
		return "issue/create";
	}
	
	@RequestMapping(value="create", method=RequestMethod.POST)
	public String createIssue(@Valid IssueForm form, BindingResult bindingResult, 
			Model model, final RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("errorMessage", "Fail to create a new issue. Check error messages");
			return "redirect:/tracker/issue";
		}
		if(form.getFormType() == IssueForm.FormType.BUG) {
			bugRepository.save(new Bug(form.getTitle(), form.getDescription(), 
					form.getDeveloper(), form.getBugStatus(), form.getBugPriority()));
		} else if (form.getFormType() == IssueForm.FormType.STORY) {
			
		}
		return "issue/create";
	}

}
