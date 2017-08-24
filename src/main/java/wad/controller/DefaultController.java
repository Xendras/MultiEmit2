package wad.controller;

import java.util.ArrayList;
import java.util.List;
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
    public void init() {
        UserAccount admin = new UserAccount();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        List<String> adminAuthorities = new ArrayList<>();
        adminAuthorities.add("ADMIN");
        adminAuthorities.add("USER");
        admin.setAuthorities(adminAuthorities);
        userAccountRepository.save(admin);
        
        UserAccount user = new UserAccount();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user"));
        List<String> userAuthorities = new ArrayList<>();
        userAuthorities.add("ADMIN");
        userAuthorities.add("USER");
        user.setAuthorities(userAuthorities);
        userAccountRepository.save(user);
    }
    
    @RequestMapping("*")
    public String handleDefault() {
        return "redirect:/index";
    }

}
