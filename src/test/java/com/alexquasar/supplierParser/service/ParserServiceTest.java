package com.alexquasar.supplierParser.service;

import com.alexquasar.supplierParser.dto.ConverterJSON;
import com.alexquasar.supplierParser.dto.yamlStructure.YamlMain;
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class ParserServiceTest {

    Logger log = Logger.getLogger(ParserServiceTest.class.getName());

    ConverterJSON converterJSON = new ConverterJSON();
    final String directoryPath = ".";///src/test/java/com.alexquasar.supplierParser/testFiles";
    final String supplierPath = directoryPath + "/directoryTestFiles/postavshhik.yaml";
    final String receiverPath = directoryPath + "/directoryTestFiles/priemshhik.yaml";
    final String resultPath = "rezultat.yaml";

    @Test
    @SneakyThrows
    public void parseTest() {
        ParserService parserService = new ParserService();

        Path supplier = Paths.get(supplierPath);
        Path receiver = Paths.get(receiverPath);
        String result = parserService.parse(Files.readAllBytes(supplier), Files.readAllBytes(receiver));

        YamlMain resultJson = converterJSON.convertStringToJson(result, YamlMain.class);
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