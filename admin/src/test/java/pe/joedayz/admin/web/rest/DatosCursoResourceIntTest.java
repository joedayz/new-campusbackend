package pe.joedayz.admin.web.rest;

import pe.joedayz.admin.AdminApp;

import pe.joedayz.admin.domain.DatosCurso;
import pe.joedayz.admin.repository.DatosCursoRepository;
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
 * Test class for the DatosCursoResource REST controller.
 *
 * @see DatosCursoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApp.class)
public class DatosCursoResourceIntTest {

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_INSTRUCTOR = "AAAAAAAAAA";
    private static final String UPDATED_INSTRUCTOR = "BBBBBBBBBB";

    private static final String DEFAULT_METODOLOGIA = "AAAAAAAAAA";
    private static final String UPDATED_METODOLOGIA = "BBBBBBBBBB";

    private static final String DEFAULT_PARTICIPANTES = "AAAAAAAAAA";
    private static final String UPDATED_PARTICIPANTES = "BBBBBBBBBB";

    private static final String DEFAULT_REQUISITOS = "AAAAAAAAAA";
    private static final String UPDATED_REQUISITOS = "BBBBBBBBBB";

    private static final String DEFAULT_SYLLABUS = "AAAAAAAAAA";
    private static final String UPDATED_SYLLABUS = "BBBBBBBBBB";

    private static final String DEFAULT_COSTO = "AAAAAAAAAA";
    private static final String UPDATED_COSTO = "BBBBBBBBBB";

    private static final String DEFAULT_DURACION = "AAAAAAAAAA";
    private static final String UPDATED_DURACION = "BBBBBBBBBB";

    private static final String DEFAULT_FECHA_INSCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_FECHA_INSCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_HORARIOS = "AAAAAAAAAA";
    private static final String UPDATED_HORARIOS = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_PARTICIPANTES = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_PARTICIPANTES = "BBBBBBBBBB";

    private static final String DEFAULT_MEMBRESIA_ANUAL = "AAAAAAAAAA";
    private static final String UPDATED_MEMBRESIA_ANUAL = "BBBBBBBBBB";

    @Autowired
    private DatosCursoRepository datosCursoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDatosCursoMockMvc;

    private DatosCurso datosCurso;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DatosCursoResource datosCursoResource = new DatosCursoResource(datosCursoRepository);
        this.restDatosCursoMockMvc = MockMvcBuilders.standaloneSetup(datosCursoResource)
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
    public static DatosCurso createEntity(EntityManager em) {
        DatosCurso datosCurso = new DatosCurso()
            .descripcion(DEFAULT_DESCRIPCION)
            .instructor(DEFAULT_INSTRUCTOR)
            .metodologia(DEFAULT_METODOLOGIA)
            .participantes(DEFAULT_PARTICIPANTES)
            .requisitos(DEFAULT_REQUISITOS)
            .syllabus(DEFAULT_SYLLABUS)
            .costo(DEFAULT_COSTO)
            .duracion(DEFAULT_DURACION)
            .fechaInscripcion(DEFAULT_FECHA_INSCRIPCION)
            .horarios(DEFAULT_HORARIOS)
            .numeroParticipantes(DEFAULT_NUMERO_PARTICIPANTES)
            .membresiaAnual(DEFAULT_MEMBRESIA_ANUAL);
        return datosCurso;
    }

    @Before
    public void initTest() {
        datosCurso = createEntity(em);
    }

    @Test
    @Transactional
    public void createDatosCurso() throws Exception {
        int databaseSizeBeforeCreate = datosCursoRepository.findAll().size();

        // Create the DatosCurso
        restDatosCursoMockMvc.perform(post("/api/datos-cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(datosCurso)))
            .andExpect(status().isCreated());

        // Validate the DatosCurso in the database
        List<DatosCurso> datosCursoList = datosCursoRepository.findAll();
        assertThat(datosCursoList).hasSize(databaseSizeBeforeCreate + 1);
        DatosCurso testDatosCurso = datosCursoList.get(datosCursoList.size() - 1);
        assertThat(testDatosCurso.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testDatosCurso.getInstructor()).isEqualTo(DEFAULT_INSTRUCTOR);
        assertThat(testDatosCurso.getMetodologia()).isEqualTo(DEFAULT_METODOLOGIA);
        assertThat(testDatosCurso.getParticipantes()).isEqualTo(DEFAULT_PARTICIPANTES);
        assertThat(testDatosCurso.getRequisitos()).isEqualTo(DEFAULT_REQUISITOS);
        assertThat(testDatosCurso.getSyllabus()).isEqualTo(DEFAULT_SYLLABUS);
        assertThat(testDatosCurso.getCosto()).isEqualTo(DEFAULT_COSTO);
        assertThat(testDatosCurso.getDuracion()).isEqualTo(DEFAULT_DURACION);
        assertThat(testDatosCurso.getFechaInscripcion()).isEqualTo(DEFAULT_FECHA_INSCRIPCION);
        assertThat(testDatosCurso.getHorarios()).isEqualTo(DEFAULT_HORARIOS);
        assertThat(testDatosCurso.getNumeroParticipantes()).isEqualTo(DEFAULT_NUMERO_PARTICIPANTES);
        assertThat(testDatosCurso.getMembresiaAnual()).isEqualTo(DEFAULT_MEMBRESIA_ANUAL);
    }

    @Test
    @Transactional
    public void createDatosCursoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = datosCursoRepository.findAll().size();

        // Create the DatosCurso with an existing ID
        datosCurso.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDatosCursoMockMvc.perform(post("/api/datos-cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(datosCurso)))
            .andExpect(status().isBadRequest());

        // Validate the DatosCurso in the database
        List<DatosCurso> datosCursoList = datosCursoRepository.findAll();
        assertThat(datosCursoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDatosCursos() throws Exception {
        // Initialize the database
        datosCursoRepository.saveAndFlush(datosCurso);

        // Get all the datosCursoList
        restDatosCursoMockMvc.perform(get("/api/datos-cursos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(datosCurso.getId().intValue())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].instructor").value(hasItem(DEFAULT_INSTRUCTOR.toString())))
            .andExpect(jsonPath("$.[*].metodologia").value(hasItem(DEFAULT_METODOLOGIA.toString())))
            .andExpect(jsonPath("$.[*].participantes").value(hasItem(DEFAULT_PARTICIPANTES.toString())))
            .andExpect(jsonPath("$.[*].requisitos").value(hasItem(DEFAULT_REQUISITOS.toString())))
            .andExpect(jsonPath("$.[*].syllabus").value(hasItem(DEFAULT_SYLLABUS.toString())))
            .andExpect(jsonPath("$.[*].costo").value(hasItem(DEFAULT_COSTO.toString())))
            .andExpect(jsonPath("$.[*].duracion").value(hasItem(DEFAULT_DURACION.toString())))
            .andExpect(jsonPath("$.[*].fechaInscripcion").value(hasItem(DEFAULT_FECHA_INSCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].horarios").value(hasItem(DEFAULT_HORARIOS.toString())))
            .andExpect(jsonPath("$.[*].numeroParticipantes").value(hasItem(DEFAULT_NUMERO_PARTICIPANTES.toString())))
            .andExpect(jsonPath("$.[*].membresiaAnual").value(hasItem(DEFAULT_MEMBRESIA_ANUAL.toString())));
    }
    
    @Test
    @Transactional
    public void getDatosCurso() throws Exception {
        // Initialize the database
        datosCursoRepository.saveAndFlush(datosCurso);

        // Get the datosCurso
        restDatosCursoMockMvc.perform(get("/api/datos-cursos/{id}", datosCurso.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(datosCurso.getId().intValue()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.instructor").value(DEFAULT_INSTRUCTOR.toString()))
            .andExpect(jsonPath("$.metodologia").value(DEFAULT_METODOLOGIA.toString()))
            .andExpect(jsonPath("$.participantes").value(DEFAULT_PARTICIPANTES.toString()))
            .andExpect(jsonPath("$.requisitos").value(DEFAULT_REQUISITOS.toString()))
            .andExpect(jsonPath("$.syllabus").value(DEFAULT_SYLLABUS.toString()))
            .andExpect(jsonPath("$.costo").value(DEFAULT_COSTO.toString()))
            .andExpect(jsonPath("$.duracion").value(DEFAULT_DURACION.toString()))
            .andExpect(jsonPath("$.fechaInscripcion").value(DEFAULT_FECHA_INSCRIPCION.toString()))
            .andExpect(jsonPath("$.horarios").value(DEFAULT_HORARIOS.toString()))
            .andExpect(jsonPath("$.numeroParticipantes").value(DEFAULT_NUMERO_PARTICIPANTES.toString()))
            .andExpect(jsonPath("$.membresiaAnual").value(DEFAULT_MEMBRESIA_ANUAL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDatosCurso() throws Exception {
        // Get the datosCurso
        restDatosCursoMockMvc.perform(get("/api/datos-cursos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDatosCurso() throws Exception {
        // Initialize the database
        datosCursoRepository.saveAndFlush(datosCurso);

        int databaseSizeBeforeUpdate = datosCursoRepository.findAll().size();

        // Update the datosCurso
        DatosCurso updatedDatosCurso = datosCursoRepository.findById(datosCurso.getId()).get();
        // Disconnect from session so that the updates on updatedDatosCurso are not directly saved in db
        em.detach(updatedDatosCurso);
        updatedDatosCurso
            .descripcion(UPDATED_DESCRIPCION)
            .instructor(UPDATED_INSTRUCTOR)
            .metodologia(UPDATED_METODOLOGIA)
            .participantes(UPDATED_PARTICIPANTES)
            .requisitos(UPDATED_REQUISITOS)
            .syllabus(UPDATED_SYLLABUS)
            .costo(UPDATED_COSTO)
            .duracion(UPDATED_DURACION)
            .fechaInscripcion(UPDATED_FECHA_INSCRIPCION)
            .horarios(UPDATED_HORARIOS)
            .numeroParticipantes(UPDATED_NUMERO_PARTICIPANTES)
            .membresiaAnual(UPDATED_MEMBRESIA_ANUAL);

        restDatosCursoMockMvc.perform(put("/api/datos-cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDatosCurso)))
            .andExpect(status().isOk());

        // Validate the DatosCurso in the database
        List<DatosCurso> datosCursoList = datosCursoRepository.findAll();
        assertThat(datosCursoList).hasSize(databaseSizeBeforeUpdate);
        DatosCurso testDatosCurso = datosCursoList.get(datosCursoList.size() - 1);
        assertThat(testDatosCurso.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testDatosCurso.getInstructor()).isEqualTo(UPDATED_INSTRUCTOR);
        assertThat(testDatosCurso.getMetodologia()).isEqualTo(UPDATED_METODOLOGIA);
        assertThat(testDatosCurso.getParticipantes()).isEqualTo(UPDATED_PARTICIPANTES);
        assertThat(testDatosCurso.getRequisitos()).isEqualTo(UPDATED_REQUISITOS);
        assertThat(testDatosCurso.getSyllabus()).isEqualTo(UPDATED_SYLLABUS);
        assertThat(testDatosCurso.getCosto()).isEqualTo(UPDATED_COSTO);
        assertThat(testDatosCurso.getDuracion()).isEqualTo(UPDATED_DURACION);
        assertThat(testDatosCurso.getFechaInscripcion()).isEqualTo(UPDATED_FECHA_INSCRIPCION);
        assertThat(testDatosCurso.getHorarios()).isEqualTo(UPDATED_HORARIOS);
        assertThat(testDatosCurso.getNumeroParticipantes()).isEqualTo(UPDATED_NUMERO_PARTICIPANTES);
        assertThat(testDatosCurso.getMembresiaAnual()).isEqualTo(UPDATED_MEMBRESIA_ANUAL);
    }

    @Test
    @Transactional
    public void updateNonExistingDatosCurso() throws Exception {
        int databaseSizeBeforeUpdate = datosCursoRepository.findAll().size();

        // Create the DatosCurso

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDatosCursoMockMvc.perform(put("/api/datos-cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(datosCurso)))
            .andExpect(status().isBadRequest());

        // Validate the DatosCurso in the database
        List<DatosCurso> datosCursoList = datosCursoRepository.findAll();
        assertThat(datosCursoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDatosCurso() throws Exception {
        // Initialize the database
        datosCursoRepository.saveAndFlush(datosCurso);

        int databaseSizeBeforeDelete = datosCursoRepository.findAll().size();

        // Get the datosCurso
        restDatosCursoMockMvc.perform(delete("/api/datos-cursos/{id}", datosCurso.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DatosCurso> datosCursoList = datosCursoRepository.findAll();
        assertThat(datosCursoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DatosCurso.class);
        DatosCurso datosCurso1 = new DatosCurso();
        datosCurso1.setId(1L);
        DatosCurso datosCurso2 = new DatosCurso();
        datosCurso2.setId(datosCurso1.getId());
        assertThat(datosCurso1).isEqualTo(datosCurso2);
        datosCurso2.setId(2L);
        assertThat(datosCurso1).isNotEqualTo(datosCurso2);
        datosCurso1.setId(null);
        assertThat(datosCurso1).isNotEqualTo(datosCurso2);
    }
}
