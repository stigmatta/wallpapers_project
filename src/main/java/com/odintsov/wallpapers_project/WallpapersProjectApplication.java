package com.odintsov.wallpapers_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class WallpapersProjectApplication {

    static void main(String[] args) {
        SpringApplication.run(WallpapersProjectApplication.class, args);
    }

}

@RestController
class HelloController {
    @GetMapping("/")
    public String sayHello() {
        return "Hello world";
    }
}
