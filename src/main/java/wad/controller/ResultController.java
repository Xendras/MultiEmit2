
package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Competition;
import wad.domain.Result;
import wad.repository.CompetitionRepository;
import wad.repository.ResultRepository;

@Controller
@RequestMapping("/competitions/{id}")
public class ResultController {
    
    @Autowired
    private ResultRepository resultRepository;
    
    @Autowired
    private CompetitionRepository competitionRepository;
    
    @ModelAttribute
    private Result getResult() {
        return new Result();
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String addResults(@PathVariable Long id, @RequestParam Competition competition){
        return "competition";
    }
    
    @RequestMapping(value = "/results/{resultId}", method = RequestMethod.DELETE)
    public String removeResult(@PathVariable Long id, @PathVariable Long resultId){
        Competition competition = competitionRepository.findOne(id);
        Result result = resultRepository.findOne(resultId);
        competition.getResults().remove(result);
        resultRepository.delete(resultId);
        return "redirect:/competition";
    }
    
    
    
}
