package wad.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Competitor;
import wad.domain.Emit;
import wad.domain.EmitPunch;
import wad.repository.EmitRepository;

@Service
public class EmitService {

    @Autowired
    private EmitRepository emitRepository;

    @Autowired
    private EmitPunchService emitPunchService;

    @Autowired
    private CompetitorService competitorService;

    public void saveEmit(Emit emit) {
        emitRepository.save(emit);
    }

    public List<Emit> getEmits() {
        return emitRepository.findAll();
    }

    public Emit getEmit(Long id) {
        return emitRepository.findOne(id);
    }

    public void deleteEmit(Long id) {
        Emit emit = emitRepository.findOne(id);

        Competitor owner = emit.getOwner();
        if (owner != null) {
            owner.setEmit(null);
            owner.setEmitNumber(null);
            competitorService.saveCompetitor(owner);
        }

        List<EmitPunch> punches = emit.getEmitPunches();
        if (punches != null) {
            emit.setEmitPunches(null);
            for (EmitPunch punch : punches) {
                emitPunchService.deleteEmitPunch(punch.getId());
            }
        }
        emitRepository.delete(id);
    }

    public Emit getByNumber(String number) {
        return emitRepository.findByNumber(number);
    }

    public List<String> processEmitPunchesToCodes(Emit emit) {
        List<EmitPunch> punches = emit.getEmitPunches();
        List<String> codes = new ArrayList<>();
        for (EmitPunch punch : punches) {
            codes.add(punch.getPunchCode());
        }
        return codes;
    }

    public List<String> processEmitPunchesToSplits(Emit emit) {
        List<EmitPunch> punches = emit.getEmitPunches();
        List<String> splits = new ArrayList<>();

        for (int i = 0; i < punches.size() - 1; i++) {
            Long split = punches.get(i + 1).getTimestamp().getTime() - punches.get(i).getTimestamp().getTime();
            splits.add(millisecondsToMinutesSeconds(split));
        }

        return splits;

    }

    public List<String> processEmitPunchesToCumulativeTime(Emit emit) {
        List<EmitPunch> punches = emit.getEmitPunches();
        List<String> cumulative = new ArrayList<>();
        Long cumulativeSplit = 0L;
        for (int i = 0; i < punches.size() - 1; i++) {
            cumulativeSplit += punches.get(i + 1).getTimestamp().getTime() - punches.get(i).getTimestamp().getTime();
            cumulative.add(millisecondsToMinutesSeconds(cumulativeSplit));
        }

        return cumulative;
    }

    public List<String> processEmitPunchesToPrintableSplits(Emit emit) {
        List<EmitPunch> punches = emit.getEmitPunches();
        List<String> printableSplits = new ArrayList<>();
        EmitPunch currentPunch;
        Long cumulativeSplit = 0L;
        for (int i = 0; i < punches.size() - 1; i++) {
            currentPunch = punches.get(i + 1);
            cumulativeSplit += punches.get(i + 1).getTimestamp().getTime() - punches.get(i).getTimestamp().getTime();
            Long split = punches.get(i + 1).getTimestamp().getTime() - punches.get(i).getTimestamp().getTime();
            printableSplits.add(millisecondsToMinutesSeconds(split) + " " + millisecondsToMinutesSeconds(cumulativeSplit) + " " + currentPunch.getPunchCode());
        }

        return printableSplits;

    }

    public String millisecondsToMinutesSeconds(Long ms) {
        return new SimpleDateFormat("mm:ss").format(new Date(ms));
    }
}
