package kr.mjc.lsh2.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "kr.mjc.lsh2.web.servlets")
public class Lsh2Application {

    public static void main(String[] args) {
        SpringApplication.run(Lsh2Application.class, args);
    }

}
