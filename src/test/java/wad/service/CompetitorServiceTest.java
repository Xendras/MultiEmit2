package wad.service;

import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wad.domain.Competitor;
import wad.domain.Emit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompetitorServiceTest {
    
    @Autowired
    private CompetitionService competitionService;
    
    @Autowired
    private EmitService emitService;
    
    @Autowired
    private CompetitorService competitorService;
          
    private Competitor competitor;
    private Emit emit;
    
    @Before
    public void setUp() {
        emit = new Emit("123123");
        emitService.saveEmit(emit);
        
        competitor = new Competitor();
        
        emit.setOwner(competitor);
        competitor.setName("Jonas");
        competitor.setClub("OK77");
        competitor.setEmit(emit);
        
        competitorService.saveCompetitor(competitor);
        emitService.saveEmit(emit);
    }
    
    @Test
    public void oldOwnedEmitIsRegisteredCorrectly(){
        competitor.setEmit(null);
        competitorService.registerEmitForCompetitor(competitor, "123123");
        assertNull(competitor.getEmit());
    }
    
}
