
package wad.service;

import java.util.List;
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
        competitionRepository.delete(id);
    }
    
    public void addCompetitorToCompetition(Long competitorId, Long competitionId){
        Competitor competitor = competitorService.getCompetitor(competitorId);
        Competition competition = competitionRepository.findOne(competitionId);
        List<Competitor> competitors = competition.getCompetitors();
        if (competitors.contains(competitor)) {
            return;
        }
        competitors.add(competitor);
        competition.setCompetitors(competitors);
        competitionRepository.save(competition);
        competitorService.saveCompetitor(competitor);
    }
    
}
