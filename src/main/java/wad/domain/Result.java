
package wad.domain;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Result extends AbstractPersistable<Long>{
    
    @NotNull
    private Competitor competitor;
    @NotNull
    private Competition competition;
    @OneToMany
    @NotEmpty
    private List<EmitPunch> splits;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    
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
    
    public List<EmitPunch> getSplits(){
        if (this.splits == null) {
            this.splits = new ArrayList<>();
        }
        return this.splits;
    }
    
    public void setSplits(List<EmitPunch> newSplits){
        this.splits = newSplits;
    }
    
    public Date getStartTime(){
        return this.startTime;
    }
    
    public void setStartTime(Date newStartTime){
        this.startTime = newStartTime;
    }
    
    public Date getEndTime(){
        return this.endTime;
    }
    
    public void setEndTime(Date newEndTime){
        this.endTime = newEndTime;
    }
    
}
