
package wad.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Result extends AbstractPersistable<Long>{
    
    @ManyToOne
    private Competitor competitor;
    @ManyToOne
    private Competition competition;
    @OneToMany
    private List<EmitPunch> punches; 
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

    public Result(Competitor competitor, Competition competition, List<EmitPunch> punches) {
        this.competitor = competitor;
        this.competition = competition;
        this.punches = punches;
    }
    
    public Competitor getCompetitor(){
        return this.competitor;
    }
    
    public void setCompetitor(Competitor newCompetitor){
        this.competitor = newCompetitor;
    }
    
    public Competition getCompetition(){
        return this.competition;
    }
    
    public void setCompetition(Competition newCompetition){
        this.competition = newCompetition;
    }
    
    public List<EmitPunch> getPunches(){
        if (this.punches == null) {
            this.punches = new ArrayList<>();
        }
        return this.punches;
    }
    
    public void setPunches(List<EmitPunch> newPunches){
        this.punches = newPunches;
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
