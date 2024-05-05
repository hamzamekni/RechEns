package com.example.resens.controller;

import com.example.resens.model.Adress;
import com.example.resens.service.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adresses")
public class AdressController {

    @Autowired
    private AdressService adressService;

    @GetMapping
    public List<Adress> getAllAdresses() {
        return adressService.getAllAdresses();
    }

    @GetMapping("/{id}")
    public Adress getAdressById(@PathVariable Long id) {
        return adressService.getAdressById(id);
    }

    @PostMapping
    public Adress saveAdress(@RequestBody Adress adress) {
        return adressService.saveAdress(adress);
    }

    @PutMapping("/{id}")
    public Adress updateAdress(@PathVariable Long id, @RequestBody Adress updatedAdress) {
        return adressService.updateAdress(id, updatedAdress);
    }

    @DeleteMapping("/{id}")
    public void deleteAdressById(@PathVariable Long id) {
        adressService.deleteAdressById(id);
    }
}