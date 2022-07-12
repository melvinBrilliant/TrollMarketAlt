package com.melvin.TrollMarketAlt.dao;

import com.melvin.TrollMarketAlt.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {
}