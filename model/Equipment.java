package com.maintainx.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "equipment", uniqueConstraints = @UniqueConstraint(columnNames = "serial_number"))
public class Equipment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Size(max = 120)
    private String name;
    @NotBlank @Size(max = 80)
    private String category;
    @NotBlank @Size(max = 120)
    private String location;
    @Column(name = "serial_number", nullable = false, unique = true)
    @NotBlank @Size(max = 80)
    private String serialNumber;
    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private EquipmentStatus status = EquipmentStatus.ACTIVE;
    private LocalDate purchaseDate;

    public Equipment() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
    public EquipmentStatus getStatus() { return status; }
    public void setStatus(EquipmentStatus status) { this.status = status; }
    public LocalDate getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }
}
