package dev.antonstihl.tryhtmxspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

        @DeleteMapping("/apples/{id}")
        public ResponseEntity<Void> deleteApple(@PathVariable UUID id) {
            appleService.deleteApple(id);
            return ResponseEntity.noContent().build();
        }
    }

    public record AppleForm(String name) {
    }

    public record Apple(UUID id, String name) {
    }

    @Service
    public static class AppleService {

        private final List<Apple> apples = new ArrayList<>(List.of(
                new Apple(UUID.randomUUID(), "Cox's Orange Pippin"),
                new Apple(UUID.randomUUID(), "Braeburn"),
                new Apple(UUID.randomUUID(), "Granny Smith"),
                new Apple(UUID.randomUUID(), "Golden Delicious"),
                new Apple(UUID.randomUUID(), "Pink Lady"),
                new Apple(UUID.randomUUID(), "Fuji"),
                new Apple(UUID.randomUUID(), "Ingrid-Marie")
        ));

        public String getRandomAppleName() {
            return apples.get((int) (Math.random() * apples.size())).name();
        }

        public List<Apple> getApples() {
            return apples;
        }

        public void addApple(String name) {
            apples.add(new Apple(UUID.randomUUID(), name));
        }

        public void deleteApple(UUID id) {
            apples.removeIf(a -> a.id.equals(id));
        }
    }
}

