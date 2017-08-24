
package wad.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class UserAccount extends AbstractPersistable<Long>{
    
    private String username;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> authorities;
    
    public String getUsername(){
        return this.username;
    }
    
    public void setUsername(String newUsername){
        this.username = newUsername;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public void setPassword(String newPassword){
        this.password = newPassword;
    }
    
    public List<String> getAuthorities(){
        if (this.authorities == null) {
            this.authorities = new ArrayList<>();
        }
        return this.authorities;
    }
    
    public void setAuthorities(List<String> newAuthorities){
        this.authorities = newAuthorities;
    }
    
}
