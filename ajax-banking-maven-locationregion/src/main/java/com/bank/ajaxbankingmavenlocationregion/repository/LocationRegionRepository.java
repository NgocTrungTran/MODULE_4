package com.bank.ajaxbankingmavenlocationregion.repository;

import com.bank.ajaxbankingmavenlocationregion.model.LocationRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRegionRepository extends JpaRepository<LocationRegion, Long> {

}
