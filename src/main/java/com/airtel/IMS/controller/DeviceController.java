package com.airtel.IMS.controller;

import com.airtel.IMS.entity.Device;
import com.airtel.IMS.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/devices")
@CrossOrigin(origins = "*")
public class DeviceController {
    
    @Autowired
    private DeviceService deviceService;
    
    @PostMapping
    public ResponseEntity<?> createDevice(@RequestBody Device device) {
        try {
            Device createdDevice = deviceService.createDevice(device);
            return new ResponseEntity<>(createdDevice, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        List<Device> devices = deviceService.getAllDevices();
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getDeviceById(@PathVariable Long id) {
        java.util.Optional<Device> device = deviceService.getDeviceById(id);
        if (device.isPresent()) {
            return new ResponseEntity<>(device.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Device not found", HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/available")
    public ResponseEntity<List<Device>> getAvailableDevices() {
        List<Device> devices = deviceService.getDevicesByStatus(Device.DeviceStatus.AVAILABLE);
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDevice(@PathVariable Long id, @RequestBody Device device) {
        try {
            Device updatedDevice = deviceService.updateDevice(id, device);
            return new ResponseEntity<>(updatedDevice, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateDeviceStatus(@PathVariable Long id, @RequestParam Device.DeviceStatus status) {
        try {
            Device updatedDevice = deviceService.updateDeviceStatus(id, status);
            return new ResponseEntity<>(updatedDevice, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDevice(@PathVariable Long id) {
        try {
            deviceService.deleteDevice(id);
            return new ResponseEntity<>("Device deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Device>> searchDevices(@RequestParam String serialNumber) {
        List<Device> devices = deviceService.searchDevicesBySerialNumber(serialNumber);
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }
}