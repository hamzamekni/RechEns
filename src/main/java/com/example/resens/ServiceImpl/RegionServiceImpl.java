package com.example.resens.ServiceImpl;

import com.example.resens.model.Etudiant;
import com.example.resens.model.Region;
import com.example.resens.model.Ville;
import com.example.resens.repository.RegionRepository;
import com.example.resens.repository.VilleRepository;
import com.example.resens.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private VilleRepository villeRepository;

    @Override
    public Region saveRegion(Region region, Long ville_id) {
        Optional<Ville> optionalVille = villeRepository.findById(ville_id);
        if (optionalVille.isPresent()){
            region.setVilles(optionalVille.get());
        }
        return regionRepository.save(region);
    }

    @Override
    public Region updateRegion(Long id, Region updatedRegion) {
        Region existingRegion = regionRepository.findById(id).orElse(null);
        if (existingRegion != null) {
            updatedRegion.setRegion_Id(existingRegion.getRegion_Id());
            return regionRepository.save(updatedRegion);
        }
        return null;
    }

    @Override
    public void deleteRegion(Long id) {
        regionRepository.deleteById(id);
    }

    @Override
    public Region getRegionById(Long id) {
        return regionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }
}