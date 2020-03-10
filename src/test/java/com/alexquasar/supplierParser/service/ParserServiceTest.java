package com.alexquasar.supplierParser.service;

import com.alexquasar.supplierParser.dto.ConverterJSON;
import com.alexquasar.supplierParser.dto.yamlStructure.YamlMain;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

public class ParserServiceTest {

    Logger log = Logger.getLogger(ParserServiceTest.class.getName());

    final String directoryPath = ".";///src/test/java/com.alexquasar.supplierParser/testFiles";
    final String supplierPath = directoryPath + "/directoryTestFiles/postavshhik.yaml";
    final String receiverPath = directoryPath + "/directoryTestFiles/priemshhik.yaml";
    final String resultPath = directoryPath + "/directoryTestFiles/rezultat.yaml";

    @Test
    public void converterTest() {
        ConverterJSON converterJSON = new ConverterJSON();

        String supplierYAML = "";
        YamlMain yamlMain = new YamlMain();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        try {
//            supplierYAML = converterJSON.convertYAMLToJSON(new File(supplierPath));
            yamlMain = converterJSON.convertYAMLToJSON(new File(supplierPath), YamlMain.class);
//            linkedHashMap = (LinkedHashMap) converterJSON.readYAML(new File(supplierPath));
//            yamlMain = converterJSON.convertStringToJson(linkedHashMap.toString(), YamlMain.class);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println(supplierYAML);
        System.out.println(yamlMain);
        System.out.println(linkedHashMap);
    }

    @Test
    public void parseTest() {
        ParserService parserService = new ParserService();
        String empty = parserService.parse(new File(supplierPath), new File(receiverPath));

        System.out.println(empty);
    }
}