package wad.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Emit;
import wad.domain.EmitPunch;
import wad.repository.EmitPunchRepository;

@Service
public class EmitPunchService {

    @Autowired
    private EmitPunchRepository emitPunchRepository;
    
    @Autowired
    private EmitService emitService;

    public void saveEmitPunch(EmitPunch emitPunch) {
        emitPunchRepository.save(emitPunch);
    }

    public List<EmitPunch> getEmitPunches() {
        return emitPunchRepository.findAll();
    }

    public EmitPunch getEmitPunch(Long id) {
        return emitPunchRepository.findOne(id);
    }
    
    public void addEmitPunchToEmit(EmitPunch emitPunch, Long emitId) {
        if(emitPunch.getPunchTimeString().isEmpty()){
            return;
        }
        Emit emit = emitService.getEmit(emitId);
        emitPunchRepository.save(emitPunch);
        Date punchTime = convertStringToTimestamp(emitPunch.getPunchTimeString());
        emitPunch.setPunchTime(punchTime);
        emit.getEmitPunches().add(emitPunch);
        emitPunch.setEmit(emit);
        emitService.saveEmit(emit);
        emitPunchRepository.save(emitPunch);
    }

    public void deleteEmitPunch(Long id) {
        EmitPunch emitPunch = emitPunchRepository.findOne(id);
        Emit emit = emitPunch.getEmit();
        emit.getEmitPunches().remove(emitPunch);
        emitPunchRepository.delete(id);
    }
    
    public Date convertStringToTimestamp(String punchTimeString){
        String date = (String) punchTimeString.subSequence(0,10);
        String time = (String) punchTimeString.subSequence(11,19);
        Date punchTime = Timestamp.valueOf(date + " " + time);
        return punchTime;
    }
}
