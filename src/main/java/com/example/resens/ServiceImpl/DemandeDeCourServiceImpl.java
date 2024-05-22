package com.example.resens.ServiceImpl;

import com.example.resens.dto.DemandeRequest;
import com.example.resens.enumeration.Statut_Demande;
import com.example.resens.enumeration.Statut_Etude_Presentiel;
import com.example.resens.exceptions.TeacherException;
import com.example.resens.model.*;
import com.example.resens.repository.*;
import com.example.resens.service.DemandeDeCourService;
import com.example.resens.service.EmailRegistrationService;
import com.example.resens.service.JwtService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DemandeDeCourServiceImpl implements DemandeDeCourService {
    private static final String CONFIRMATION_URL = "http://localhost:8081/demandeDeCours/ConfirmDemand/%s";
    private static final String DELETE_DEMANDE_URL = "http://localhost:8081/demandeDeCours/%s";
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

    private final EmailRegistrationService emailRegistrationService;




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
    @Transactional
    @Override
    public void demadeForm(DemandeRequest demandeRequest, Statut_Demande statutDemande,
                          Long teacherId, Long etudiantId, Long matiereId, Long adressId) {
        Etudiant optionalEtudiant = etudiantRepository.findByEtudiantId(etudiantId);
        Teacher optionalTeacher = teacherRepository.findByTeacherId(teacherId);
        Adress optionalAdress = adressRepository.findByAdressId(adressId);
        Matiere optionalMatiere = matiereRepository.findByMatiereId(matiereId);

        var demande = DemandeDeCour.builder()
                .titre_demande(demandeRequest.getTitre_demande())
                .detail_demande(demandeRequest.getDetail_demande())
                .locale(demandeRequest.getLocale())
                .statutDemande(statutDemande)
                .prix_max(demandeRequest.getPrix_max())
                .prix_min(demandeRequest.getPrix_min())
                .adress(optionalAdress)
                .matiere(optionalMatiere)
                .etudiant(optionalEtudiant)
                .teacher(optionalTeacher)
                .build();
        demandeDeCourRepository.save(demande);
        try {
            System.out.println(demande.getDemandeDeCour_Id());
            emailRegistrationService.send(
                    "hamzamekni4@gmail.com",
                    demande.getTitre_demande(),
                    demande.getDetail_demande(),
                    demande.getEtudiant().getUsers().getFirstName(),
                    demande.getTeacher().getFirstName(),
                    demande.getMatiere().getMatiere_name(),
                    demande.getAdress().getRoad_adress(),
                    "confirmed-demande",
                    String.format(CONFIRMATION_URL,demande.getDemandeDeCour_Id()),
                    demande.getDemandeDeCour_Id()
            );
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public String confirmDemande(Long id) {
        DemandeDeCour demandeDeCour= demandeDeCourRepository.getReferenceById(id);
        if (!demandeDeCour.getStatutDemande().equals(Statut_Demande.DONE)) {
            demandeDeCour.setStatutDemande(Statut_Demande.valueOf("DONE"));
            demandeDeCourRepository.save(demandeDeCour);
            try {
                emailRegistrationService.sendDemandeToTeacher(
                        "hamzamekni4@gmail.com",
                        demandeDeCour.getEtudiant().getUsers().getFirstName(),
                        demandeDeCour.getTeacher().getEmail(),
                        demandeDeCour.getEtudiant().getUsers().getEmail(),
                        demandeDeCour.getEtudiant().getUsers().getPhoneNumber(),
                        "demande-De-Cour",
                        demandeDeCour.getTeacher().getTeacherId()
                );
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return "successfully";

        }
        else {
            return "already";
        }

    }


}
