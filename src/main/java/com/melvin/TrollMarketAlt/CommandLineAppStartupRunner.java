package com.melvin.TrollMarketAlt;

import com.melvin.TrollMarketAlt.config.RestSecurityConfig;
import com.melvin.TrollMarketAlt.dao.AccountRepository;
import com.melvin.TrollMarketAlt.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        PasswordEncoder passwordEncoder = RestSecurityConfig.passwordEncoder();
        String hashPassword = passwordEncoder.encode("melvin");

        Account initialAdmin = Account.builder()
                .id("melvin")
                .password(hashPassword)
                .role("ADMIN")
                .build();
        accountRepository.save(initialAdmin);
    }
}
