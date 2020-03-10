package com.alexquasar.supplierParser.dto;

import com.alexquasar.supplierParser.dto.yamlStructure.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataProcessing {

    private String similarListType = "similar";

    public YamlMain joinData(YamlMain supplier, YamlMain receiver) {
        List<Section> sectionsSupplier = supplier.getEntity().getData().getSections();
        List<Section> sectionsReceiver = receiver.getEntity().getData().getSections();

        List<Item> itemsSupplier = getAllItems(sectionsSupplier, similarListType);
        List<Item> itemsReceiver = getAllItems(sectionsReceiver, "");
        addItems(itemsSupplier, itemsReceiver);

//        Map<String ,List<SectionList>> sectionsListsMap = getSpecificLists(sectionsSupplier, similarListType);
        addSpecificLists(sectionsSupplier, sectionsReceiver);

//        Map<String, List<SectionList>> sectionListsOnId = getSpecificLists(sectionsSupplier, similarListType);
//        addSpecificLists(sectionListsOnId, sectionsReceiver);

        return receiver;
    }

    private List<Item> getAllItems(List<Section> sections, String listTypeFilter) {
        List<Item> items = new ArrayList<>();

        for (Section section : sections) {
            List<SectionList> sectionLists;

            if (listTypeFilter.equals("")) {
                sectionLists = section.getLists();
            } else  {
                sectionLists = new ArrayList<>();
                for (SectionList sectionList : section.getLists()) {
                    if (!checkEqualsListType(sectionList, listTypeFilter)) {
                        sectionLists.add(sectionList);
                    }
                }
            }

            sectionLists.forEach(i -> i.getGroups().forEach(j -> items.addAll(j.getItems())));
        }

        return items;
    }

    private void addItems(List<Item> itemsSupplier, List<Item> itemsReceiver) {
        for (Item itemSupplier : itemsSupplier) {
            for (Item itemReceiver : itemsReceiver) {
                if (itemSupplier.getItemId().equals(itemReceiver.getItemId())) {
                    itemReceiver.setValue(itemSupplier.getValue());
                }
            }
        }
    }

    private  Map<String ,List<SectionList>> getSpecificLists(List<Section> sections, String listType) {
        Map<String ,List<SectionList>> sectionsListsMap = new HashMap<>();

        for (Section section : sections) {
            List<SectionList> sectionLists = new ArrayList<>();
            for (SectionList sectionList : section.getLists()) {
//                if (checkEqualsListType(sectionList, listType)) {
                    sectionLists.add(sectionList);
//                }
            }
            sectionsListsMap.put(section.getSection_id(), sectionLists);
        }

        return sectionsListsMap;
    }

    private void addSpecificLists(List<Section> sectionsSupplier, List<Section> sectionsReceiver) {
        for (Section section : sectionsSupplier) {
            boolean sectionFound = false;

            for (Section sectionReceiver : sectionsReceiver) {
                if (section.getSection_id().equals(sectionReceiver.getSection_id())) {
                    setGroupedSection(section, sectionReceiver);
                    sectionFound = true;
                }
            }

            if (!sectionFound) {
                sectionsReceiver.add(section);
            }
        }
    }

    private void setGroupedSection(Section section, Section sectionReceiver) {
        for (SectionList sectionList : section.getLists()) {
            boolean listFound = false;
            boolean isSimilar = false;

            List<SectionList> sectionListsReceiver = sectionReceiver.getLists();
            for (SectionList sectionListReceiver : sectionListsReceiver) {
                isSimilar = sectionListReceiver.getListType() != null
                        && sectionListReceiver.getListType().equals(similarListType);

                if (sectionList.getListId().equals(sectionListReceiver.getListId()) && isSimilar) {
                    setGroupedGroups(sectionList, sectionListReceiver);
                    listFound = true;
                }
            }

            if (!listFound && isSimilar) {
                sectionListsReceiver.add(sectionList);
            }
        }
    }

    private void setGroupedGroups(SectionList sectionList, SectionList sectionListReceiver) {
        for (Group group : sectionList.getGroups()) {
            boolean groupFound = false;

            List<Group> groups = sectionListReceiver.getGroups();
            for (Group groupReceiver : groups) {
                if (group.getGroupId().equals(groupReceiver.getGroupId())) {
                    setGroupedItems(group, groupReceiver);
                    groupFound = true;
                }
            }

            if (!groupFound) {
                groups.add(group);
            }
        }
    }

    private void setGroupedItems(Group group, Group groupReceiver) {
        for (Item item : group.getItems()) {
            boolean itemFound = false;

            List<Item> items = groupReceiver.getItems();
            for (Item itemReceiver : items) {
                if (item.getItemId().equals(itemReceiver.getItemId())) {
                    itemReceiver.setValue(item.getValue());
                    itemFound = true;
                }
            }

            if (!itemFound) {
                items.add(item);
            }
        }
    }

//    private Map<String, List<SectionList>> getSpecificLists(List<Section> sections, String listType) {
//        Map<String, List<SectionList>> sectionListsOnId = new HashMap<>();
//
//        for (Section section : sections) {
//            for (SectionList sectionList : section.getLists()) {
//                List<SectionList> sectionLists = new ArrayList<>();
//                if (checkEqualsListType(sectionList, listType)) {
//                    sectionLists.add(sectionList);
//                }
//
//                List<SectionList> findSectionLists = sectionListsOnId.get(sectionList.getListId());
//                if (findSectionLists == null) {
//                    sectionListsOnId.put(sectionList.getListId(), sectionLists);
//                } else {
//                    sectionListsOnId.put(sectionList.getListId(), getGroupsLists(findSectionLists, sectionLists));
//                }
//            }
//        }
//
//        return sectionListsOnId;
//    }
//
//    private List<SectionList> getGroupsLists(List<SectionList> findSectionLists, List<SectionList> sectionLists) {
//        List<SectionList> newSectionLists = new ArrayList<>(findSectionLists);
//
//        for (SectionList findSectionList : findSectionLists) {
//            if (!isListFind(sectionLists, findSectionList)) {
//                for (SectionList sectionList : sectionLists) {
//                    if (findSectionList.getListId().equals(sectionList.getListId())) {
//                        newSectionLists.add(sectionList);
//                    }
//                }
//            }
//        }
//
//        return newSectionLists;
//    }
//
//    private boolean isListFind(List<SectionList> sectionLists, SectionList findSectionList) {
//        boolean listFind = false;
//        for (SectionList sectionList : sectionLists) {
//            if (findSectionList.getListId().equals(sectionList.getListId())) {
//                listFind = true;
//            }
//        }
//        return listFind;
//    }
//
//    private void addSpecificLists(Map<String, List<SectionList>> sectionListsOnId, List<Section> sectionsReceiver) {
//        for (Map.Entry<String, List<SectionList>> entry : sectionListsOnId.entrySet()) {
//            String sectionId = entry.getKey();
//            List<SectionList> sectionLists = entry.getValue();
//
//            for (Section section : sectionsReceiver) {
//                List<SectionList> sectionListsReceiver = section.getLists();
//            }
//        }
//    }

    private Boolean checkEqualsListType(SectionList sectionList, String listType) {
        return sectionList.getListType() != null
                && sectionList.getListType().equals(listType);
    }
}
