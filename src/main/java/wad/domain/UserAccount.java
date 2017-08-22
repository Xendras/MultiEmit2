
package wad.domain;

import javax.persistence.Entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class UserAccount extends AbstractPersistable<Long>{
    
    @NotBlank
    @Length(min = 2, max = 10)
    private String username;
    @NotBlank
    @Length(min = 5, max = 21)
    private String password;
    @NotBlank
    private String authority;
    
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
    
    public String getAuthority(){
        return this.authority;
    }
    
    public void setAuthority(String newAuthority){
        this.authority = newAuthority;
    }
    
}
