
package wad.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Emit extends AbstractPersistable<Long>{
    
    @NotBlank
    @Digits(integer = 6, fraction = 0)
    private Integer number;   
    @NotNull
    private Competitor owner;
    @OneToMany
    private List<EmitPunch> emitPunches;
    
    public Integer getInteger() {
        return this.number;
    }

    public void setNumber(Integer newNumber) {
        this.number = newNumber;
    }

    public Competitor getOwner() {
        return this.owner;
    }

    public void setCompetitor(Competitor newOwner) {
        this.owner = newOwner;
    }
    
    public List<EmitPunch> getEmitPunches() {
        if (this.emitPunches == null) {
            this.emitPunches = new ArrayList<>();
        }
        return this.emitPunches;
    }

    public void setEmitPunches(List<EmitPunch> newEmitPunches) {
        this.emitPunches = newEmitPunches;
    }
    
}
