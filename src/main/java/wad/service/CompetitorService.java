package wad.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Competitor;
import wad.domain.Emit;
import wad.repository.CompetitorRepository;
import wad.repository.EmitRepository;

@Service
public class CompetitorService {

    @Autowired
    private CompetitorRepository competitorRepository;

    @Autowired
    private EmitRepository emitRepository;

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
        emit.setCompetitor(null);
        competitor.setEmit(null);
        competitorRepository.delete(id);
    }

    public void registerEmitForCompetitor(Competitor competitor, String emitNumber) {
        Emit emit = emitRepository.findByNumber(emitNumber);
        if (emit == null) {
            emit = new Emit();
            emit.setNumber(emitNumber);
            emit.setCompetitor(competitor);
            competitor.setEmit(emit);
        } else if(emit.getOwner() == null){
            emit.setCompetitor(competitor);
            competitor.setEmit(emit);
        } else {
            return;
        }
        competitorRepository.save(competitor);
        emitRepository.save(emit);
    }

}
