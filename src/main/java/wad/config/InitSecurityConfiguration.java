/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.config;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import wad.domain.UserAccount;
import wad.repository.UserAccountRepository;

@Configuration
public class InitSecurityConfiguration {

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

}
