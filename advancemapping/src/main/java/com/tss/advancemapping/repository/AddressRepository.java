package com.tss.advancemapping.repository;

import com.tss.advancemapping.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
