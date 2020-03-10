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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ParserService implements Parser {

    private ConverterJSON converterJSON = new ConverterJSON();
    private DataProcessing dataProcessing = new DataProcessing();

    @Override
    public String parse(File supplierFile, File receiverFile) {
        YamlMain supplier;
        YamlMain receiver;
        try {
            supplier = converterJSON.convertYAMLToJSON(supplierFile, YamlMain.class);
            receiver = converterJSON.convertYAMLToJSON(receiverFile, YamlMain.class);
        } catch (IOException ex) {
            throw new ServiceException("can't read files", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        YamlMain resultJoin = dataProcessing.joinData(supplier, receiver);

        try {
            converterJSON.writeJSON("./directoryTestFiles/result.json", resultJoin); // filePath not normal!
        } catch (IOException ex) {
            throw new ServiceException("can't write files", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return "yep";
    }

//    public String parse(String supplier, String receiver) {
//        LinkedHashMap rootSupplier;
//        LinkedHashMap rootReceiver;
//        try {
//            rootSupplier = (LinkedHashMap) converterJSON.readYAML(supplier);
//            rootReceiver = (LinkedHashMap) converterJSON.readYAML(receiver);
//        } catch (IOException ex) {
//            throw new ServiceException("", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        return parseLinkedHashMap(rootSupplier, rootReceiver);
//    }
//
//    private String parseLinkedHashMap(LinkedHashMap rootSupplier, LinkedHashMap rootReceiver) {
//        if (rootSupplier == null || rootReceiver == null) {
//            throw new ServiceException("file is empty", HttpStatus.NO_CONTENT);
//        }
//
//        List<LinkedHashMap> sectionsSupplier = getSectionObject(rootSupplier);
//        List<LinkedHashMap> sectionsReceiver = getSectionObject(rootReceiver);
//
//        joinData(sectionsSupplier, sectionsReceiver);
//
//        System.out.println(rootSupplier);
//        System.out.println();
//        System.out.println(rootReceiver);
//
//        return "";
//    }
//
//    private List<LinkedHashMap> getSectionObject(LinkedHashMap root) {
//        LinkedHashMap entity = (LinkedHashMap) root.get("entity");
//        LinkedHashMap data = (LinkedHashMap) entity.get("data");
//        List<LinkedHashMap> sections = (List<LinkedHashMap>) data.get("section");
//        return sections;
//    }
//
//    private void joinData(List<LinkedHashMap> supplier, List<LinkedHashMap> receiver) {
//        for (LinkedHashMap sections : supplier) {
//            String sectionId = (String) sections.get("section_id");
//
//            List<LinkedHashMap> lists = (List<LinkedHashMap>) sections.get("list");
//            for (LinkedHashMap list : lists) {
//                String listId = (String) list.get("list-id");
//                String listTypeValue = (String) list.get("list-type");
//
//                if (!listTypeValue.equals("similar")) {
//                    List<LinkedHashMap> groups = (List<LinkedHashMap>) list.get("group");
//                    for (LinkedHashMap group : groups) {
//                        String groupId = (String) group.get("group-id");
//
//                        List<LinkedHashMap> items = (List<LinkedHashMap>) group.get("item");
//                        joinSupplier(items, receiver, sectionId, listId, groupId);
//                    }
//                }
//            }
//        }
//    }
//
//    private void joinSupplier(List<LinkedHashMap> itemsSupplier, List<LinkedHashMap> receiver,
//                              String sectionIdSupplier, String listIdSupplier, String groupIdSupplier) {
//            for (LinkedHashMap sections : receiver) {
//                String sectionId = (String) sections.get("section_id");
//
//                if (sectionId.equals(sectionIdSupplier)) {
//                    searchInlist(itemsSupplier, listIdSupplier, groupIdSupplier, sections);
//                }
//            }
//    }
//
//    private void searchInlist(List<LinkedHashMap> itemsSupplier, String listIdSupplier, String groupIdSupplier, LinkedHashMap sections) {
//        List<LinkedHashMap> lists = (List<LinkedHashMap>) sections.get("list");
//        for (LinkedHashMap list : lists) {
//            String listId = (String) list.get("list-id");
//            if (listId.equals(listIdSupplier)) {
//                searchInGroup(itemsSupplier, groupIdSupplier, list);
//            }
//        }
//    }
//
//    private void searchInGroup(List<LinkedHashMap> itemsSupplier, String groupIdSupplier, LinkedHashMap list) {
//        List<LinkedHashMap> groups = (List<LinkedHashMap>) list.get("group");
//        for (LinkedHashMap group : groups) {
//            String groupId = (String) group.get("group-id");
//
//            if (groupId.equals(groupIdSupplier)) {
//                searchInItems(itemsSupplier, group);
//            }
//        }
//    }
//
//    private void searchInItems(List<LinkedHashMap> itemsSupplier, LinkedHashMap group) {
//        for (LinkedHashMap itemSupplier : itemsSupplier) {
//            String itemIdSupplier = (String) itemSupplier.get("item-id");
//            List<LinkedHashMap> items = (List<LinkedHashMap>) group.get("item");
//            for (LinkedHashMap item : items) {
//                String itemId = (String) item.get("item-id");
//                if (itemId != null && itemId.equals(itemIdSupplier)) {
//                    String value = (String) itemSupplier.get("value");
//                    item.put("value", value);
//                }
//            }
//        }
//    }
}
