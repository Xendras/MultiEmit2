package wad.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Competition;
import wad.domain.Competitor;
import wad.domain.Emit;
import wad.domain.EmitPunch;
import wad.domain.Result;
import wad.service.CompetitionService;
import wad.service.CompetitorService;
import wad.service.EmitPunchService;
import wad.service.EmitService;
import wad.service.ResultService;

@Controller
@RequestMapping("/competitions")
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private CompetitorService competitorService;
    @Autowired
    private EmitService emitService;
    @Autowired
    private EmitPunchService emitPunchService;
    @Autowired
    private ResultService resultService;

//    @PostConstruct
//    @Profile("default")
//    private void init() {
//        Competition competition = new Competition("Länsirastit", "Kaitakorpi", new Date(117, 7, 24) );
//        competitionService.saveCompetition(competition);
//
//        Emit emit = new Emit("412552");
//        emitService.saveEmit(emit);
//        Competitor competitor = new Competitor("Jonas Westerlund", "OK77", emit);
//        competitorService.saveCompetitor(competitor);
//        emit.setCompetitor(competitor);
//        competitorService.saveCompetitor(competitor);
//        emitService.saveEmit(emit);
//
//        Emit emit2 = new Emit("221489");
//        emitService.saveEmit(emit2);
//        Competitor competitor2 = new Competitor("Sandra Kulla", "IK Falken", emit2);
//        competitorService.saveCompetitor(competitor2);
//        emit2.setCompetitor(competitor2);
//        competitorService.saveCompetitor(competitor2);
//        emitService.saveEmit(emit2);
//        
//        List<EmitPunch> punches = new ArrayList<>();
//        EmitPunch punch1 = new EmitPunch(emit,"123",new Date(117,7,24,18,0,0));
//        EmitPunch punch2 = new EmitPunch(emit,"124",new Date(117,7,24,18,2,0));
//        EmitPunch punch3 = new EmitPunch(emit,"125",new Date(117,7,24,18,5,0));
//        punches.add(punch1);
//        punches.add(punch2);
//        punches.add(punch3);
//        emit.setEmitPunches(punches);
//        emitPunchService.saveEmitPunch(punch1);
//        emitPunchService.saveEmitPunch(punch2);
//        emitPunchService.saveEmitPunch(punch3);
//        Result result = new Result(competitor, competition, punches);
//        resultService.initialiseResultFromEmit(result, emit);
//        List<Result> results = Arrays.asList(result);
//        competition.setResults(results);
//        competitor.setResults(results);
//        resultService.saveResult(result);
//        competitorService.saveCompetitor(competitor);
//        competitionService.saveCompetition(competition);
//    }

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
        Competitor competitor = competitorService.getCompetitor(competitorId);
        Competition competition = competitionService.getCompetition(id);
        List<Competitor> competitors = competition.getCompetitors();
        if (competitors.contains(competitor)) {
            return "redirect:/competitions/{id}";
        }
        competitors.add(competitor);
        competition.setCompetitors(competitors);
        competitionService.saveCompetition(competition);
        competitorService.saveCompetitor(competitor);
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
        // remove result connections
        competitionService.deleteCompetition(id);
        return "redirect:/competitions";
    }

    @RequestMapping(value = "/{id}/competitors/{competitorId}", method = RequestMethod.DELETE)
    public String deleteCompetitorFromCompetition(@PathVariable Long id, @PathVariable Long competitorId) {
        Competitor competitor = competitorService.getCompetitor(competitorId);
        Competition competition = competitionService.getCompetition(id);
        List<Competitor> competitors = competition.getCompetitors();
        competitors.remove(competitor);
        competitionService.saveCompetition(competition);
        competitorService.saveCompetitor(competitor);
        return "redirect:/competitions/{id}";
    }

}
