package pe.joedayz.admin.web.rest;

import pe.joedayz.admin.AdminApp;

import pe.joedayz.admin.domain.Hangout;
import pe.joedayz.admin.repository.HangoutRepository;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;


import static pe.joedayz.admin.web.rest.TestUtil.sameInstant;
import static pe.joedayz.admin.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the HangoutResource REST controller.
 *
 * @see HangoutResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApp.class)
public class HangoutResourceIntTest {

    private static final String DEFAULT_TITULO = "AAAAAAAAAA";
    private static final String UPDATED_TITULO = "BBBBBBBBBB";

    private static final String DEFAULT_PONENTE = "AAAAAAAAAA";
    private static final String UPDATED_PONENTE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_FECHA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FECHA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_STATUS = false;
    private static final Boolean UPDATED_STATUS = true;

    @Autowired
    private HangoutRepository hangoutRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restHangoutMockMvc;

    private Hangout hangout;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HangoutResource hangoutResource = new HangoutResource(hangoutRepository);
        this.restHangoutMockMvc = MockMvcBuilders.standaloneSetup(hangoutResource)
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
    public static Hangout createEntity(EntityManager em) {
        Hangout hangout = new Hangout()
            .titulo(DEFAULT_TITULO)
            .ponente(DEFAULT_PONENTE)
            .descripcion(DEFAULT_DESCRIPCION)
            .url(DEFAULT_URL)
            .fecha(DEFAULT_FECHA)
            .status(DEFAULT_STATUS);
        return hangout;
    }

    @Before
    public void initTest() {
        hangout = createEntity(em);
    }

    @Test
    @Transactional
    public void createHangout() throws Exception {
        int databaseSizeBeforeCreate = hangoutRepository.findAll().size();

        // Create the Hangout
        restHangoutMockMvc.perform(post("/api/hangouts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hangout)))
            .andExpect(status().isCreated());

        // Validate the Hangout in the database
        List<Hangout> hangoutList = hangoutRepository.findAll();
        assertThat(hangoutList).hasSize(databaseSizeBeforeCreate + 1);
        Hangout testHangout = hangoutList.get(hangoutList.size() - 1);
        assertThat(testHangout.getTitulo()).isEqualTo(DEFAULT_TITULO);
        assertThat(testHangout.getPonente()).isEqualTo(DEFAULT_PONENTE);
        assertThat(testHangout.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testHangout.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testHangout.getFecha()).isEqualTo(DEFAULT_FECHA);
        assertThat(testHangout.isStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createHangoutWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = hangoutRepository.findAll().size();

        // Create the Hangout with an existing ID
        hangout.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHangoutMockMvc.perform(post("/api/hangouts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hangout)))
            .andExpect(status().isBadRequest());

        // Validate the Hangout in the database
        List<Hangout> hangoutList = hangoutRepository.findAll();
        assertThat(hangoutList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTituloIsRequired() throws Exception {
        int databaseSizeBeforeTest = hangoutRepository.findAll().size();
        // set the field null
        hangout.setTitulo(null);

        // Create the Hangout, which fails.

        restHangoutMockMvc.perform(post("/api/hangouts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hangout)))
            .andExpect(status().isBadRequest());

        List<Hangout> hangoutList = hangoutRepository.findAll();
        assertThat(hangoutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPonenteIsRequired() throws Exception {
        int databaseSizeBeforeTest = hangoutRepository.findAll().size();
        // set the field null
        hangout.setPonente(null);

        // Create the Hangout, which fails.

        restHangoutMockMvc.perform(post("/api/hangouts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hangout)))
            .andExpect(status().isBadRequest());

        List<Hangout> hangoutList = hangoutRepository.findAll();
        assertThat(hangoutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = hangoutRepository.findAll().size();
        // set the field null
        hangout.setUrl(null);

        // Create the Hangout, which fails.

        restHangoutMockMvc.perform(post("/api/hangouts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hangout)))
            .andExpect(status().isBadRequest());

        List<Hangout> hangoutList = hangoutRepository.findAll();
        assertThat(hangoutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaIsRequired() throws Exception {
        int databaseSizeBeforeTest = hangoutRepository.findAll().size();
        // set the field null
        hangout.setFecha(null);

        // Create the Hangout, which fails.

        restHangoutMockMvc.perform(post("/api/hangouts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hangout)))
            .andExpect(status().isBadRequest());

        List<Hangout> hangoutList = hangoutRepository.findAll();
        assertThat(hangoutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllHangouts() throws Exception {
        // Initialize the database
        hangoutRepository.saveAndFlush(hangout);

        // Get all the hangoutList
        restHangoutMockMvc.perform(get("/api/hangouts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hangout.getId().intValue())))
            .andExpect(jsonPath("$.[*].titulo").value(hasItem(DEFAULT_TITULO.toString())))
            .andExpect(jsonPath("$.[*].ponente").value(hasItem(DEFAULT_PONENTE.toString())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL.toString())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(sameInstant(DEFAULT_FECHA))))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getHangout() throws Exception {
        // Initialize the database
        hangoutRepository.saveAndFlush(hangout);

        // Get the hangout
        restHangoutMockMvc.perform(get("/api/hangouts/{id}", hangout.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(hangout.getId().intValue()))
            .andExpect(jsonPath("$.titulo").value(DEFAULT_TITULO.toString()))
            .andExpect(jsonPath("$.ponente").value(DEFAULT_PONENTE.toString()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL.toString()))
            .andExpect(jsonPath("$.fecha").value(sameInstant(DEFAULT_FECHA)))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingHangout() throws Exception {
        // Get the hangout
        restHangoutMockMvc.perform(get("/api/hangouts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHangout() throws Exception {
        // Initialize the database
        hangoutRepository.saveAndFlush(hangout);

        int databaseSizeBeforeUpdate = hangoutRepository.findAll().size();

        // Update the hangout
        Hangout updatedHangout = hangoutRepository.findById(hangout.getId()).get();
        // Disconnect from session so that the updates on updatedHangout are not directly saved in db
        em.detach(updatedHangout);
        updatedHangout
            .titulo(UPDATED_TITULO)
            .ponente(UPDATED_PONENTE)
            .descripcion(UPDATED_DESCRIPCION)
            .url(UPDATED_URL)
            .fecha(UPDATED_FECHA)
            .status(UPDATED_STATUS);

        restHangoutMockMvc.perform(put("/api/hangouts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedHangout)))
            .andExpect(status().isOk());

        // Validate the Hangout in the database
        List<Hangout> hangoutList = hangoutRepository.findAll();
        assertThat(hangoutList).hasSize(databaseSizeBeforeUpdate);
        Hangout testHangout = hangoutList.get(hangoutList.size() - 1);
        assertThat(testHangout.getTitulo()).isEqualTo(UPDATED_TITULO);
        assertThat(testHangout.getPonente()).isEqualTo(UPDATED_PONENTE);
        assertThat(testHangout.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testHangout.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testHangout.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testHangout.isStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingHangout() throws Exception {
        int databaseSizeBeforeUpdate = hangoutRepository.findAll().size();

        // Create the Hangout

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHangoutMockMvc.perform(put("/api/hangouts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hangout)))
            .andExpect(status().isBadRequest());

        // Validate the Hangout in the database
        List<Hangout> hangoutList = hangoutRepository.findAll();
        assertThat(hangoutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHangout() throws Exception {
        // Initialize the database
        hangoutRepository.saveAndFlush(hangout);

        int databaseSizeBeforeDelete = hangoutRepository.findAll().size();

        // Get the hangout
        restHangoutMockMvc.perform(delete("/api/hangouts/{id}", hangout.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Hangout> hangoutList = hangoutRepository.findAll();
        assertThat(hangoutList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Hangout.class);
        Hangout hangout1 = new Hangout();
        hangout1.setId(1L);
        Hangout hangout2 = new Hangout();
        hangout2.setId(hangout1.getId());
        assertThat(hangout1).isEqualTo(hangout2);
        hangout2.setId(2L);
        assertThat(hangout1).isNotEqualTo(hangout2);
        hangout1.setId(null);
        assertThat(hangout1).isNotEqualTo(hangout2);
    }
}
