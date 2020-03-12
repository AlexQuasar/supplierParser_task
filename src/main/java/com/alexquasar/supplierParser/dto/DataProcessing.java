package com.alexquasar.supplierParser.dto;

import com.alexquasar.supplierParser.dto.yamlStructure.*;

import java.util.HashSet;
import java.util.Set;

public class DataProcessing {

    private final String similarListType = "similar";

    public YamlMain joinData(YamlMain supplier, YamlMain receiver) {
        Set<Section> sectionsSupplier = supplier.getEntity().getData().getSections();
        Set<Section> sectionsReceiver = receiver.getEntity().getData().getSections();

        Set<Item> itemsSupplier = getAllItems(sectionsSupplier, similarListType);
        Set<Item> itemsReceiver = getAllItems(sectionsReceiver, "");
        addItems(itemsSupplier, itemsReceiver);

        addSpecificLists(sectionsSupplier, sectionsReceiver);

        return receiver;
    }

    private Set<Item> getAllItems(Set<Section> sections, String listTypeFilter) {
        Set<Item> items = new HashSet<>();

        for (Section section : sections) {
            Set<SectionList> sectionLists;

            if (listTypeFilter.equals("")) {
                sectionLists = section.getLists();
            } else  {
                sectionLists = new HashSet<>();
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

    private void addItems(Set<Item> itemsSupplier, Set<Item> itemsReceiver) {
        for (Item itemSupplier : itemsSupplier) {
            for (Item itemReceiver : itemsReceiver) {
                if (itemSupplier.getItemId().equals(itemReceiver.getItemId())) {
                    itemReceiver.setValue(itemSupplier.getValue());
                }
            }
        }
    }

    private void addSpecificLists(Set<Section> sectionsSupplier, Set<Section> sectionsReceiver) {
        for (Section section : sectionsSupplier) {
            boolean sectionFound = false;

            if (extendSimilarList(section)) {
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
    }

    private Boolean extendSimilarList(Section section) {
        long countSimilarList = section.getLists().stream()
                .filter(i -> i.getListType() != null && i.getListType().equals(similarListType))
                .count();
        return countSimilarList > 0L;
    }

    private void setGroupedSection(Section section, Section sectionReceiver) {
        for (SectionList sectionList : section.getLists()) {
            boolean listFound = false;

            if (sectionList.getListType() != null
                    && sectionList.getListType().equals(similarListType)) {
                Set<SectionList> sectionListsReceiver = sectionReceiver.getLists();
                for (SectionList sectionListReceiver : sectionListsReceiver) {
                    String listType = sectionListReceiver.getListType();

                    if (listType.equals(sectionListReceiver.getListId())) {
                        setGroupedGroups(sectionList, sectionListReceiver);
                        listFound = true;
                    }
                }

                if (!listFound) {
                    sectionListsReceiver.add(sectionList);
                }
            }
        }
    }

    private void setGroupedGroups(SectionList sectionList, SectionList sectionListReceiver) {
        for (Group group : sectionList.getGroups()) {
            boolean groupFound = false;

            Set<Group> groups = sectionListReceiver.getGroups();
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

            Set<Item> items = groupReceiver.getItems();
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

    private Boolean checkEqualsListType(SectionList sectionList, String listType) {
        return sectionList.getListType() != null
                && sectionList.getListType().equals(listType);
    }
}
