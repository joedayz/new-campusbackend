package pe.joedayz.admin.web.rest;

import pe.joedayz.admin.AdminApp;

import pe.joedayz.admin.domain.TemasCurso;
import pe.joedayz.admin.repository.TemasCursoRepository;
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
import java.util.List;


import static pe.joedayz.admin.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TemasCursoResource REST controller.
 *
 * @see TemasCursoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApp.class)
public class TemasCursoResourceIntTest {

    private static final String DEFAULT_TITULO = "AAAAAAAAAA";
    private static final String UPDATED_TITULO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final Double DEFAULT_ORDEN = 1D;
    private static final Double UPDATED_ORDEN = 2D;

    private static final Boolean DEFAULT_STATUS = false;
    private static final Boolean UPDATED_STATUS = true;

    @Autowired
    private TemasCursoRepository temasCursoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTemasCursoMockMvc;

    private TemasCurso temasCurso;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TemasCursoResource temasCursoResource = new TemasCursoResource(temasCursoRepository);
        this.restTemasCursoMockMvc = MockMvcBuilders.standaloneSetup(temasCursoResource)
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
    public static TemasCurso createEntity(EntityManager em) {
        TemasCurso temasCurso = new TemasCurso()
            .titulo(DEFAULT_TITULO)
            .descripcion(DEFAULT_DESCRIPCION)
            .url(DEFAULT_URL)
            .orden(DEFAULT_ORDEN)
            .status(DEFAULT_STATUS);
        return temasCurso;
    }

    @Before
    public void initTest() {
        temasCurso = createEntity(em);
    }

    @Test
    @Transactional
    public void createTemasCurso() throws Exception {
        int databaseSizeBeforeCreate = temasCursoRepository.findAll().size();

        // Create the TemasCurso
        restTemasCursoMockMvc.perform(post("/api/temas-cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(temasCurso)))
            .andExpect(status().isCreated());

        // Validate the TemasCurso in the database
        List<TemasCurso> temasCursoList = temasCursoRepository.findAll();
        assertThat(temasCursoList).hasSize(databaseSizeBeforeCreate + 1);
        TemasCurso testTemasCurso = temasCursoList.get(temasCursoList.size() - 1);
        assertThat(testTemasCurso.getTitulo()).isEqualTo(DEFAULT_TITULO);
        assertThat(testTemasCurso.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testTemasCurso.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testTemasCurso.getOrden()).isEqualTo(DEFAULT_ORDEN);
        assertThat(testTemasCurso.isStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createTemasCursoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = temasCursoRepository.findAll().size();

        // Create the TemasCurso with an existing ID
        temasCurso.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTemasCursoMockMvc.perform(post("/api/temas-cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(temasCurso)))
            .andExpect(status().isBadRequest());

        // Validate the TemasCurso in the database
        List<TemasCurso> temasCursoList = temasCursoRepository.findAll();
        assertThat(temasCursoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTemasCursos() throws Exception {
        // Initialize the database
        temasCursoRepository.saveAndFlush(temasCurso);

        // Get all the temasCursoList
        restTemasCursoMockMvc.perform(get("/api/temas-cursos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(temasCurso.getId().intValue())))
            .andExpect(jsonPath("$.[*].titulo").value(hasItem(DEFAULT_TITULO.toString())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL.toString())))
            .andExpect(jsonPath("$.[*].orden").value(hasItem(DEFAULT_ORDEN.doubleValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getTemasCurso() throws Exception {
        // Initialize the database
        temasCursoRepository.saveAndFlush(temasCurso);

        // Get the temasCurso
        restTemasCursoMockMvc.perform(get("/api/temas-cursos/{id}", temasCurso.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(temasCurso.getId().intValue()))
            .andExpect(jsonPath("$.titulo").value(DEFAULT_TITULO.toString()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL.toString()))
            .andExpect(jsonPath("$.orden").value(DEFAULT_ORDEN.doubleValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTemasCurso() throws Exception {
        // Get the temasCurso
        restTemasCursoMockMvc.perform(get("/api/temas-cursos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTemasCurso() throws Exception {
        // Initialize the database
        temasCursoRepository.saveAndFlush(temasCurso);

        int databaseSizeBeforeUpdate = temasCursoRepository.findAll().size();

        // Update the temasCurso
        TemasCurso updatedTemasCurso = temasCursoRepository.findById(temasCurso.getId()).get();
        // Disconnect from session so that the updates on updatedTemasCurso are not directly saved in db
        em.detach(updatedTemasCurso);
        updatedTemasCurso
            .titulo(UPDATED_TITULO)
            .descripcion(UPDATED_DESCRIPCION)
            .url(UPDATED_URL)
            .orden(UPDATED_ORDEN)
            .status(UPDATED_STATUS);

        restTemasCursoMockMvc.perform(put("/api/temas-cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTemasCurso)))
            .andExpect(status().isOk());

        // Validate the TemasCurso in the database
        List<TemasCurso> temasCursoList = temasCursoRepository.findAll();
        assertThat(temasCursoList).hasSize(databaseSizeBeforeUpdate);
        TemasCurso testTemasCurso = temasCursoList.get(temasCursoList.size() - 1);
        assertThat(testTemasCurso.getTitulo()).isEqualTo(UPDATED_TITULO);
        assertThat(testTemasCurso.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testTemasCurso.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testTemasCurso.getOrden()).isEqualTo(UPDATED_ORDEN);
        assertThat(testTemasCurso.isStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingTemasCurso() throws Exception {
        int databaseSizeBeforeUpdate = temasCursoRepository.findAll().size();

        // Create the TemasCurso

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTemasCursoMockMvc.perform(put("/api/temas-cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(temasCurso)))
            .andExpect(status().isBadRequest());

        // Validate the TemasCurso in the database
        List<TemasCurso> temasCursoList = temasCursoRepository.findAll();
        assertThat(temasCursoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTemasCurso() throws Exception {
        // Initialize the database
        temasCursoRepository.saveAndFlush(temasCurso);

        int databaseSizeBeforeDelete = temasCursoRepository.findAll().size();

        // Get the temasCurso
        restTemasCursoMockMvc.perform(delete("/api/temas-cursos/{id}", temasCurso.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TemasCurso> temasCursoList = temasCursoRepository.findAll();
        assertThat(temasCursoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TemasCurso.class);
        TemasCurso temasCurso1 = new TemasCurso();
        temasCurso1.setId(1L);
        TemasCurso temasCurso2 = new TemasCurso();
        temasCurso2.setId(temasCurso1.getId());
        assertThat(temasCurso1).isEqualTo(temasCurso2);
        temasCurso2.setId(2L);
        assertThat(temasCurso1).isNotEqualTo(temasCurso2);
        temasCurso1.setId(null);
        assertThat(temasCurso1).isNotEqualTo(temasCurso2);
    }
}
