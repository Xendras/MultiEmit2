
package wad.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Emit;
import wad.domain.EmitPunch;
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
    
    public List<String> procesEmitPunchesToCodes(Emit emit){
        List<EmitPunch> punches = emit.getEmitPunches();
        List<String> codes = new ArrayList<>();
        for(EmitPunch punch : punches){
            codes.add(punch.getPunchCode());
        }
        return codes;
    }
    
    public List<String> processEmitPunchesToSplits(Emit emit){
        List<EmitPunch> punches = emit.getEmitPunches();
        List<String> splits = new ArrayList<>();
        
        
        
        return splits;
        
    }
    
    public List<String> processEmitPunchesToCumulativeTime(Emit emit){
        List<EmitPunch> punches = emit.getEmitPunches();
        List<String> cumulative = new ArrayList<>();
        
        
        
        return cumulative;
    }
}
