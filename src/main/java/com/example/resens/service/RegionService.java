package com.example.resens.service;

import com.example.resens.model.Region;

import java.util.List;

public interface RegionService {
    Region saveRegion(Region region, Long ville_id);
    Region updateRegion(Long id, Region updatedRegion);
    void deleteRegion(Long id);
    Region getRegionById(Long id);
    List<Region> getAllRegions();
}
