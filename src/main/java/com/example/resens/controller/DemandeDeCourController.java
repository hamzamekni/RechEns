package com.example.resens.controller;

import com.example.resens.ServiceImpl.DemandeDeCourServiceImpl;
import com.example.resens.dto.ConfirmationResponse;
import com.example.resens.dto.DemandeRequest;
import com.example.resens.dto.RegisterResponse;
import com.example.resens.enumeration.Statut_Demande;
import com.example.resens.exceptions.UserException;
import com.example.resens.model.*;
import com.example.resens.repository.AdressRepository;
import com.example.resens.repository.EtudiantRepository;
import com.example.resens.repository.MatiereRepository;
import com.example.resens.repository.TeacherRepository;
import com.example.resens.service.DemandeDeCourService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demandeDeCours")
@RequiredArgsConstructor
public class DemandeDeCourController {

    @Autowired
    private DemandeDeCourService demandeDeCourService;
    private final DemandeDeCourServiceImpl demandeDeCourServiceimpl;
    private EtudiantRepository etudiantRepository;
    private MatiereRepository matiereRepository;
    private AdressRepository adressRepository;
    private TeacherRepository teacherRepository;
    private Teacher teacher;
    private Etudiant etudiant;
    private Adress adress;
    private Matiere matiere;



    @PostMapping
    public DemandeDeCour saveDemandeDeCour(@RequestBody DemandeDeCour demandeDeCour,
                                           @RequestParam Long adress_id, Long matiere_id,
                                           @RequestParam Long etudiant_id,
                                           @RequestParam Long teacherId) {
        return demandeDeCourService.saveDemandeDeCour(demandeDeCour, adress_id, matiere_id,
                                                                                etudiant_id,
                                                                                teacherId);
    }

    @PutMapping("/{id}")
    public DemandeDeCour updateDemandeDeCour(@PathVariable Long id, @RequestBody DemandeDeCour updatedDemandeDeCour) {
        return demandeDeCourService.updateDemandeDeCour(id, updatedDemandeDeCour);
    }

    @GetMapping("/deleteDemande/{id}")
    public void deleteDemandeDeCour(@PathVariable Long id) {
        demandeDeCourService.deleteDemandeDeCour(id);
    }

    @GetMapping("/{id}")
    public DemandeDeCour getDemandeDeCourById(@PathVariable Long id) {
        return demandeDeCourService.getDemandeDeCourById(id);
    }

    @GetMapping
    public List<DemandeDeCour> getAllDemandeDeCours() {
        return demandeDeCourService.getAllDemandeDeCours();
    }

    @PostMapping("/demandeForm")
    public ResponseEntity<RegisterResponse> demandeForm(@RequestBody DemandeRequest demandeRequest) {


            RegisterResponse registerResponse = new RegisterResponse();
            try {
                demandeDeCourService.demadeForm(demandeRequest, Statut_Demande.IN_PROGRESS, demandeRequest.getTeacherId(),
                        demandeRequest.getEtudiantId(), demandeRequest.getMatiereId(), demandeRequest.getAdressId());
                registerResponse.setMessageResponse("Demand Created");
                return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);
            } catch (UserException e) {
                registerResponse.setMessageResponse(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(registerResponse);
            } catch (Exception e) {
                registerResponse.setMessageResponse(e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(registerResponse);
            }
    }

    @GetMapping("/ConfirmDemand/{id}")
    public ResponseEntity<ConfirmationResponse> confirmDemande(@PathVariable Long id) {
        ConfirmationResponse confirmationMessage = new ConfirmationResponse();
        try {
            String response = demandeDeCourServiceimpl.confirmDemande(id);
            if (response.equals("successfully")) {
                confirmationMessage.setMessageResponse("This Demande Is Sent To The Teacher Now");
            }
            else if (response.equals("already")){
                confirmationMessage.setMessageResponse("You Did Confirm This Demand");
            }
            else{
                confirmationMessage.setMessageResponse(response);
            }
            confirmationMessage.setConfirmedEmail(true);
            return ResponseEntity.ok(confirmationMessage);
        }catch (UserException e) {
            confirmationMessage.setMessageResponse(e.getMessage());
            confirmationMessage.setConfirmedEmail(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(confirmationMessage);
        } catch (Exception e) {
            confirmationMessage.setMessageResponse(e.getMessage());
            confirmationMessage.setConfirmedEmail(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(confirmationMessage);
        }
    }
}