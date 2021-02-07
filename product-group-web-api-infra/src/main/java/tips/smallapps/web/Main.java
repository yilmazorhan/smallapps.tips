package tips.smallapps.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import tips.smallapps.web.platform.configuration.MainConfiguration;

@SpringBootApplication
@Import(MainConfiguration.class)
@ComponentScan("tips.smallapps")
public class Main {

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

}
