package com.alexquasar.supplierParser.service;

import com.alexquasar.supplierParser.dto.ConverterJSON;
import com.alexquasar.supplierParser.dto.DataProcessing;
import com.alexquasar.supplierParser.dto.yamlStructure.*;
import com.alexquasar.supplierParser.exception.ServiceException;
import com.alexquasar.supplierParser.service.interfaces.Parser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Service
public class ParserService implements Parser {

    private final String schemaPath = "rezultat.yaml";
    private ConverterJSON converterJSON = new ConverterJSON();
    private DataProcessing dataProcessing = new DataProcessing();

    @Override
    public String parse(File supplierFile, File receiverFile) {
        YamlMain supplier;
        YamlMain receiver;
        YamlMain resultPattern;

        try {
            supplier = converterJSON.convertYAMLToJSON(supplierFile, YamlMain.class);
            receiver = converterJSON.convertYAMLToJSON(receiverFile, YamlMain.class);
            resultPattern = converterJSON.convertYAMLToJSON(getFileResultPattern(), YamlMain.class);
        } catch (IOException ex) {
            throw new ServiceException("can't read files", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        YamlMain resultJoin = dataProcessing.joinData(supplier, receiver);

//        if (!resultJoin.equals(resultPattern)) {
//            throw new ServiceException("result object doesn't match schema pattern", HttpStatus.CONFLICT);
//        }

        try {
//            converterJSON.writeJSON("./directoryTestFiles/result.json", resultJoin); // filePath not normal!
            return converterJSON.writeJSONString(resultJoin);
        } catch (IOException ex) {
            throw new ServiceException("can't write files", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private File getFileResultPattern() {
        URL schema = ParserService.class.getClassLoader().getResource(schemaPath);
        if (schema != null) {
            return new File(schema.getFile().replace("%20", " "));
        } else {
            throw new ServiceException("can't find schema pattern", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
