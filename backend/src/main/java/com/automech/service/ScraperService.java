package com.automech.service;

import com.automech.model.Workshop;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ScraperService {

    /**
     * Simulates fetching and scraping garage listings from public directories.
     * Can be wired with JSoup or Selenium for real-time web crawling.
     */
    public List<Workshop> scrapeNearbyGarages(String locationQuery) {
        List<Workshop> scraped = new ArrayList<>();
        scraped.add(new Workshop(
                "Apex Precision Auto Care",
                "104 Outer Ring Rd, Koramangala, Bengaluru",
                12.9352, 77.6245,
                "+91-9876501234",
                4.8, true,
                Arrays.asList("Engine Diagnostics", "Brake Overhaul", "Towing Assist")
        ));
        scraped.add(new Workshop(
                "Metro QuickFix Garage",
                "45 Indiranagar 100ft Rd, Bengaluru",
                12.9719, 77.6412,
                "+91-9876505678",
                4.6, true,
                Arrays.asList("Oil Change", "Battery Replacement", "Wheel Alignment")
        ));
        scraped.add(new Workshop(
                "Whitefield Express Mechanics",
                "12 ITPL Main Rd, Whitefield, Bengaluru",
                12.9698, 77.7499,
                "+91-9876509988",
                4.4, false,
                Arrays.asList("Bodywork Repair", "Electrical Diagnostics", "AC Servicing")
        ));
        return scraped;
    }
}
