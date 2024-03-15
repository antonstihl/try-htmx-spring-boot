package dev.antonstihl.tryhtmxspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TryHtmxApplication {

    public static void main(String[] args) {
        SpringApplication.run(TryHtmxApplication.class, args);
    }

    @Controller
    public static class AppleController {
        private final AppleService appleService;

        public AppleController(AppleService appleService) {
            this.appleService = appleService;
        }

        @GetMapping("/random-apple")
        public String apple(Model model) {
            model.addAttribute("applename", appleService.getRandomAppleName());
            return "apple";
        }

        @GetMapping("/apples")
        public String apples(Model model) {
            model.addAttribute("apples", appleService.getApples());
            return "apples";
        }

        @PostMapping("/apples")
        public void postApples(AppleForm appleForm) {
            appleService.addApple(appleForm.name());
        }
    }

    public record AppleForm(String name) {
    }

    @Service
    public static class AppleService {

        private List<String> apples = new ArrayList<>(List.of(
                "Cox's Orange Pippin",
                "Braeburn",
                "Granny Smith",
                "Golden Delicious",
                "Pink Lady",
                "Fuji",
                "Ingrid-Marie"
        ));

        public String getRandomAppleName() {
            return apples.get((int) (Math.random() * apples.size()));
        }

        public List<String> getApples() {
            return apples;
        }

        public void addApple(String name) {
            apples.add(name);
        }
    }
}

