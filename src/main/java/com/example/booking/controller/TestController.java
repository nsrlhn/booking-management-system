package com.example.booking.controller;

import com.example.booking.model.Company;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"test"})
@Tag(name = "test")
public class TestController {

    @GetMapping(value = "EntityNotFoundException")
    public Object EntityNotFoundException() {
        throw new EntityNotFoundException();
    }

    @GetMapping(value = "InvalidDataAccessApiUsageException")
    public Object InvalidDataAccessApiUsageException() {
        throw new InvalidDataAccessApiUsageException("haha");
    }

    @GetMapping(value = "InternalError")
    public Object InternalError() {
        throw new InternalError();
    }

    @SneakyThrows
    @GetMapping(value = "ServletRequestBindingException")
    public Object ServletRequestBindingException() {
        throw new ServletRequestBindingException("haha");
    }

    @GetMapping(value = "NullPointerException")
    public Object NullPointerException() {
        Company company = null;
        return company.getId();
    }
}
