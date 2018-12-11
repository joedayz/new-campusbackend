package pe.joedayz.admin.web.rest;

import pe.joedayz.admin.AdminApp;

import pe.joedayz.admin.domain.GeneralTable;
import pe.joedayz.admin.repository.GeneralTableRepository;
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
 * Test class for the GeneralTableResource REST controller.
 *
 * @see GeneralTableResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApp.class)
public class GeneralTableResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TABLA = "AAAAAAAAAA";
    private static final String UPDATED_TABLA = "BBBBBBBBBB";

    private static final String DEFAULT_VALOR = "AAAAAAAAAA";
    private static final String UPDATED_VALOR = "BBBBBBBBBB";

    private static final Double DEFAULT_ORDEN = 1D;
    private static final Double UPDATED_ORDEN = 2D;

    private static final Boolean DEFAULT_STATUS = false;
    private static final Boolean UPDATED_STATUS = true;

    @Autowired
    private GeneralTableRepository generalTableRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGeneralTableMockMvc;

    private GeneralTable generalTable;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GeneralTableResource generalTableResource = new GeneralTableResource(generalTableRepository);
        this.restGeneralTableMockMvc = MockMvcBuilders.standaloneSetup(generalTableResource)
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
    public static GeneralTable createEntity(EntityManager em) {
        GeneralTable generalTable = new GeneralTable()
            .code(DEFAULT_CODE)
            .tabla(DEFAULT_TABLA)
            .valor(DEFAULT_VALOR)
            .orden(DEFAULT_ORDEN)
            .status(DEFAULT_STATUS);
        return generalTable;
    }

    @Before
    public void initTest() {
        generalTable = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeneralTable() throws Exception {
        int databaseSizeBeforeCreate = generalTableRepository.findAll().size();

        // Create the GeneralTable
        restGeneralTableMockMvc.perform(post("/api/general-tables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(generalTable)))
            .andExpect(status().isCreated());

        // Validate the GeneralTable in the database
        List<GeneralTable> generalTableList = generalTableRepository.findAll();
        assertThat(generalTableList).hasSize(databaseSizeBeforeCreate + 1);
        GeneralTable testGeneralTable = generalTableList.get(generalTableList.size() - 1);
        assertThat(testGeneralTable.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testGeneralTable.getTabla()).isEqualTo(DEFAULT_TABLA);
        assertThat(testGeneralTable.getValor()).isEqualTo(DEFAULT_VALOR);
        assertThat(testGeneralTable.getOrden()).isEqualTo(DEFAULT_ORDEN);
        assertThat(testGeneralTable.isStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createGeneralTableWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = generalTableRepository.findAll().size();

        // Create the GeneralTable with an existing ID
        generalTable.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeneralTableMockMvc.perform(post("/api/general-tables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(generalTable)))
            .andExpect(status().isBadRequest());

        // Validate the GeneralTable in the database
        List<GeneralTable> generalTableList = generalTableRepository.findAll();
        assertThat(generalTableList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = generalTableRepository.findAll().size();
        // set the field null
        generalTable.setCode(null);

        // Create the GeneralTable, which fails.

        restGeneralTableMockMvc.perform(post("/api/general-tables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(generalTable)))
            .andExpect(status().isBadRequest());

        List<GeneralTable> generalTableList = generalTableRepository.findAll();
        assertThat(generalTableList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTablaIsRequired() throws Exception {
        int databaseSizeBeforeTest = generalTableRepository.findAll().size();
        // set the field null
        generalTable.setTabla(null);

        // Create the GeneralTable, which fails.

        restGeneralTableMockMvc.perform(post("/api/general-tables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(generalTable)))
            .andExpect(status().isBadRequest());

        List<GeneralTable> generalTableList = generalTableRepository.findAll();
        assertThat(generalTableList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValorIsRequired() throws Exception {
        int databaseSizeBeforeTest = generalTableRepository.findAll().size();
        // set the field null
        generalTable.setValor(null);

        // Create the GeneralTable, which fails.

        restGeneralTableMockMvc.perform(post("/api/general-tables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(generalTable)))
            .andExpect(status().isBadRequest());

        List<GeneralTable> generalTableList = generalTableRepository.findAll();
        assertThat(generalTableList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGeneralTables() throws Exception {
        // Initialize the database
        generalTableRepository.saveAndFlush(generalTable);

        // Get all the generalTableList
        restGeneralTableMockMvc.perform(get("/api/general-tables?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(generalTable.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].tabla").value(hasItem(DEFAULT_TABLA.toString())))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR.toString())))
            .andExpect(jsonPath("$.[*].orden").value(hasItem(DEFAULT_ORDEN.doubleValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getGeneralTable() throws Exception {
        // Initialize the database
        generalTableRepository.saveAndFlush(generalTable);

        // Get the generalTable
        restGeneralTableMockMvc.perform(get("/api/general-tables/{id}", generalTable.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(generalTable.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.tabla").value(DEFAULT_TABLA.toString()))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR.toString()))
            .andExpect(jsonPath("$.orden").value(DEFAULT_ORDEN.doubleValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingGeneralTable() throws Exception {
        // Get the generalTable
        restGeneralTableMockMvc.perform(get("/api/general-tables/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeneralTable() throws Exception {
        // Initialize the database
        generalTableRepository.saveAndFlush(generalTable);

        int databaseSizeBeforeUpdate = generalTableRepository.findAll().size();

        // Update the generalTable
        GeneralTable updatedGeneralTable = generalTableRepository.findById(generalTable.getId()).get();
        // Disconnect from session so that the updates on updatedGeneralTable are not directly saved in db
        em.detach(updatedGeneralTable);
        updatedGeneralTable
            .code(UPDATED_CODE)
            .tabla(UPDATED_TABLA)
            .valor(UPDATED_VALOR)
            .orden(UPDATED_ORDEN)
            .status(UPDATED_STATUS);

        restGeneralTableMockMvc.perform(put("/api/general-tables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGeneralTable)))
            .andExpect(status().isOk());

        // Validate the GeneralTable in the database
        List<GeneralTable> generalTableList = generalTableRepository.findAll();
        assertThat(generalTableList).hasSize(databaseSizeBeforeUpdate);
        GeneralTable testGeneralTable = generalTableList.get(generalTableList.size() - 1);
        assertThat(testGeneralTable.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testGeneralTable.getTabla()).isEqualTo(UPDATED_TABLA);
        assertThat(testGeneralTable.getValor()).isEqualTo(UPDATED_VALOR);
        assertThat(testGeneralTable.getOrden()).isEqualTo(UPDATED_ORDEN);
        assertThat(testGeneralTable.isStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingGeneralTable() throws Exception {
        int databaseSizeBeforeUpdate = generalTableRepository.findAll().size();

        // Create the GeneralTable

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeneralTableMockMvc.perform(put("/api/general-tables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(generalTable)))
            .andExpect(status().isBadRequest());

        // Validate the GeneralTable in the database
        List<GeneralTable> generalTableList = generalTableRepository.findAll();
        assertThat(generalTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeneralTable() throws Exception {
        // Initialize the database
        generalTableRepository.saveAndFlush(generalTable);

        int databaseSizeBeforeDelete = generalTableRepository.findAll().size();

        // Get the generalTable
        restGeneralTableMockMvc.perform(delete("/api/general-tables/{id}", generalTable.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GeneralTable> generalTableList = generalTableRepository.findAll();
        assertThat(generalTableList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeneralTable.class);
        GeneralTable generalTable1 = new GeneralTable();
        generalTable1.setId(1L);
        GeneralTable generalTable2 = new GeneralTable();
        generalTable2.setId(generalTable1.getId());
        assertThat(generalTable1).isEqualTo(generalTable2);
        generalTable2.setId(2L);
        assertThat(generalTable1).isNotEqualTo(generalTable2);
        generalTable1.setId(null);
        assertThat(generalTable1).isNotEqualTo(generalTable2);
    }
}
