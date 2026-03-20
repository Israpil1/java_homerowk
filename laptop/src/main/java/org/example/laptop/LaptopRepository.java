package org.example.laptop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Integer> {
    // Здесь ничего писать не нужно, Spring всё сделает за тебя
}