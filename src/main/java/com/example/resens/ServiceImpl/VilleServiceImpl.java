package com.example.resens.ServiceImpl;

import com.example.resens.model.Ville;
import com.example.resens.repository.VilleRepository;
import com.example.resens.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VilleServiceImpl implements VilleService {

    @Autowired
    private VilleRepository villeRepository;

    @Override
    public List<Ville> getAllVilles() {
        return villeRepository.findAll();
    }

    @Override
    public Ville getVilleById(Long id) {
        return villeRepository.findById(id).orElse(null);
    }

    @Override
    public Ville saveVille(Ville ville) {
        return villeRepository.save(ville);
    }

    @Override
    public Ville updateVille(Long id, Ville updatedVille) {
        Ville existingVille = villeRepository.findById(id).orElse(null);
        if (existingVille != null) {
            // Set the ID of the existingVille to the updatedVille
            updatedVille.setVille_Id(existingVille.getVille_Id());
            return villeRepository.save(updatedVille);
        }
        return null;
    }

    @Override
    public void deleteVilleById(Long id) {
        villeRepository.deleteById(id);
    }
}