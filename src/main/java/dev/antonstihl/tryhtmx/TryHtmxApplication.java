package dev.antonstihl.tryhtmx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@SpringBootApplication
public class TryHtmxApplication {

    public static void main(String[] args) {
        SpringApplication.run(TryHtmxApplication.class, args);
    }

    @Controller
    public class AppleController {
        private final AppleService appleService;

        public AppleController(AppleService appleService) {
            this.appleService = appleService;
        }

        @GetMapping("/apple")
        public String apple(Model model) {
            model.addAttribute("applename", appleService.getRandomAppleName());
            return "apples";
        }
    }

    @Service
    public class AppleService {
        public String getRandomAppleName() {
            var Apples = List.of(
                    "Cox's Orange Pippin",
                    "Braeburn",
                    "Granny Smith",
                    "Golden Delicious",
                    "Pink Lady",
                    "Fuji",
                    "Ingrid-Marie"
            );
            return Apples.get((int) (Math.random() * Apples.size()));
        }
    }
}

