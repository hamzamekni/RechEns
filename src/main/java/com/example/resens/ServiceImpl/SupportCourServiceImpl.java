package com.example.resens.ServiceImpl;

import com.example.resens.model.SupportCour;
import com.example.resens.repository.SupportCourRepository;
import com.example.resens.service.SupportCourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupportCourServiceImpl implements SupportCourService {

    @Autowired
    private SupportCourRepository supportCourRepository;

    @Override
    public SupportCour saveSupportCour(SupportCour supportCour) {
        return supportCourRepository.save(supportCour);
    }

    @Override
    public SupportCour updateSupportCour(Long id, SupportCour updatedSupportCour) {
        SupportCour existingSupportCour = supportCourRepository.findById(id).orElse(null);
        if (existingSupportCour != null) {
            updatedSupportCour.setSupportCour_Id(existingSupportCour.getSupportCour_Id());
            return supportCourRepository.save(updatedSupportCour);
        }
        return null;
    }

    @Override
    public void deleteSupportCour(Long id) {
        supportCourRepository.deleteById(id);
    }

    @Override
    public SupportCour getSupportCourById(Long id) {
        return supportCourRepository.findById(id).orElse(null);
    }

    @Override
    public List<SupportCour> getAllSupportCours() {
        return supportCourRepository.findAll();
    }
}