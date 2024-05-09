package practice.newbalance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

@EnableJpaAuditing
@SpringBootApplication
public class NewBalanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewBalanceApplication.class, args);
    }

    // TODO : 임시 UUID를 디비에 저장하는 방식을
    //  추후 세션에서 아이디를 꺼내서 DB에 저장하는 방식으로 변경 예정
    @Bean
    public AuditorAware<String> auditorProvider(){
        return () -> Optional.of(UUID.randomUUID().toString());
    }

}
