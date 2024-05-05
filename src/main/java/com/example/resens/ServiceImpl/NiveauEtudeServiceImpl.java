package com.example.resens.ServiceImpl;

import com.example.resens.model.NiveauEtude;
import com.example.resens.repository.NiveauEtudeRepository;
import com.example.resens.service.NiveauEtudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NiveauEtudeServiceImpl implements NiveauEtudeService {

    @Autowired
    private NiveauEtudeRepository niveauEtudeRepository;

    @Override
    public NiveauEtude saveNiveauEtude(NiveauEtude niveauEtude) {
        return niveauEtudeRepository.save(niveauEtude);
    }

    @Override
    public NiveauEtude updateNiveauEtude(Long id, NiveauEtude updatedNiveauEtude) {
        NiveauEtude existingNiveauEtude = niveauEtudeRepository.findById(id).orElse(null);
        if (existingNiveauEtude != null) {
            updatedNiveauEtude.setNiveauEtude_Id(existingNiveauEtude.getNiveauEtude_Id());
            return niveauEtudeRepository.save(updatedNiveauEtude);
        }
        return null;
    }

    @Override
    public void deleteNiveauEtude(Long id) {
        niveauEtudeRepository.deleteById(id);
    }

    @Override
    public NiveauEtude getNiveauEtudeById(Long id) {
        return niveauEtudeRepository.findById(id).orElse(null);
    }

    @Override
    public List<NiveauEtude> getAllNiveauEtudes() {
        return niveauEtudeRepository.findAll();
    }
}