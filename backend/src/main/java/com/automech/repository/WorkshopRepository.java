package com.automech.repository;

import com.automech.model.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Long> {
    List<Workshop> findByNameContainingIgnoreCase(String keyword);
    List<Workshop> findByOpenNowTrue();
}
