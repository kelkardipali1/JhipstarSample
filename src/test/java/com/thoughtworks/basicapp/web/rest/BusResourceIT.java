package com.thoughtworks.basicapp.web.rest;

import com.thoughtworks.basicapp.BasicappApp;
import com.thoughtworks.basicapp.domain.Bus;
import com.thoughtworks.basicapp.repository.BusRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link BusResource} REST controller.
 */
@SpringBootTest(classes = BasicappApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BusResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBusMockMvc;

    private Bus bus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bus createEntity(EntityManager em) {
        Bus bus = new Bus()
            .name(DEFAULT_NAME);
        return bus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bus createUpdatedEntity(EntityManager em) {
        Bus bus = new Bus()
            .name(UPDATED_NAME);
        return bus;
    }

    @BeforeEach
    public void initTest() {
        bus = createEntity(em);
    }

    @Test
    @Transactional
    public void getAllBuses() throws Exception {
        // Initialize the database
        busRepository.saveAndFlush(bus);

        // Get all the busList
        restBusMockMvc.perform(get("/api/buses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bus.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getBus() throws Exception {
        // Initialize the database
        busRepository.saveAndFlush(bus);

        // Get the bus
        restBusMockMvc.perform(get("/api/buses/{id}", bus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bus.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingBus() throws Exception {
        // Get the bus
        restBusMockMvc.perform(get("/api/buses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }
}
