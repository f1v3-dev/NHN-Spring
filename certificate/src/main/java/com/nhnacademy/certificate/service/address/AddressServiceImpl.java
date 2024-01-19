package com.nhnacademy.certificate.service.address;

import com.nhnacademy.certificate.entity.HouseholdMovementAddress;
import com.nhnacademy.certificate.repository.address.AddressRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<HouseholdMovementAddress> findBySerialNumber(Integer serialNumber) {
        return addressRepository.findBySerialNumber(serialNumber);
    }
}
