package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dto.CarEntryDto;
import ru.itis.dto.LeaveDto;
import ru.itis.services.CarEntryService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class CarEntryController {

    private final CarEntryService carEntryService;

    @PostMapping(value = "/can-go")
    public CarEntryDto canGo(@Valid @RequestBody LeaveDto leave) {
        return carEntryService.canCarGoOut(leave);
    }
}
