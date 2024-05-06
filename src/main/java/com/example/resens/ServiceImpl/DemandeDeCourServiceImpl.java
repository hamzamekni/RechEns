package com.example.resens.ServiceImpl;

import com.example.resens.model.*;
import com.example.resens.repository.*;
import com.example.resens.service.DemandeDeCourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandeDeCourServiceImpl implements DemandeDeCourService {

    @Autowired
    private DemandeDeCourRepository demandeDeCourRepository;
    @Autowired
    private AdressRepository adressRepository;
    @Autowired
    private MatiereRepository matiereRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public DemandeDeCour saveDemandeDeCour(DemandeDeCour demandeDeCour, Long adress_id, Long matiere_id,
                                           Long etudiant_id,
                                           Long teacherId) {
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(etudiant_id);
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
        Optional<Adress> optionalAdress = adressRepository.findById(adress_id);
        Optional<Matiere> optionalMatiere = matiereRepository.findById(matiere_id);

        if (optionalEtudiant.isPresent()){
            demandeDeCour.setEtudiant(optionalEtudiant.get());
        }
        if (optionalAdress.isPresent()){
            demandeDeCour.setAdress(optionalAdress.get());
        }
        if (optionalMatiere.isPresent()){
            demandeDeCour.setMatiere(optionalMatiere.get());
        }
        if (optionalTeacher.isPresent()){
            demandeDeCour.setTeacher(optionalTeacher.get());
        }

        return demandeDeCourRepository.save(demandeDeCour);
    }

    @Override
    public DemandeDeCour updateDemandeDeCour(Long id, DemandeDeCour updatedDemandeDeCour) {
        DemandeDeCour existingDemandeDeCour = demandeDeCourRepository.findById(id).orElse(null);
        if (existingDemandeDeCour != null) {
            updatedDemandeDeCour.setDemandeDeCour_Id(existingDemandeDeCour.getDemandeDeCour_Id());
            return demandeDeCourRepository.save(updatedDemandeDeCour);
        }
        return null;
    }

    @Override
    public void deleteDemandeDeCour(Long id) {
        demandeDeCourRepository.deleteById(id);
    }

    @Override
    public DemandeDeCour getDemandeDeCourById(Long id) {
        return demandeDeCourRepository.findById(id).orElse(null);
    }

    @Override
    public List<DemandeDeCour> getAllDemandeDeCours() {
        return demandeDeCourRepository.findAll();
    }
}
