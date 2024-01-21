package com.nhnacademy.certificate.service.householdmovementaddress;

import com.nhnacademy.certificate.domain.rest.MovementAddressRequestDto;
import com.nhnacademy.certificate.entity.Household;
import com.nhnacademy.certificate.entity.HouseholdMovementAddress;
import com.nhnacademy.certificate.exception.HouseholdMovementAddressAlreadyExistsException;
import com.nhnacademy.certificate.exception.HouseholdMovementAddressNotFoundException;
import com.nhnacademy.certificate.exception.HouseholdNotFoundException;
import com.nhnacademy.certificate.repository.household.HouseholdRepository;
import com.nhnacademy.certificate.repository.householdmovementaddress.HouseholdMovementAddressRepository;
import java.time.LocalDate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HouseholdMovementAddressServiceImpl implements HouseholdMovementAddressService {

    private final HouseholdRepository householdRepository;

    private final HouseholdMovementAddressRepository householdMovementAddressRepository;

    public HouseholdMovementAddressServiceImpl(HouseholdRepository householdRepository,
                                               HouseholdMovementAddressRepository householdMovementAddressRepository) {
        this.householdRepository = householdRepository;
        this.householdMovementAddressRepository = householdMovementAddressRepository;
    }

    @Transactional
    @Override
    public HouseholdMovementAddress register(MovementAddressRequestDto requestDto) {
        HouseholdMovementAddress.Pk pk = new HouseholdMovementAddress.Pk();
        pk.setHouseholdSerialNumber(requestDto.getHouseholdSerialNumber());
        pk.setHouseMovementReportDate(requestDto.getHouseMovementReportDate());

        if (householdMovementAddressRepository.countByPk(pk) > 0) {
            throw new HouseholdMovementAddressAlreadyExistsException();
        }


        Household household = householdRepository.findById(requestDto.getHouseholdSerialNumber())
                .orElseThrow(HouseholdNotFoundException::new);

        // 기존에 존재한 last_address_yn = 'Y'인 데이터를 'N'으로 변경
        householdMovementAddressRepository.updateLastAddress(household.getHouseholdSerialNumber());

        HouseholdMovementAddress householdMovementAddress = new HouseholdMovementAddress();
        householdMovementAddress.setHousehold(household);


        householdMovementAddress.setPk(pk);
        householdMovementAddress.setHouseMovementAddress(requestDto.getHouseMovementAddress());
        householdMovementAddress.setLastAddressYn(requestDto.getLastAddressYn());

        return householdMovementAddressRepository.save(householdMovementAddress);
    }

    @Override
    public HouseholdMovementAddress modify(MovementAddressRequestDto requestDto) {

        Household household = householdRepository.findById(requestDto.getHouseholdSerialNumber())
                .orElseThrow(HouseholdNotFoundException::new);

        HouseholdMovementAddress householdMovementAddress = new HouseholdMovementAddress();
        householdMovementAddress.setHousehold(household);

        HouseholdMovementAddress.Pk pk = new HouseholdMovementAddress.Pk();
        pk.setHouseholdSerialNumber(requestDto.getHouseholdSerialNumber());
        pk.setHouseMovementReportDate(requestDto.getHouseMovementReportDate());

        householdMovementAddress.setPk(pk);
        householdMovementAddress.setHouseMovementAddress(requestDto.getHouseMovementAddress());
        householdMovementAddress.setLastAddressYn(requestDto.getLastAddressYn());

        return householdMovementAddressRepository.save(householdMovementAddress);
    }

    @Override
    public void delete(Integer serialNumber, LocalDate houseMovementReportDate) {

        HouseholdMovementAddress.Pk pk = new HouseholdMovementAddress.Pk();

        pk.setHouseholdSerialNumber(serialNumber);
        pk.setHouseMovementReportDate(houseMovementReportDate);

        if (householdMovementAddressRepository.countByPk(pk) < 1) {
            throw new HouseholdMovementAddressNotFoundException();
        }

        householdMovementAddressRepository.deleteById(pk);

    }
}
