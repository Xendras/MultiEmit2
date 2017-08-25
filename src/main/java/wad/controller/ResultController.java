
package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Competition;
import wad.domain.Result;
import wad.service.CompetitionService;
import wad.service.CompetitorService;
import wad.service.ResultService;

@Controller
@RequestMapping("/competitions/{id}/results")
public class ResultController {
    
    
    @Autowired
    private ResultService resultService;
    
    @Autowired
    private CompetitionService competitionService;
    
    @Autowired
    private CompetitorService competitorService;
    
    @ModelAttribute
    private Result getResult() {
        return new Result();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String viewResults(@PathVariable Long id, Model model){
        Competition competition = competitionService.getCompetition(id);
        model.addAttribute("results", competition.getResults());
        return "results";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String addResult(@PathVariable Long id, @RequestParam Long competitorId){
        resultService.addResult(id, competitorId);
        return "redirect:/competitions/{id}";
    }
    
    @RequestMapping(value = "/{resultId}", method = RequestMethod.DELETE)
    public String removeResult(@PathVariable Long id, @PathVariable Long resultId){
        resultService.deleteResult(resultId);
        return "redirect:/competitions/{id}";
    }
    
    
    
}
