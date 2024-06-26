package com.nhnacademy.certificate.controller.rest;

import com.nhnacademy.certificate.domain.rest.RelationshipRegisterRequestDto;
import com.nhnacademy.certificate.service.family.FamilyRelationshipService;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/residents/{serialNumber}/relationship")
public class RelationshipRestController {

    private final FamilyRelationshipService familyRelationshipService;

    public RelationshipRestController(FamilyRelationshipService familyRelationshipService) {
        this.familyRelationshipService = familyRelationshipService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerRelationship(@RequestBody RelationshipRegisterRequestDto relationship,
                                     @PathVariable("serialNumber") Integer residentSerialNumber) {

        familyRelationshipService.register(residentSerialNumber,
                relationship.getFamilySerialNumber(),
                relationship.getRelationship());

    }

    @PutMapping("/{familySerialNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void modifyRelationship(@RequestBody Map<String, String> request,
                                   @PathVariable("serialNumber") Integer residentSerialNumber,
                                   @PathVariable Integer familySerialNumber) {

        familyRelationshipService.modify(residentSerialNumber, familySerialNumber, request.get("relationship"));
    }


    @DeleteMapping("/{familySerialNumber}")
    public void deleteRelationship(@PathVariable("serialNumber") Integer residentSerialNumber,
                                   @PathVariable Integer familySerialNumber) {

        familyRelationshipService.deleteById(residentSerialNumber, familySerialNumber);

    }
}
