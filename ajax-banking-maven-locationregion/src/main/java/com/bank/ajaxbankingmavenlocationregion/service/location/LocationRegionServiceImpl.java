package com.bank.ajaxbankingmavenlocationregion.service.location;

import com.bank.ajaxbankingmavenlocationregion.model.LocationRegion;
import com.bank.ajaxbankingmavenlocationregion.repository.LocationRegionRepository;
import com.bank.ajaxbankingmavenlocationregion.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class LocationRegionServiceImpl implements LocationRegionService {
    @Autowired
    private LocationRegionRepository locationRegionRepository;

    @Override
    public Iterable<LocationRegion> findAll() {
        return null;
    }

    @Override
    public Optional<LocationRegion> findById(Long id) {
        return Optional.empty ();
    }

    @Override
    public LocationRegion save(LocationRegion locationRegion) {
        return locationRegionRepository.save ( locationRegion );
    }

    @Override
    public void remove(Long id) {

    }
}
