
package wad.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Competitor extends AbstractPersistable<Long>{
    
    @NotBlank
    private String name;
    @NotNull
    private Emit emit;
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
    
    public String getClub() {
        return this.club;
    }

    public void setclub(String newClub) {
        this.club = newClub;
    }
    
}
