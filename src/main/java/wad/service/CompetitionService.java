
package wad.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Competition;
import wad.domain.Competitor;
import wad.domain.Result;
import wad.repository.CompetitionRepository;

@Service
public class CompetitionService {
    
    @Autowired
    private CompetitionRepository competitionRepository;
    
    @Autowired
    private CompetitorService competitorService;
    
    @Autowired
    private ResultService resultService;
    
    public void saveCompetition(Competition competition){
        competitionRepository.save(competition);
    }
    
    public List<Competition> getCompetitions(){
        return competitionRepository.findAll();
    }
    
    public Competition getCompetition(Long id){
        return competitionRepository.findOne(id);
    }
    
    public void deleteCompetition(Long id){
        Competition competition = competitionRepository.findOne(id);
        List<Result> results = competition.getResults();
        competition.setResults(null);
        for(Result result : results){
            resultService.deleteResult(result.getId());
        }
        competitionRepository.delete(id);
    }
    
    
    @Transactional
    public void addCompetitorToCompetition(Long competitorId, Long competitionId){
        Competitor competitor = competitorService.getCompetitor(competitorId);
        Competition competition = competitionRepository.findOne(competitionId);
        
        List<Competitor> competitors = competition.getCompetitors();
        if (competitors.contains(competitor)) {
            return;
        }
        
        List<Competition> competitions = competitor.getCompetitions();
        if (competitions.contains(competition)) {
            return;
        }
        
        competitors.add(competitor);
        competitions.add(competition);
        
//        competitionRepository.save(competition);
//        competitorService.saveCompetitor(competitor);
    }
    
    @Transactional
    public void deleteCompetitorFromCompetition(Long competitorId, Long competitionId){
        Competitor competitor = competitorService.getCompetitor(competitorId);
        Competition competition = competitionRepository.findOne(competitionId);
        
        List<Competitor> competitors = competition.getCompetitors();
        competitors.remove(competitor);
        
        List<Competition> competitions = competitor.getCompetitions();
        competitions.remove(competition);
        
//        competitionRepository.save(competition);
//        competitorService.saveCompetitor(competitor);
    }
    
}
