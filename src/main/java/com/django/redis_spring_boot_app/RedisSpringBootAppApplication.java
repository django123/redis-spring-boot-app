package com.django.redis_spring_boot_app;

import com.django.redis_spring_boot_app.domain.UserEntity;
import com.django.redis_spring_boot_app.repos.UserEntityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class RedisSpringBootAppApplication implements CommandLineRunner {

    private final UserEntityRepository userRepository;

    public RedisSpringBootAppApplication(UserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(final String[] args) {
        SpringApplication.run(RedisSpringBootAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        UserEntity user = new UserEntity("EDOUGA",2000L);
        UserEntity user2 = new UserEntity("EDOUGA 2",29000L);
        UserEntity user3 = new UserEntity("EJP",550L);
        UserEntity user4 = new UserEntity("Django",200L);

        userRepository.save(user);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);


    }
}
