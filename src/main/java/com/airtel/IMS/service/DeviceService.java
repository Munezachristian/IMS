package com.airtel.IMS.service;

import com.airtel.IMS.entity.Device;
import com.airtel.IMS.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {
    
    @Autowired
    private DeviceRepository deviceRepository;
    
    public Device createDevice(Device device) {
        if (deviceRepository.existsBySerialNumber(device.getSerialNumber())) {
            throw new RuntimeException("Device with serial number " + device.getSerialNumber() + " already exists!");
        }
        device.setCreatedAt(LocalDateTime.now());
        device.setUpdatedAt(LocalDateTime.now());
        return deviceRepository.save(device);
    }
    
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }
    
    public Optional<Device> getDeviceById(Long id) {
        return deviceRepository.findById(id);
    }
    
    public Optional<Device> getDeviceBySerialNumber(String serialNumber) {
        return deviceRepository.findBySerialNumber(serialNumber);
    }
    
    public List<Device> getDevicesByType(Device.DeviceType deviceType) {
        return deviceRepository.findByDeviceType(deviceType);
    }
    
    public List<Device> getDevicesByStatus(Device.DeviceStatus status) {
        return deviceRepository.findByCurrentStatus(status);
    }
    
    public Device updateDevice(Long id, Device deviceDetails) {
        Device existingDevice = deviceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Device not found with id: " + id));
        existingDevice.setDeviceType(deviceDetails.getDeviceType());
        existingDevice.setBrand(deviceDetails.getBrand());
        existingDevice.setModel(deviceDetails.getModel());
        existingDevice.setSpecifications(deviceDetails.getSpecifications());
        existingDevice.setPurchaseDate(deviceDetails.getPurchaseDate());
        existingDevice.setWarrantyExpiry(deviceDetails.getWarrantyExpiry());
        existingDevice.setUpdatedAt(LocalDateTime.now());
        return deviceRepository.save(existingDevice);
    }
    
    public Device updateDeviceStatus(Long id, Device.DeviceStatus status) {
        Device device = deviceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Device not found with id: " + id));
        device.setCurrentStatus(status);
        device.setUpdatedAt(LocalDateTime.now());
        return deviceRepository.save(device);
    }
    
    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }
    
    public List<Device> searchDevicesBySerialNumber(String serialNumber) {
        return deviceRepository.findBySerialNumberContaining(serialNumber);
    }
}