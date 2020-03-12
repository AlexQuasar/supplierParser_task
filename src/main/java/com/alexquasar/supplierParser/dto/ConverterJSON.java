package com.alexquasar.supplierParser.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class ConverterJSON {

    private ObjectMapper mapper;

    public ConverterJSON() {
        mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public <T> T convertYAMLToJSON(String yamlText, Class<T> clazz) throws IOException {
        String jsonText = convertYAMLToJSON(yamlText);
        return mapper.readValue(jsonText, clazz);
    }

    public <T> T convertYAMLToJSON(File yamlFile, Class<T> clazz) throws IOException {
        String jsonText = convertYAMLToJSON(yamlFile);
        return mapper.readValue(jsonText, clazz);
    }

    public <T> T convertStringToJson(String content, Class<T> clazz) throws IOException {
        return mapper.readValue(content, clazz);
    }

    public String convertYAMLToJSON(String yamlText) throws IOException {
        Object obj = readYAML(yamlText);
        return mapper.writeValueAsString(obj);
    }

    public String convertYAMLToJSON(File yamlFile) throws IOException {
        Object obj = readYAML(yamlFile);
        return mapper.writeValueAsString(obj);
    }

    public Object readYAML(String yamlText) throws IOException {
        ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
        return yamlReader.readValue(yamlText, Object.class);
    }

    public Object readYAML(File yamlFile) throws IOException {
        ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
        return yamlReader.readValue(yamlFile, Object.class);
    }

    public <T> void writeJSON(String filePath, T obj) throws IOException {
        mapper.writeValue(new File(filePath), obj);
    }

    public <T> String writeJSONString(T obj) throws IOException {
        return mapper.writeValueAsString(obj);
    }
}
