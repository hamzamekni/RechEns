package com.example.resens.service;

import com.example.resens.model.Adress;

import java.util.List;

public interface AdressService {
    List<Adress> getAllAdresses();
    Adress getAdressById(Long id);
    Adress saveAdress(Adress adress);
    Adress updateAdress(Long id, Adress updatedAdress);
    void deleteAdressById(Long id);
}
