
package wad.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Competition;
import wad.domain.Competitor;
import wad.domain.Result;
import wad.repository.CompetitionRepository;
import wad.repository.ResultRepository;

@Controller
@RequestMapping("/competitions/{id}/results")
public class ResultController {
    
    @Autowired
    private ResultRepository resultRepository;
    
    @Autowired
    private CompetitionRepository competitionRepository;
    
    @ModelAttribute
    private Result getResult() {
        return new Result();
    }
    
//    @RequestMapping(method = RequestMethod.GET)
//    public String viewResults(@PathVariable Long id, Model model){
//        Competition competition = competitionRepository.findOne(id);
//        model.addAttribute("results", competition.getResults());
//        return "competition";
//    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String addResults(@PathVariable Long id, @RequestParam Competitor competitor){
        Competition competition = competitionRepository.findOne(id);
        Result result = new Result();
        result.setCompetitor(competitor);
        List<Result> results = competition.getResults();
        results.add(result);
        competition.setResults(results);
        return "competition";
    }
    
    @RequestMapping(value = "/{resultId}", method = RequestMethod.DELETE)
    public String removeResult(@PathVariable Long id, @PathVariable Long resultId){
        Competition competition = competitionRepository.findOne(id);
        Result result = resultRepository.findOne(resultId);
        competition.getResults().remove(result);
        resultRepository.delete(resultId);
        return "redirect:/competition";
    }
    
    
    
}
