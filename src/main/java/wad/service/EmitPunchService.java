
package wad.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Emit;
import wad.domain.EmitPunch;
import wad.repository.CompetitorRepository;
import wad.repository.EmitPunchRepository;

@Service
public class EmitPunchService {
    
    @Autowired
    private EmitPunchRepository emitPunchRepository;
    
    public void saveEmitPunch(EmitPunch emitPunch){
        emitPunchRepository.save(emitPunch);
    }
    
    public List<EmitPunch> getEmitPunches(){
        return emitPunchRepository.findAll();
    }
    
    public EmitPunch getEmitPunch(Long id){
        return emitPunchRepository.findOne(id);
    }
    
    public void deleteEmitPunch(Long id){
        emitPunchRepository.delete(id);
    }   
}
