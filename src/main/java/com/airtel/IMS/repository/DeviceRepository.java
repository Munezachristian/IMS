package com.airtel.IMS.repository;

import com.airtel.IMS.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    Optional<Device> findBySerialNumber(String serialNumber);
    List<Device> findByDeviceType(Device.DeviceType deviceType);
    List<Device> findByCurrentStatus(Device.DeviceStatus status);
    List<Device> findBySerialNumberContaining(String serialNumber);
    boolean existsBySerialNumber(String serialNumber);
}