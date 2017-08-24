
package wad.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Result extends AbstractPersistable<Long>{
    
    @NotBlank
    private String competitorName;
    private String competitorClub;
    private String competitorEmitNumber;
    @ManyToOne
    private Competition competition;
    @ElementCollection
    private List<String> splits;
    @ElementCollection
    private List<String> codes;
    @ElementCollection
    private List<String> cumulative;
    @ElementCollection
    private List<String> printableSplits;
    
    public Result() {

    }

    public Result(Competitor competitor, Competition competition) {
        this.competitorName = competitor.getName();
        this.competitorClub = competitor.getClub();
        this.competitorEmitNumber = competitor.getEmit().getNumber();
        this.competition = competition;
    }
    
    public String getCompetitorName(){
        return this.competitorName;
    }
    
    public void setCompetitorName(String newCompetitorName){
        this.competitorName = newCompetitorName;
    }
    
    public String getCompetitorClub(){
        return this.competitorClub;
    }
    
    public void setCompetitorClub(String newCompetitorClub){
        this.competitorClub = newCompetitorClub;
    }
    
    public String getCompetitorEmitNumber(){
        return this.competitorEmitNumber;
    }
    
    public void setCompetitorEmitNumber(String newCompetitorEmitNumber){
        this.competitorEmitNumber = newCompetitorEmitNumber;
    }
    
    public Competition getCompetition(){
        return this.competition;
    }
    
    public void setCompetition(Competition newCompetition){
        this.competition = newCompetition;
    }
    
    public List<String> getSplits(){
        if (this.splits == null) {
            this.splits = new ArrayList<>();
        }
        return this.splits;
    }
    
    public void setSplits(List<String> newSplits){
        this.splits = newSplits;
    }
    
    public List<String> getCodes(){
        if (this.codes == null) {
            this.codes = new ArrayList<>();
        }
        return this.codes;
    }
    
    public void setCodes(List<String> newCodes){
        this.codes = newCodes;
    }
    
    public List<String> getCumulative(){
        if (this.cumulative == null) {
            this.cumulative = new ArrayList<>();
        }
        return this.cumulative;
    }
    
    public void setCumulative(List<String> newCumulative){
        this.cumulative = newCumulative;
    }
    
    public List<String> getPrintableSplits(){
        if (this.printableSplits == null) {
            this.printableSplits = new ArrayList<>();
        }
        return this.printableSplits;
    }
    
    public void setPrintableSplits(List<String> newPrintableSplits){
        this.printableSplits = newPrintableSplits;
    }
}
