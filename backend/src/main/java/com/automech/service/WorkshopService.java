package com.automech.service;

import com.automech.model.Workshop;
import com.automech.repository.WorkshopRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkshopService {

    private final WorkshopRepository workshopRepository;

    public WorkshopService(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    public List<Workshop> getAllWorkshops() {
        return workshopRepository.findAll();
    }

    public Optional<Workshop> getWorkshopById(Long id) {
        return workshopRepository.findById(id);
    }

    public Workshop saveWorkshop(Workshop workshop) {
        return workshopRepository.save(workshop);
    }

    public List<Workshop> searchWorkshops(String keyword) {
        return workshopRepository.findByNameContainingIgnoreCase(keyword);
    }

    /**
     * Find workshops within a specified radial distance (in kilometers)
     * using the Haversine formula.
     */
    public List<Workshop> findNearbyWorkshops(double userLat, double userLng, double radiusKm) {
        return workshopRepository.findAll().stream()
                .filter(w -> w.getLatitude() != null && w.getLongitude() != null)
                .filter(w -> calculateDistanceKm(userLat, userLng, w.getLatitude(), w.getLongitude()) <= radiusKm)
                .sorted(Comparator.comparingDouble(w -> calculateDistanceKm(userLat, userLng, w.getLatitude(), w.getLongitude())))
                .collect(Collectors.toList());
    }

    private double calculateDistanceKm(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS_KM = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_KM * c;
    }
}
