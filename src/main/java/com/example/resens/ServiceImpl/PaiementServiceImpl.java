package com.example.resens.ServiceImpl;

import com.example.resens.model.Etudiant;
import com.example.resens.model.Paiement;
import com.example.resens.model.Teacher;
import com.example.resens.repository.EtudiantRepository;
import com.example.resens.repository.PaiementRepository;
import com.example.resens.repository.TeacherRepository;
import com.example.resens.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaiementServiceImpl implements PaiementService {

    @Autowired
    private PaiementRepository paiementRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public Paiement savePaiement(Paiement paiement, Long etudiantId, Long teacherId) {
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(etudiantId);
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
        System.out.println(optionalTeacher);
        if (optionalEtudiant.isPresent()){
            paiement.setEtudiant(optionalEtudiant.get());
        }
        if (optionalTeacher.isPresent()){
            paiement.setTeacher(optionalTeacher.get());
        }
        return paiementRepository.save(paiement);
    }

    @Override
    public Paiement updatePaiement(Long id, Paiement updatedPaiement){
        Paiement existingPaiement = paiementRepository.findById(id).orElse(null);
        if (existingPaiement != null) {
            updatedPaiement.setPaiementId(existingPaiement.getPaiementId());
            return paiementRepository.save(updatedPaiement);
        }
        return null;
    }

    @Override
    public void deletePaiement(Long id) {
        paiementRepository.deleteById(id);
    }

    @Override
    public Paiement getPaiementById(Long id) {
        return paiementRepository.findById(id).orElse(null);
    }

    @Override
    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }
}
