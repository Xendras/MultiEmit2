package wad.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Competitor;
import wad.domain.Emit;
import wad.repository.CompetitorRepository;

@Service
public class CompetitorService {

    @Autowired
    private CompetitorRepository competitorRepository;

    @Autowired
    private EmitService emitService;

    @Autowired
    private ResultService resultService;

    public void saveCompetitor(Competitor competitor) {
        competitorRepository.save(competitor);
    }

    public List<Competitor> getCompetitors() {
        return competitorRepository.findAll();
    }

    public Competitor getCompetitor(Long id) {
        return competitorRepository.findOne(id);
    }

    public void deleteCompetitor(Long id) {
        Competitor competitor = competitorRepository.findOne(id);
        Emit emit = competitor.getEmit();
        if (emit != null) {
            emit.setOwner(null);
        }
        competitorRepository.delete(id);
    }

    public void registerEmitForCompetitor(Competitor competitor, String emitNumber) {
        Emit emit = emitService.getByNumber(emitNumber);
        
        if (emit == null) {
            emit = new Emit();
            emit.setNumber(emitNumber);
            emitService.saveEmit(emit);
            competitor.setEmit(emit);
            competitorRepository.save(competitor);
            emit.setOwner(competitor);
        } else if (emit.getOwner() == null) {
            emit.setOwner(competitor);
            competitor.setEmit(emit);
        } else {
            return;
        }
        
        competitorRepository.save(competitor);
        emitService.saveEmit(emit);
    }

}
