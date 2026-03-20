package org.example.laptop;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "laptops")
@Data
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String brand;
    private Integer ssd;
    private String gpu;
    private String cpu;
    private String os;
    private String matrix;
    private String resolution;
    private Boolean touch;
    private Boolean keyboard;

}