/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.config;

import java.util.Arrays;
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
        admin.setAuthorities(Arrays.asList("ADMIN", "USER"));
        userAccountRepository.save(admin);
        
        UserAccount user = new UserAccount();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user"));
        user.setAuthorities(Arrays.asList("USER"));
        userAccountRepository.save(user);
    }

}
