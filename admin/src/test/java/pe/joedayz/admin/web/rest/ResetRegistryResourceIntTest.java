package pe.joedayz.admin.web.rest;

import pe.joedayz.admin.AdminApp;

import pe.joedayz.admin.domain.ResetRegistry;
import pe.joedayz.admin.repository.ResetRegistryRepository;
import pe.joedayz.admin.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static pe.joedayz.admin.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ResetRegistryResource REST controller.
 *
 * @see ResetRegistryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApp.class)
public class ResetRegistryResourceIntTest {

    private static final String DEFAULT_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_TOKEN = "BBBBBBBBBB";

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_STATUS = false;
    private static final Boolean UPDATED_STATUS = true;

    @Autowired
    private ResetRegistryRepository resetRegistryRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restResetRegistryMockMvc;

    private ResetRegistry resetRegistry;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ResetRegistryResource resetRegistryResource = new ResetRegistryResource(resetRegistryRepository);
        this.restResetRegistryMockMvc = MockMvcBuilders.standaloneSetup(resetRegistryResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResetRegistry createEntity(EntityManager em) {
        ResetRegistry resetRegistry = new ResetRegistry()
            .token(DEFAULT_TOKEN)
            .userName(DEFAULT_USER_NAME)
            .status(DEFAULT_STATUS);
        return resetRegistry;
    }

    @Before
    public void initTest() {
        resetRegistry = createEntity(em);
    }

    @Test
    @Transactional
    public void createResetRegistry() throws Exception {
        int databaseSizeBeforeCreate = resetRegistryRepository.findAll().size();

        // Create the ResetRegistry
        restResetRegistryMockMvc.perform(post("/api/reset-registries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resetRegistry)))
            .andExpect(status().isCreated());

        // Validate the ResetRegistry in the database
        List<ResetRegistry> resetRegistryList = resetRegistryRepository.findAll();
        assertThat(resetRegistryList).hasSize(databaseSizeBeforeCreate + 1);
        ResetRegistry testResetRegistry = resetRegistryList.get(resetRegistryList.size() - 1);
        assertThat(testResetRegistry.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testResetRegistry.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testResetRegistry.isStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createResetRegistryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = resetRegistryRepository.findAll().size();

        // Create the ResetRegistry with an existing ID
        resetRegistry.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restResetRegistryMockMvc.perform(post("/api/reset-registries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resetRegistry)))
            .andExpect(status().isBadRequest());

        // Validate the ResetRegistry in the database
        List<ResetRegistry> resetRegistryList = resetRegistryRepository.findAll();
        assertThat(resetRegistryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllResetRegistries() throws Exception {
        // Initialize the database
        resetRegistryRepository.saveAndFlush(resetRegistry);

        // Get all the resetRegistryList
        restResetRegistryMockMvc.perform(get("/api/reset-registries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resetRegistry.getId().intValue())))
            .andExpect(jsonPath("$.[*].token").value(hasItem(DEFAULT_TOKEN.toString())))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getResetRegistry() throws Exception {
        // Initialize the database
        resetRegistryRepository.saveAndFlush(resetRegistry);

        // Get the resetRegistry
        restResetRegistryMockMvc.perform(get("/api/reset-registries/{id}", resetRegistry.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(resetRegistry.getId().intValue()))
            .andExpect(jsonPath("$.token").value(DEFAULT_TOKEN.toString()))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingResetRegistry() throws Exception {
        // Get the resetRegistry
        restResetRegistryMockMvc.perform(get("/api/reset-registries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateResetRegistry() throws Exception {
        // Initialize the database
        resetRegistryRepository.saveAndFlush(resetRegistry);

        int databaseSizeBeforeUpdate = resetRegistryRepository.findAll().size();

        // Update the resetRegistry
        ResetRegistry updatedResetRegistry = resetRegistryRepository.findById(resetRegistry.getId()).get();
        // Disconnect from session so that the updates on updatedResetRegistry are not directly saved in db
        em.detach(updatedResetRegistry);
        updatedResetRegistry
            .token(UPDATED_TOKEN)
            .userName(UPDATED_USER_NAME)
            .status(UPDATED_STATUS);

        restResetRegistryMockMvc.perform(put("/api/reset-registries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedResetRegistry)))
            .andExpect(status().isOk());

        // Validate the ResetRegistry in the database
        List<ResetRegistry> resetRegistryList = resetRegistryRepository.findAll();
        assertThat(resetRegistryList).hasSize(databaseSizeBeforeUpdate);
        ResetRegistry testResetRegistry = resetRegistryList.get(resetRegistryList.size() - 1);
        assertThat(testResetRegistry.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testResetRegistry.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testResetRegistry.isStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingResetRegistry() throws Exception {
        int databaseSizeBeforeUpdate = resetRegistryRepository.findAll().size();

        // Create the ResetRegistry

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResetRegistryMockMvc.perform(put("/api/reset-registries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resetRegistry)))
            .andExpect(status().isBadRequest());

        // Validate the ResetRegistry in the database
        List<ResetRegistry> resetRegistryList = resetRegistryRepository.findAll();
        assertThat(resetRegistryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteResetRegistry() throws Exception {
        // Initialize the database
        resetRegistryRepository.saveAndFlush(resetRegistry);

        int databaseSizeBeforeDelete = resetRegistryRepository.findAll().size();

        // Get the resetRegistry
        restResetRegistryMockMvc.perform(delete("/api/reset-registries/{id}", resetRegistry.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ResetRegistry> resetRegistryList = resetRegistryRepository.findAll();
        assertThat(resetRegistryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResetRegistry.class);
        ResetRegistry resetRegistry1 = new ResetRegistry();
        resetRegistry1.setId(1L);
        ResetRegistry resetRegistry2 = new ResetRegistry();
        resetRegistry2.setId(resetRegistry1.getId());
        assertThat(resetRegistry1).isEqualTo(resetRegistry2);
        resetRegistry2.setId(2L);
        assertThat(resetRegistry1).isNotEqualTo(resetRegistry2);
        resetRegistry1.setId(null);
        assertThat(resetRegistry1).isNotEqualTo(resetRegistry2);
    }
}
