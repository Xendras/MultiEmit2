package wad.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Competition extends AbstractPersistable<Long> {

    @NotBlank
    private String location;
    @NotBlank
    private String name;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @ElementCollection
    private List<String> controls;
    
    @ManyToMany
    private List<Competitor> competitors;
    
    @OneToMany
    private List<Result> results;
    
    public Competition() {

    }

    public Competition(String name, String location, Date date) {
        this.name = name;
        this.location = location;
        this.date = date;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String newLocation) {
        this.location = newLocation;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date newDate) {
        this.date = newDate;
    }
    
    public List<Competitor> getCompetitors() {
        if (this.competitors == null) {
            this.competitors = new ArrayList<>();
        }
        return this.competitors;
    }

    public void setCompetitors(List<Competitor> newCompetitors) {
        this.competitors = newCompetitors;
    }
    
    public List<Result> getResults() {
        if (this.results == null) {
            this.results = new ArrayList<>();
        }
        return this.results;
    }

    public void setResults(List<Result> newResults) {
        this.results = newResults;
    }

}
