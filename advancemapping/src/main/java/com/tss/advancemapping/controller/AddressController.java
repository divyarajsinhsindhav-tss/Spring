package com.tss.advancemapping.controller;

import com.tss.advancemapping.dto.request.AddressRequestDto;
import com.tss.advancemapping.dto.response.AddressResponseDto;
import com.tss.advancemapping.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student/address")
public class AddressController {

    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<AddressResponseDto> getAddress(@RequestParam Integer studentId) {
        return ResponseEntity.ok().body(addressService.findByStudentId(studentId));
    }

    @PutMapping
    public ResponseEntity<AddressResponseDto> updateStudent(@RequestParam Integer studentId, @RequestBody AddressRequestDto addressRequestDto) {
        return ResponseEntity.ok().body(addressService.updateAddress(studentId, addressRequestDto));
    }

}
