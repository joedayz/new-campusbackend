package pe.joedayz.admin.web.rest;

import pe.joedayz.admin.AdminApp;

import pe.joedayz.admin.domain.Portada;
import pe.joedayz.admin.repository.PortadaRepository;
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
 * Test class for the PortadaResource REST controller.
 *
 * @see PortadaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApp.class)
public class PortadaResourceIntTest {

    private static final String DEFAULT_TITULO_FOTO = "AAAAAAAAAA";
    private static final String UPDATED_TITULO_FOTO = "BBBBBBBBBB";

    private static final String DEFAULT_URL_FOTO = "AAAAAAAAAA";
    private static final String UPDATED_URL_FOTO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_STATUS = false;
    private static final Boolean UPDATED_STATUS = true;

    @Autowired
    private PortadaRepository portadaRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPortadaMockMvc;

    private Portada portada;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PortadaResource portadaResource = new PortadaResource(portadaRepository);
        this.restPortadaMockMvc = MockMvcBuilders.standaloneSetup(portadaResource)
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
    public static Portada createEntity(EntityManager em) {
        Portada portada = new Portada()
            .tituloFoto(DEFAULT_TITULO_FOTO)
            .urlFoto(DEFAULT_URL_FOTO)
            .status(DEFAULT_STATUS);
        return portada;
    }

    @Before
    public void initTest() {
        portada = createEntity(em);
    }

    @Test
    @Transactional
    public void createPortada() throws Exception {
        int databaseSizeBeforeCreate = portadaRepository.findAll().size();

        // Create the Portada
        restPortadaMockMvc.perform(post("/api/portadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(portada)))
            .andExpect(status().isCreated());

        // Validate the Portada in the database
        List<Portada> portadaList = portadaRepository.findAll();
        assertThat(portadaList).hasSize(databaseSizeBeforeCreate + 1);
        Portada testPortada = portadaList.get(portadaList.size() - 1);
        assertThat(testPortada.getTituloFoto()).isEqualTo(DEFAULT_TITULO_FOTO);
        assertThat(testPortada.getUrlFoto()).isEqualTo(DEFAULT_URL_FOTO);
        assertThat(testPortada.isStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createPortadaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = portadaRepository.findAll().size();

        // Create the Portada with an existing ID
        portada.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPortadaMockMvc.perform(post("/api/portadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(portada)))
            .andExpect(status().isBadRequest());

        // Validate the Portada in the database
        List<Portada> portadaList = portadaRepository.findAll();
        assertThat(portadaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUrlFotoIsRequired() throws Exception {
        int databaseSizeBeforeTest = portadaRepository.findAll().size();
        // set the field null
        portada.setUrlFoto(null);

        // Create the Portada, which fails.

        restPortadaMockMvc.perform(post("/api/portadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(portada)))
            .andExpect(status().isBadRequest());

        List<Portada> portadaList = portadaRepository.findAll();
        assertThat(portadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPortadas() throws Exception {
        // Initialize the database
        portadaRepository.saveAndFlush(portada);

        // Get all the portadaList
        restPortadaMockMvc.perform(get("/api/portadas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(portada.getId().intValue())))
            .andExpect(jsonPath("$.[*].tituloFoto").value(hasItem(DEFAULT_TITULO_FOTO.toString())))
            .andExpect(jsonPath("$.[*].urlFoto").value(hasItem(DEFAULT_URL_FOTO.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPortada() throws Exception {
        // Initialize the database
        portadaRepository.saveAndFlush(portada);

        // Get the portada
        restPortadaMockMvc.perform(get("/api/portadas/{id}", portada.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(portada.getId().intValue()))
            .andExpect(jsonPath("$.tituloFoto").value(DEFAULT_TITULO_FOTO.toString()))
            .andExpect(jsonPath("$.urlFoto").value(DEFAULT_URL_FOTO.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPortada() throws Exception {
        // Get the portada
        restPortadaMockMvc.perform(get("/api/portadas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePortada() throws Exception {
        // Initialize the database
        portadaRepository.saveAndFlush(portada);

        int databaseSizeBeforeUpdate = portadaRepository.findAll().size();

        // Update the portada
        Portada updatedPortada = portadaRepository.findById(portada.getId()).get();
        // Disconnect from session so that the updates on updatedPortada are not directly saved in db
        em.detach(updatedPortada);
        updatedPortada
            .tituloFoto(UPDATED_TITULO_FOTO)
            .urlFoto(UPDATED_URL_FOTO)
            .status(UPDATED_STATUS);

        restPortadaMockMvc.perform(put("/api/portadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPortada)))
            .andExpect(status().isOk());

        // Validate the Portada in the database
        List<Portada> portadaList = portadaRepository.findAll();
        assertThat(portadaList).hasSize(databaseSizeBeforeUpdate);
        Portada testPortada = portadaList.get(portadaList.size() - 1);
        assertThat(testPortada.getTituloFoto()).isEqualTo(UPDATED_TITULO_FOTO);
        assertThat(testPortada.getUrlFoto()).isEqualTo(UPDATED_URL_FOTO);
        assertThat(testPortada.isStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingPortada() throws Exception {
        int databaseSizeBeforeUpdate = portadaRepository.findAll().size();

        // Create the Portada

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPortadaMockMvc.perform(put("/api/portadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(portada)))
            .andExpect(status().isBadRequest());

        // Validate the Portada in the database
        List<Portada> portadaList = portadaRepository.findAll();
        assertThat(portadaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePortada() throws Exception {
        // Initialize the database
        portadaRepository.saveAndFlush(portada);

        int databaseSizeBeforeDelete = portadaRepository.findAll().size();

        // Get the portada
        restPortadaMockMvc.perform(delete("/api/portadas/{id}", portada.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Portada> portadaList = portadaRepository.findAll();
        assertThat(portadaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Portada.class);
        Portada portada1 = new Portada();
        portada1.setId(1L);
        Portada portada2 = new Portada();
        portada2.setId(portada1.getId());
        assertThat(portada1).isEqualTo(portada2);
        portada2.setId(2L);
        assertThat(portada1).isNotEqualTo(portada2);
        portada1.setId(null);
        assertThat(portada1).isNotEqualTo(portada2);
    }
}
