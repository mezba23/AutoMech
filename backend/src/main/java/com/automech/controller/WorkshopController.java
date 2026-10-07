package com.automech.controller;

import com.automech.model.Workshop;
import com.automech.service.ScraperService;
import com.automech.service.WorkshopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workshops")
@CrossOrigin(origins = "*")
public class WorkshopController {

    private final WorkshopService workshopService;
    private final ScraperService scraperService;

    public WorkshopController(WorkshopService workshopService, ScraperService scraperService) {
        this.workshopService = workshopService;
        this.scraperService = scraperService;
    }

    @GetMapping
    public ResponseEntity<List<Workshop>> getAllWorkshops() {
        return ResponseEntity.ok(workshopService.getAllWorkshops());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workshop> getWorkshopById(@PathVariable Long id) {
        return workshopService.getWorkshopById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<Workshop>> getNearbyWorkshops(
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam(defaultValue = "10.0") double radiusKm) {
        List<Workshop> nearby = workshopService.findNearbyWorkshops(lat, lng, radiusKm);
        return ResponseEntity.ok(nearby);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Workshop>> searchWorkshops(@RequestParam String q) {
        return ResponseEntity.ok(workshopService.searchWorkshops(q));
    }

    @PostMapping("/sync-scrape")
    public ResponseEntity<List<Workshop>> triggerScrapeAndSync(
            @RequestParam(defaultValue = "Bengaluru") String location) {
        List<Workshop> scraped = scraperService.scrapeNearbyGarages(location);
        for (Workshop w : scraped) {
            workshopService.saveWorkshop(w);
        }
        return ResponseEntity.ok(workshopService.getAllWorkshops());
    }
}
