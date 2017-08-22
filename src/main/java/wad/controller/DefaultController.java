package wad.controller;

import java.util.Arrays;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import wad.domain.UserAccount;
import wad.repository.UserAccountRepository;

@Controller
public class DefaultController {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
 
    @Autowired
    private UserAccountRepository userAccountRepository;
    
    @PostConstruct
    public void init(){
        UserAccount admin = new UserAccount();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setAuthorities(Arrays.asList("ADMIN", "USER"));
        
        UserAccount user = new UserAccount();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user"));
        user.setAuthorities(Arrays.asList("USER"));
    }
    

    @RequestMapping("/")
    public String handleDefault() {
        return "redirect:/index";
    }

}
