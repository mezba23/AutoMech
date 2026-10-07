package com.automech.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "workshops")
public class Workshop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String address;
    private Double latitude;
    private Double longitude;
    private String contactNumber;
    private Double rating;
    private Boolean openNow;

    @ElementCollection
    private List<String> servicesOffered;

    public Workshop() {}

    public Workshop(String name, String address, Double latitude, Double longitude, String contactNumber, Double rating, Boolean openNow, List<String> servicesOffered) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.contactNumber = contactNumber;
        this.rating = rating;
        this.openNow = openNow;
        this.servicesOffered = servicesOffered;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public Boolean getOpenNow() { return openNow; }
    public void setOpenNow(Boolean openNow) { this.openNow = openNow; }

    public List<String> getServicesOffered() { return servicesOffered; }
    public void setServicesOffered(List<String> servicesOffered) { this.servicesOffered = servicesOffered; }
}
