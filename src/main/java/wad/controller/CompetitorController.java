
package wad.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import wad.domain.Competitor;
import wad.domain.Emit;
import wad.repository.EmitRepository;
import wad.service.CompetitorService;

@Controller
@RequestMapping("/competitors")
public class CompetitorController {
    
    @Autowired
    private CompetitorService competitorService;
    
    @Autowired
    private EmitRepository emitRepository;
    
    @ModelAttribute
    private Competitor getCompetitor() {
        return new Competitor();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String viewCompetitors(Model model) {
        model.addAttribute("competitors", competitorService.getCompetitors());
        return "competitors";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addCompetitor(@Valid @ModelAttribute("competitor") Competitor competitor, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            viewCompetitors(model);
            return "competitors";
        }
        competitorService.saveCompetitor(competitor);
        competitorService.registerEmitForCompetitor(competitor, competitor.getEmitNumber());
        return "redirect:/competitors";
    }
    
    @ModelAttribute("emit")
    @RequestMapping(value = "/{competitorId}", method = RequestMethod.POST)
    public String registerEmitForCompetitor(@PathVariable Long competitorId, @Valid Emit emit, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            viewCompetitors(model);
            return "competitor";
        }
        
        competitorService.registerEmitForCompetitor(competitorService.getCompetitor(competitorId), emit.getNumber());
        return "redirect:/competitors";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String viewCompetitor(@PathVariable Long id, Model model) {
        Competitor competitor = competitorService.getCompetitor(id);
        model.addAttribute("competitor", competitor);
        return "competitor";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteCompetitor(@PathVariable Long id) {
        competitorService.deleteCompetitor(id);
        return "redirect:/competitors";
    }
}
