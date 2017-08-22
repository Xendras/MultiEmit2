
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
public class EmitService {
    
    @Autowired
    private CompetitorRepository competitorRepository;
    
    @Autowired
    private EmitRepository emitRepository;
    
    public void saveEmit(Emit emit){
        emitRepository.save(emit);
    }
    
    public List<Emit> getEmits(){
        return emitRepository.findAll();
    }
    
    public Emit getEmit(Long id){
        return emitRepository.findOne(id);
    }
    
    public void deleteEmit(Long id){
        emitRepository.delete(id);
    }   
}
