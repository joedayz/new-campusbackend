package pe.joedayz.admin.web.rest;

import pe.joedayz.admin.AdminApp;

import pe.joedayz.admin.domain.TipoCurso;
import pe.joedayz.admin.repository.TipoCursoRepository;
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
 * Test class for the TipoCursoResource REST controller.
 *
 * @see TipoCursoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApp.class)
public class TipoCursoResourceIntTest {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "A";
    private static final String UPDATED_CODE = "B";

    private static final Boolean DEFAULT_STATUS = false;
    private static final Boolean UPDATED_STATUS = true;

    @Autowired
    private TipoCursoRepository tipoCursoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoCursoMockMvc;

    private TipoCurso tipoCurso;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoCursoResource tipoCursoResource = new TipoCursoResource(tipoCursoRepository);
        this.restTipoCursoMockMvc = MockMvcBuilders.standaloneSetup(tipoCursoResource)
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
    public static TipoCurso createEntity(EntityManager em) {
        TipoCurso tipoCurso = new TipoCurso()
            .nombre(DEFAULT_NOMBRE)
            .code(DEFAULT_CODE)
            .status(DEFAULT_STATUS);
        return tipoCurso;
    }

    @Before
    public void initTest() {
        tipoCurso = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoCurso() throws Exception {
        int databaseSizeBeforeCreate = tipoCursoRepository.findAll().size();

        // Create the TipoCurso
        restTipoCursoMockMvc.perform(post("/api/tipo-cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoCurso)))
            .andExpect(status().isCreated());

        // Validate the TipoCurso in the database
        List<TipoCurso> tipoCursoList = tipoCursoRepository.findAll();
        assertThat(tipoCursoList).hasSize(databaseSizeBeforeCreate + 1);
        TipoCurso testTipoCurso = tipoCursoList.get(tipoCursoList.size() - 1);
        assertThat(testTipoCurso.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testTipoCurso.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testTipoCurso.isStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createTipoCursoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoCursoRepository.findAll().size();

        // Create the TipoCurso with an existing ID
        tipoCurso.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoCursoMockMvc.perform(post("/api/tipo-cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoCurso)))
            .andExpect(status().isBadRequest());

        // Validate the TipoCurso in the database
        List<TipoCurso> tipoCursoList = tipoCursoRepository.findAll();
        assertThat(tipoCursoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTipoCursos() throws Exception {
        // Initialize the database
        tipoCursoRepository.saveAndFlush(tipoCurso);

        // Get all the tipoCursoList
        restTipoCursoMockMvc.perform(get("/api/tipo-cursos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoCurso.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getTipoCurso() throws Exception {
        // Initialize the database
        tipoCursoRepository.saveAndFlush(tipoCurso);

        // Get the tipoCurso
        restTipoCursoMockMvc.perform(get("/api/tipo-cursos/{id}", tipoCurso.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoCurso.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoCurso() throws Exception {
        // Get the tipoCurso
        restTipoCursoMockMvc.perform(get("/api/tipo-cursos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoCurso() throws Exception {
        // Initialize the database
        tipoCursoRepository.saveAndFlush(tipoCurso);

        int databaseSizeBeforeUpdate = tipoCursoRepository.findAll().size();

        // Update the tipoCurso
        TipoCurso updatedTipoCurso = tipoCursoRepository.findById(tipoCurso.getId()).get();
        // Disconnect from session so that the updates on updatedTipoCurso are not directly saved in db
        em.detach(updatedTipoCurso);
        updatedTipoCurso
            .nombre(UPDATED_NOMBRE)
            .code(UPDATED_CODE)
            .status(UPDATED_STATUS);

        restTipoCursoMockMvc.perform(put("/api/tipo-cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoCurso)))
            .andExpect(status().isOk());

        // Validate the TipoCurso in the database
        List<TipoCurso> tipoCursoList = tipoCursoRepository.findAll();
        assertThat(tipoCursoList).hasSize(databaseSizeBeforeUpdate);
        TipoCurso testTipoCurso = tipoCursoList.get(tipoCursoList.size() - 1);
        assertThat(testTipoCurso.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testTipoCurso.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testTipoCurso.isStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoCurso() throws Exception {
        int databaseSizeBeforeUpdate = tipoCursoRepository.findAll().size();

        // Create the TipoCurso

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoCursoMockMvc.perform(put("/api/tipo-cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoCurso)))
            .andExpect(status().isBadRequest());

        // Validate the TipoCurso in the database
        List<TipoCurso> tipoCursoList = tipoCursoRepository.findAll();
        assertThat(tipoCursoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoCurso() throws Exception {
        // Initialize the database
        tipoCursoRepository.saveAndFlush(tipoCurso);

        int databaseSizeBeforeDelete = tipoCursoRepository.findAll().size();

        // Get the tipoCurso
        restTipoCursoMockMvc.perform(delete("/api/tipo-cursos/{id}", tipoCurso.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TipoCurso> tipoCursoList = tipoCursoRepository.findAll();
        assertThat(tipoCursoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoCurso.class);
        TipoCurso tipoCurso1 = new TipoCurso();
        tipoCurso1.setId(1L);
        TipoCurso tipoCurso2 = new TipoCurso();
        tipoCurso2.setId(tipoCurso1.getId());
        assertThat(tipoCurso1).isEqualTo(tipoCurso2);
        tipoCurso2.setId(2L);
        assertThat(tipoCurso1).isNotEqualTo(tipoCurso2);
        tipoCurso1.setId(null);
        assertThat(tipoCurso1).isNotEqualTo(tipoCurso2);
    }
}
