package com.automech;

import com.automech.model.Workshop;
import com.automech.repository.WorkshopRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class AutoMechApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoMechApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(WorkshopRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Workshop(
                        "Autotech Multi-Brand Garage",
                        "88 Bannerghatta Main Rd, Bengaluru",
                        12.8950, 77.5990,
                        "+91-9876512345",
                        4.7, true,
                        Arrays.asList("Periodic Service", "Dent & Paint", "Suspension Check")
                ));
                repository.save(new Workshop(
                        "Speedy Wheels Car Repair",
                        "15 Outer Ring Rd, Marathahalli, Bengaluru",
                        12.9560, 77.7010,
                        "+91-9876523456",
                        4.5, true,
                        Arrays.asList("Tyre Change", "Engine Tuneup", "Oil Filter Replacement")
                ));
            }
        };
    }
}
