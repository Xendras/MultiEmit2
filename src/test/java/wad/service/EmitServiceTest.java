package wad.service;


import java.util.Date;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wad.domain.Competition;
import wad.repository.CompetitionRepository;
import wad.service.CompetitionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmitServiceTest {
    
    @Autowired
    private CompetitionService competitionService;
    
    @Autowired
    private CompetitionRepository competitionRepository;
          
    private Competition competition;
    
    @Before
    public void setUp() {
        competition = new Competition();
        Date date = new Date(2017,7,6);
        competition.setName("Name");
        competition.setLocation("Location");
        competition.setDate(date);
    }
    
    @Test
    public void competitionIsAddedCorrectly(){
        competitionService.saveCompetition(competition);
        Competition newCompetition = competitionRepository.findOne(competition.getId());
        assertNotNull(newCompetition);
    }
    
    @After
    public void tearDown() {
    }


}
