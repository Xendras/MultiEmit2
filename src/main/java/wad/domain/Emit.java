package wad.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Emit extends AbstractPersistable<Long> {

    @NotBlank
    @Pattern(regexp = "\\(|\\)|\\d{6}", message = "Emit number must be 6 digits long")
    private String number;
    @OneToOne
    private Competitor owner;
    @OneToMany
    private List<EmitPunch> emitPunches;

    public Emit() {

    }

    public Emit(String number) {
        this.number = number;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String newNumber) {
        this.number = newNumber;
    }

    public Competitor getOwner() {
        return this.owner;
    }

    public void setOwner(Competitor newOwner) {
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
