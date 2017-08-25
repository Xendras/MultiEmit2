package wad.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Competitor extends AbstractPersistable<Long> {

    @NotBlank
    private String name;
    @OneToOne
    private Emit emit;
    private String emitNumber;
    @NotBlank
    private String club;
    @ManyToMany
    private List<Competition> competitions;


    public Competitor() {

    }

    public Competitor(String name, String club, Emit emit) {
        this.name = name;
        this.club = club;
        this.emit = emit;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getEmitNumber() {
        return this.emitNumber;
    }

    public void setEmitNumber(String newEmitNumber) {
        this.emitNumber = newEmitNumber;
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

    public void setClub(String newClub) {
        this.club = newClub;
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }

}
