package com.example.resens.controller;

import com.example.resens.model.Region;
import com.example.resens.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping
    public List<Region> getAllRegions() {
        return regionService.getAllRegions();
    }

    @GetMapping("/{id}")
    public Region getRegionById(@PathVariable Long id) {
        return regionService.getRegionById(id);
    }

    @PostMapping
    public Region saveRegion(@RequestBody Region region) {
        return regionService.saveRegion(region);
    }

    @PutMapping("/{id}")
    public Region updateRegion(@PathVariable Long id, @RequestBody Region updatedRegion) {
        return regionService.updateRegion(id, updatedRegion);
    }

    @DeleteMapping("/{id}")
    public void deleteRegion(@PathVariable Long id) {
        regionService.deleteRegion(id);
    }
}