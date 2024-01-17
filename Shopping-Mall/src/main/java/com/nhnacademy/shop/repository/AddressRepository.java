package com.nhnacademy.shop.repository;

import com.nhnacademy.shop.entity.Address;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {


    Long countByAddressId(Integer addressId);

    List<Address> findByUser_UserId(String userId);

}
