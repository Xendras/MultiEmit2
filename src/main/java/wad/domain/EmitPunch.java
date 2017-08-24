
package wad.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class EmitPunch extends AbstractPersistable<Long>{
    
    @OneToOne
    private Emit emit;
    @NotBlank
    private String punchCode;
    @Temporal(TemporalType.TIMESTAMP)
    private Date punchTime;
    private String punchTimeString;
    
    public EmitPunch() {

    }

    public EmitPunch(Emit emit, String code, Date timestamp) {
        this.emit = emit;
        this.punchCode = code;
        this.punchTime = timestamp;
    }
    
    public Emit getEmit(){
        return this.emit;
    }
    
    public void setEmit(Emit newEmit){
        this.emit = newEmit;
    }
    
    public String getPunchCode(){
        return this.punchCode;
    }
    
    public void setPunchCode(String newCode){
        this.punchCode = newCode;
    }
    
    public String getPunchTimeString(){
        return this.punchTimeString;
    }
    
    public void setPunchTimeString(String newPunchTimeString){
        this.punchTimeString = newPunchTimeString;
    }
    
    public Date getPunchTime(){
        return this.punchTime;
    }
    
    public void setPunchTime(Date newPunchTime){
        this.punchTime = newPunchTime;
    }
    
}
