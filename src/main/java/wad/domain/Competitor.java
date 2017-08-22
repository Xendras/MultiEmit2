
package wad.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Competitor extends AbstractPersistable<Long>{
    
    @NotBlank
    private String name;
    @OneToOne
    private Emit emit;
    @Pattern(regexp="\\(|\\)|\\d{6}", message = "Emit number must be 6 digits long")
    private String emitNumber;
    @NotBlank
    private String club;
    
    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public Emit getEmit() {
        return this.emit;
    }

    public void setEmit(Emit newEmit) {
        this.emit = newEmit;
    }
    
    public String getEmitNumber() {
        return this.emitNumber;
    }

    public void setEmitNumber(String newEmitNumber) {
        this.emitNumber = newEmitNumber;
    }
    
    public String getClub() {
        return this.club;
    }

    public void setclub(String newClub) {
        this.club = newClub;
    }
    
}
