/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
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

@Profile("default")
@Configuration
public class InitTestCasesConfiguration {
    
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

    @PostConstruct
    public void init() {
        Competition competition = new Competition("Länsirastit", "Kaitakorpi", new Date(117, 7, 24) );
        competitionService.saveCompetition(competition);

        Emit emit = new Emit("412552");
        emitService.saveEmit(emit);
        Competitor competitor = new Competitor("Jonas Westerlund", "OK77", emit);
        competitorService.saveCompetitor(competitor);
        emit.setOwner(competitor);
        competitorService.saveCompetitor(competitor);
        emitService.saveEmit(emit);

        Emit emit2 = new Emit("221489");
        emitService.saveEmit(emit2);
        Competitor competitor2 = new Competitor("Sandra Kulla", "IK Falken", emit2);
        competitorService.saveCompetitor(competitor2);
        emit2.setOwner(competitor2);
        competitorService.saveCompetitor(competitor2);
        emitService.saveEmit(emit2);
        
        List<EmitPunch> punches = new ArrayList<>();
        EmitPunch punch1 = new EmitPunch(emit,"123",new Date(117,7,24,18,0,0));
        EmitPunch punch2 = new EmitPunch(emit,"124",new Date(117,7,24,18,2,0));
        EmitPunch punch3 = new EmitPunch(emit,"125",new Date(117,7,24,18,5,0));
        punches.add(punch1);
        punches.add(punch2);
        punches.add(punch3);
        emit.setEmitPunches(punches);
        emitPunchService.saveEmitPunch(punch1);
        emitPunchService.saveEmitPunch(punch2);
        emitPunchService.saveEmitPunch(punch3);
        Result result = new Result(competitor, competition);
        resultService.initialiseResultFromEmit(result, emit);
        List<Result> results = Arrays.asList(result);
        competition.setResults(results);
        emitService.saveEmit(emit);
        resultService.saveResult(result);
        competitorService.saveCompetitor(competitor);
        competitionService.saveCompetition(competition);
    }

}
