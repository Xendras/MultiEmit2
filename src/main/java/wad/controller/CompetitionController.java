package wad.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Competition;
import wad.service.CompetitionService;
import wad.service.CompetitorService;

@Controller
@RequestMapping("/competitions")
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private CompetitorService competitorService;

    @ModelAttribute
    private Competition getCompetition() {
        return new Competition();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String viewCompetitions(Model model) {
        model.addAttribute("competitions", competitionService.getCompetitions());
        return "competitions";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addCompetition(@Valid @ModelAttribute("competition") Competition competition, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            viewCompetitions(model);
            return "competitions";
        }
        competitionService.saveCompetition(competition);
        return "redirect:/competitions";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String addCompetitorToCompetition(@PathVariable Long id, @RequestParam Long competitorId) {
        competitionService.addCompetitorToCompetition(competitorId, id);
        return "redirect:/competitions/{id}";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String viewCompetition(@PathVariable Long id, Model model) {
        Competition competition = competitionService.getCompetition(id);
        model.addAttribute("competition", competition);
        model.addAttribute("competitors", competitorService.getCompetitors());
        model.addAttribute("competitionCompetitors", competition.getCompetitors());
        return "competition";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteCompetition(@PathVariable Long id) {
        competitionService.deleteCompetition(id);
        return "redirect:/competitions";
    }

    @RequestMapping(value = "/{id}/competitors/{competitorId}", method = RequestMethod.DELETE)
    public String deleteCompetitorFromCompetition(@PathVariable Long id, @PathVariable Long competitorId) {
        competitionService.deleteCompetitorFromCompetition(competitorId, id);
        return "redirect:/competitions/{id}";
    }

}
