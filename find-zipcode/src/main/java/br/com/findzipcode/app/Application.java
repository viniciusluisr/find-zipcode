package br.com.findzipcode.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring bootable find-zipcode project class
 * Created by Vinicius on 03/12/15.
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "br.com.findzipcode")
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}


