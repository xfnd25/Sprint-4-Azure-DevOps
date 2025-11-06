package com.mottu.motolocation.config;

import com.mottu.motolocation.entity.Role;
import com.mottu.motolocation.entity.User;
import com.mottu.motolocation.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Verificando se usuários iniciais precisam ser criados...");

        if (!userRepository.findByUsername("admin").isPresent()) {
            logger.info("Criando usuário ADMIN...");
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin")) // Senha 'admin'
                    .role(Role.ROLE_ADMIN)
                    .build();
            userRepository.save(admin);
            logger.info("Usuário ADMIN criado com sucesso.");
        }

        if (!userRepository.findByUsername("user").isPresent()) {
            logger.info("Criando usuário USER...");
            User user = User.builder()
                    .username("user")
                    .password(passwordEncoder.encode("user")) // Senha 'user'
                    .role(Role.ROLE_USER)
                    .build();
            userRepository.save(user);
            logger.info("Usuário USER criado com sucesso.");
        }

        logger.info("Verificação de usuários iniciais concluída.");
    }
}