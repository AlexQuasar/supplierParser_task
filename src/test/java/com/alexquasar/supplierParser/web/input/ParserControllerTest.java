package com.alexquasar.supplierParser.web.input;

import com.alexquasar.supplierParser.dto.ConverterJSON;
import com.alexquasar.supplierParser.dto.yamlStructure.YamlMain;
import com.alexquasar.supplierParser.service.ParserServiceTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParserControllerTest {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper mapper;

    Logger log = Logger.getLogger(ParserControllerTest.class.getName());
    String supplierParser = "/supplierParser";

    ConverterJSON converterJSON = new ConverterJSON();
    final String directoryPath = ".";///src/test/java/com.alexquasar.supplierParser/testFiles";
    final String supplierPath = directoryPath + "/directoryTestFiles/postavshhik.yaml";
    final String receiverPath = directoryPath + "/directoryTestFiles/priemshhik.yaml";
    final String resultPath = "rezultat.yaml";

    @Before
    public void setUp() throws Exception {
        ConfigurableMockMvcBuilder builder =
                MockMvcBuilders.webAppContextSetup(this.webApplicationContext);
        this.mockMvc = builder.build();
    }

    @Test
    @SneakyThrows
    public void parseTest() {
        String parse = supplierParser + "/parse";

        Path supplier = Paths.get(supplierPath);
        Path receiver = Paths.get(receiverPath);

        MvcResult result = mockMvc.perform(post(parse)
                .accept(MediaType.APPLICATION_JSON)
                .content(Files.readAllBytes(supplier))
                .content(Files.readAllBytes(receiver)))
        .andExpect(status().isOk())
        .andReturn();

        String content = result.getResponse().getContentAsString();

        YamlMain resultJson = converterJSON.convertStringToJson(content, YamlMain.class);
        YamlMain resultPattern = getResultPattern();

        assertEquals(resultPattern, resultJson);
    }

    @SneakyThrows
    private YamlMain getResultPattern() {
        URL schema = ParserServiceTest.class.getClassLoader().getResource(resultPath);
        if (schema != null) {
            return converterJSON.convertYAMLToJSON(new File(schema.getFile().replace("%20", " ")), YamlMain.class);
        } else {
            return new YamlMain();
        }
    }
}