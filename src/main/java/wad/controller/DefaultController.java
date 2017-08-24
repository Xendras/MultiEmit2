package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import wad.repository.UserAccountRepository;

@Controller
public class DefaultController {
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserAccountRepository userAccountRepository;

//    @PostConstruct
//    public void init() {
//        UserAccount admin = new UserAccount();
//        userAccountRepository.save(admin);
//        admin.setUsername("admin");
//        admin.setPassword(passwordEncoder.encode("admin"));
//        List<String> adminAuthorities = new ArrayList<>();
//        adminAuthorities.add("ADMIN");
//        adminAuthorities.add("USER");
//        admin.setAuthorities(adminAuthorities);
//        userAccountRepository.save(admin);
//        
//        UserAccount user = new UserAccount();
//        userAccountRepository.save(user);
//        user.setUsername("user");
//        user.setPassword(passwordEncoder.encode("user"));
//        List<String> userAuthorities = new ArrayList<>();
//        userAuthorities.add("ADMIN");
//        userAuthorities.add("USER");
//        user.setAuthorities(userAuthorities);
//        userAccountRepository.save(user);
//    }
    
    @RequestMapping("*")
    public String handleDefault() {
        return "redirect:/index";
    }

}
