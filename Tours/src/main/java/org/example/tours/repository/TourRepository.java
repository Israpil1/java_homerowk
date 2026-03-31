package org.example.tours.repository;

import org.example.tours.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    // tourRepository.findAll() - получить все туры
    // tourRepository.save(tour) - сохранить новый тур
}