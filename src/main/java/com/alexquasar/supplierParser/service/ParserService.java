package com.alexquasar.supplierParser.service;

import com.alexquasar.supplierParser.dto.ConverterJSON;
import com.alexquasar.supplierParser.dto.DataProcessing;
import com.alexquasar.supplierParser.dto.yamlStructure.*;
import com.alexquasar.supplierParser.exception.ServiceException;
import com.alexquasar.supplierParser.service.interfaces.Parser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Service
public class ParserService implements Parser {

    private final String supplierName = "postavshhik.yaml";
    private final String receiverName = "priemshhik.yaml";
    private final String schemaPath = "rezultat.yaml";
    private ConverterJSON converterJSON = new ConverterJSON();
    private DataProcessing dataProcessing = new DataProcessing();

    public String parse(List<MultipartFile> files) {
        byte[] supplierBytes = new byte[]{};
        byte[] receiverBytes = new byte[]{};

        for (MultipartFile file : files) {
            byte[] fileBytes;
            String fileName = file.getOriginalFilename();

            fileBytes = getFileBytes(supplierName, fileName, file);
            if (fileBytes.length != 0) {
                supplierBytes = fileBytes;
            }
            fileBytes = getFileBytes(receiverName, fileName, file);
            if (fileBytes.length != 0) {
                receiverBytes = fileBytes;
            }
        }

        if (supplierBytes.length == 0 || receiverBytes.length == 0) {
            throw new ServiceException("file(s) not contain(s) data", HttpStatus.NO_CONTENT);
        }

        return parse(supplierBytes, receiverBytes);
    }

    private byte[] getFileBytes(String patternFileName, String fileName, MultipartFile file) {
        if (fileName.equals(patternFileName)) {
            try {
                return file.getBytes();
            } catch (IOException ex) {
                throw new ServiceException("can't get content file", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new byte[]{};
    }

    public String parse(byte[] supplierBytes, byte[] receiverBytes) {
        return parse(new String(supplierBytes), new String(receiverBytes));
    }

    @Override
    public String parse(String yamlSupplier, String yamlReceiver) {
        YamlMain supplier;
        YamlMain receiver;
        YamlMain resultPattern;

        try {
            supplier = converterJSON.convertYAMLToJSON(yamlSupplier, YamlMain.class);
            receiver = converterJSON.convertYAMLToJSON(yamlReceiver, YamlMain.class);
            resultPattern = converterJSON.convertYAMLToJSON(getFileResultPattern(), YamlMain.class);
        } catch (IOException ex) {
            throw new ServiceException("can't read files", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        YamlMain resultJoin = dataProcessing.joinData(supplier, receiver);

        if (!resultJoin.equals(resultPattern)) {
            throw new ServiceException("result object doesn't match schema pattern", HttpStatus.CONFLICT);
        }

        try {
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
