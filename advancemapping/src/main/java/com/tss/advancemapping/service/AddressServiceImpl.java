package com.tss.advancemapping.service;

import com.tss.advancemapping.dto.request.AddressRequestDto;
import com.tss.advancemapping.dto.response.AddressResponseDto;
import com.tss.advancemapping.entity.Address;
import com.tss.advancemapping.entity.Student;
import com.tss.advancemapping.exception.BusinessRuleException;
import com.tss.advancemapping.exception.ResourceNotFoundException;
import com.tss.advancemapping.mapper.AddressMapper;
import com.tss.advancemapping.repository.AddressRepository;
import com.tss.advancemapping.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final StudentRepository studentRepository;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(StudentRepository studentRepository,
                              AddressRepository addressRepository,
                              AddressMapper addressMapper) {
        this.studentRepository = studentRepository;
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public AddressResponseDto findByStudentId(Integer id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student", id));

        Address address = student.getAddress();

        if (address == null) {
            throw new ResourceNotFoundException("Address for student", id);
        }

        return addressMapper.toDto(address);
    }

    @Override
    public AddressResponseDto updateAddress(Integer id, AddressRequestDto addressRequestDto) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student", id));

        Address address = student.getAddress();

        if (address == null) {
            throw new ResourceNotFoundException("Address for student", id);
        }

        address.setCity(addressRequestDto.getCity());
        address.setState(addressRequestDto.getState());
        address.setPincode(addressRequestDto.getPincode());

        Address updatedAddress = addressRepository.save(address);

        return addressMapper.toDto(updatedAddress);
    }
}