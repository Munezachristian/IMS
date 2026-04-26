package com.airtel.IMS.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "device")
public class Device {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String serialNumber;
    
    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;
    
    private String brand;
    private String model;
    
    @Column(length = 1000)
    private String specifications;
    
    @Enumerated(EnumType.STRING)
    private DeviceStatus currentStatus;
    
    private LocalDate purchaseDate;
    private LocalDate warrantyExpiry;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public enum DeviceType {
        LAPTOP, DESKTOP, MOBILE
    }
    
    public enum DeviceStatus {
        AVAILABLE, ASSIGNED, UNDER_MAINTENANCE, LOST, RETIRED
    }
    
    public Device() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
    public DeviceType getDeviceType() { return deviceType; }
    public void setDeviceType(DeviceType deviceType) { this.deviceType = deviceType; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getSpecifications() { return specifications; }
    public void setSpecifications(String specifications) { this.specifications = specifications; }
    public DeviceStatus getCurrentStatus() { return currentStatus; }
    public void setCurrentStatus(DeviceStatus currentStatus) { this.currentStatus = currentStatus; }
    public LocalDate getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }
    public LocalDate getWarrantyExpiry() { return warrantyExpiry; }
    public void setWarrantyExpiry(LocalDate warrantyExpiry) { this.warrantyExpiry = warrantyExpiry; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}