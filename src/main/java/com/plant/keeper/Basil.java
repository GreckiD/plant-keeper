package com.plant.keeper;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

@Entity
@Table(name = "basil")
@Transactional
public class Basil {

    public Basil(Integer soilMoisturePercentage, Double temperature, LocalDateTime date) {
        this.soilMoisturePercentage = soilMoisturePercentage;
        this.temperature = temperature;
        this.date = date;
    }

    @Id
    @SequenceGenerator(
            name = "basil_sequence",
            sequenceName = "basil_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "basil_sequence"
    )
    private Long id;
    private Integer soilMoisturePercentage;
    private LocalDateTime date;
    private Double temperature;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSoilMoisturePercentage() {
        return soilMoisturePercentage;
    }

    public void setSoilMoisturePercentage(Integer soilMoisturePercentage) {
        this.soilMoisturePercentage = soilMoisturePercentage;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "data{" +
                "  id=" + id +
                ", date='" + date + '\'' +
                ", soilMoisture=" + soilMoisturePercentage +
                ", temperature=" + temperature +
                '}';
    }

}
