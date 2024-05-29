package com.lvh.controller;

import com.lvh.dto.StateDto;
import com.lvh.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/states")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200/")
public class StateController {

    private final StateService stateService;

    @GetMapping("/{id}")
    public ResponseEntity<StateDto> getStateById(@PathVariable("id") Integer stateId){
        return ResponseEntity.ok(stateService.getStateById(stateId));
    }

    @GetMapping
    public ResponseEntity<List<StateDto>> getAllStates(){
        return ResponseEntity.ok(stateService.getAllStates());
    }

    @GetMapping("/country/{countryId}")
    public ResponseEntity<List<StateDto>> findAllStatesByCountryId(@PathVariable Integer countryId){
        return ResponseEntity.ok(stateService.findAllStatesByCountryId(countryId));
    }


    @GetMapping("/findByCountryCode")
    public ResponseEntity<List<StateDto>> findAllStatesByCountryCode(@RequestParam String code){
        return ResponseEntity.ok(stateService.findStatesByCountryCode(code));
    }


}
