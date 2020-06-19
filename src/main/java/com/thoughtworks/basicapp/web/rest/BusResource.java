package com.thoughtworks.basicapp.web.rest;

import com.thoughtworks.basicapp.domain.Bus;
import com.thoughtworks.basicapp.repository.BusRepository;
import com.thoughtworks.basicapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.thoughtworks.basicapp.domain.Bus}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BusResource {

    private final Logger log = LoggerFactory.getLogger(BusResource.class);

    private final BusRepository busRepository;

    public BusResource(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    /**
     * {@code GET  /buses} : get all the buses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of buses in body.
     */
    @GetMapping("/buses")
    public List<Bus> getAllBuses() {
        log.debug("REST request to get all Buses");
        return busRepository.findAll();
    }

    /**
     * {@code GET  /buses/:id} : get the "id" bus.
     *
     * @param id the id of the bus to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bus, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/buses/{id}")
    public ResponseEntity<Bus> getBus(@PathVariable Long id) {
        log.debug("REST request to get Bus : {}", id);
        Optional<Bus> bus = busRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(bus);
    }
}
