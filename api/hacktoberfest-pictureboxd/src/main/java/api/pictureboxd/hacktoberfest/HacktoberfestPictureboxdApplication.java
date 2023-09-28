package api.pictureboxd.hacktoberfest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HacktoberfestPictureboxdApplication {

  public static void main(String[] args) {
    SpringApplication.run(HacktoberfestPictureboxdApplication.class, args);
  }
}
