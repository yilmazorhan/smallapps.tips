package tr.com.minicrm.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import tr.com.minicrm.web.platform.configuration.MainConfiguration;

@SpringBootApplication
@Import(MainConfiguration.class)
@ComponentScan("tr.com.minicrm")
public class Main {

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

}
