
package wad.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Competition;
import wad.domain.Competitor;
import wad.domain.Emit;
import wad.domain.EmitPunch;
import wad.domain.Result;
import wad.repository.ResultRepository;

@Service
public class ResultService {
    
    @Autowired
    private ResultRepository resultRepository;
    
    @Autowired
    private CompetitionService competitionService;
    
    @Autowired
    private EmitService emitService;
    
    @Autowired
    private CompetitorService competitorService;
    
    public void saveResult(Result result){
        resultRepository.save(result);
    }
    
    public List<Result> getResults(){
        return resultRepository.findAll();
    }
    
    public Result getResult(Long id){
        return resultRepository.findOne(id);
    }
    
    public void addResult(Long competitionId, Long competitorId){
        Competitor competitor = competitorService.getCompetitor(competitorId);
        Competition competition = competitionService.getCompetition(competitionId);
        
        Result result = new Result();
        result.setCompetitorName(competitor.getName());
        result.setCompetitorClub(competitor.getClub());
        result.setCompetitorEmitNumber(competitor.getEmit().getNumber());
        resultRepository.save(result);
        initialiseResultFromEmit(result,competitor.getEmit());
        result.setCompetition(competition);
        
        List<Result> results = competition.getResults();
        results.add(result);
        
        competition.setResults(results);
        
        competitionService.saveCompetition(competition);
        competitorService.saveCompetitor(competitor);
        
        resultRepository.save(result);
    }
    
    public void deleteResult(Long id){
        Result result = getResult(id);
        Competition competition = result.getCompetition();
        List<Result> competitionResults = competition.getResults();
        competitionResults.remove(result);
        competition.setResults(competitionResults);
        competitionService.saveCompetition(competition);
        resultRepository.delete(id);
    }
    
    public void initialiseResultFromEmit(Result result, Emit emit){
        result.setSplits(emitService.processEmitPunchesToSplits(emit));
        result.setCodes(emitService.processEmitPunchesToCodes(emit));
        result.setCumulative(emitService.processEmitPunchesToCumulativeTime(emit));
        result.setPrintableSplits(emitService.processEmitPunchesToPrintableSplits(emit));
        resultRepository.save(result);
        
    }
}
