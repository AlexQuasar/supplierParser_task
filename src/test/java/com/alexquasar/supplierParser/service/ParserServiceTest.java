package com.alexquasar.supplierParser.service;

import com.alexquasar.supplierParser.dto.ConverterJSON;
import com.alexquasar.supplierParser.dto.yamlStructure.YamlMain;
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class ParserServiceTest {

    Logger log = Logger.getLogger(ParserServiceTest.class.getName());

    private ConverterJSON converterJSON = new ConverterJSON();

    final String directoryPath = ".";///src/test/java/com.alexquasar.supplierParser/testFiles";
    final String supplierPath = directoryPath + "/directoryTestFiles/postavshhik.yaml";
    final String receiverPath = directoryPath + "/directoryTestFiles/priemshhik.yaml";
    final String resultPath = "rezultat.yaml";

    @Test
    @SneakyThrows
    public void parseTest() {
        ParserService parserService = new ParserService();
        String result = parserService.parse(new File(supplierPath), new File(receiverPath));

        YamlMain resultJson = converterJSON.convertStringToJson(result, YamlMain.class);
        YamlMain resultPattern = getResultPattern();

        assertEquals(resultPattern, resultJson);
    }

    @SneakyThrows
    private YamlMain getResultPattern() {
        URL schema = ParserService.class.getClassLoader().getResource(resultPath);
        if (schema != null) {
            return converterJSON.convertYAMLToJSON(new File(schema.getFile().replace("%20", " ")), YamlMain.class);
        } else {
            return new YamlMain();
        }
    }

    @Test
    @SneakyThrows
    public void objectsTest() {
        YamlMain yamlMain1 = converterJSON.convertStringToJson(getJson1(), YamlMain.class);
        YamlMain yamlMain2 = converterJSON.convertStringToJson(getJson2(), YamlMain.class);

        assertEquals(yamlMain1, yamlMain2);
    }

    private String getJson1() {
        return "{\n" +
                "  \"entity\": {\n" +
                "    \"meta\": {\n" +
                "      \"uid\": null\n" +
                "    },\n" +
                "    \"data\": {\n" +
                "      \"data-id\": \"data\",\n" +
                "      \"integrity-fixed\": false,\n" +
                "      \"section\": [\n" +
                "        {\n" +
                "          \"section_id\": \"s1\",\n" +
                "          \"integrity-fixed\": false,\n" +
                "          \"section_type\": \"persist\",\n" +
                "          \"list\": [\n" +
                "            {\n" +
                "              \"list-id\": \"l1\",\n" +
                "              \"list-type\": \"various\",\n" +
                "              \"group\": [\n" +
                "                {\n" +
                "                  \"group-id\": \"g1\",\n" +
                "                  \"item\": [\n" +
                "                    {\n" +
                "                      \"item-id\": \"nomer_osn_protokola\",\n" +
                "                      \"label\": \"Номер основного протокола\",\n" +
                "                      \"type\": \"string\",\n" +
                "                      \"doc_proc_context\": \"always\",\n" +
                "                      \"context\": \"always\",\n" +
                "                      \"value\": \"10\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"item-id\": \"zayavitelj\",\n" +
                "                      \"id\": 1,\n" +
                "                      \"source\": \"С1\",\n" +
                "                      \"label\": \"Заявитель\",\n" +
                "                      \"type\": \"server-select\",\n" +
                "                      \"context\": \"always\",\n" +
                "                      \"value\": \"Объект 1\"\n" +
                "                    }\n" +
                "                  ]\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"list-id\": \"sotrudnik_otki\",\n" +
                "              \"list-type\": \"similar\",\n" +
                "              \"group\": [\n" +
                "                {\n" +
                "                  \"group-id\": \"g1\",\n" +
                "                  \"show_as\": \"card\",\n" +
                "                  \"group-num\" : null,\n" +
                "                  \"item\": [\n" +
                "                    {\n" +
                "                      \"item-id\": \"sotrudnik\",\n" +
                "                      \"id\" : null,\n" +
                "                      \"source\": \"С8\",\n" +
                "                      \"label\": \"ФИО\",\n" +
                "                      \"type\": \"server-select\",\n" +
                "                      \"value\": \"Кузнецов Андрей Александрович\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"item-id\": \"dolzhnostj\",\n" +
                "                      \"label\": \"Должность\",\n" +
                "                      \"type\": \"string\",\n" +
                "                      \"value\": \"Главный специалист\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"item-id\": \"podpisj\",\n" +
                "                      \"date\": \"01.01.2018\",\n" +
                "                      \"label\": \"Подпись\",\n" +
                "                      \"type\": \"signature\",\n" +
                "                      \"transition\": \"peredatj_na_podpisj_nachalniku_otki\"\n" +
                "                    }\n" +
                "                  ]\n" +
                "                },\n" +
                "                {\n" +
                "                  \"group-id\": \"g2\",\n" +
                "                  \"show_as\": \"card\",\n" +
                "                  \"group-num\" : null,\n" +
                "                  \"item\": [\n" +
                "                    {\n" +
                "                      \"item-id\": \"dolzhnostj\",\n" +
                "                      \"label\": \"Должность\",\n" +
                "                      \"type\": \"string\",\n" +
                "                      \"value\": \"Главный специалист\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"item-id\": \"podpisj\",\n" +
                "                      \"date\": \"01.01.2018\",\n" +
                "                      \"label\": \"Подпись\",\n" +
                "                      \"type\": \"signature\",\n" +
                "                      \"transition\": \"peredatj_na_podpisj_nachalniku_otki\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"item-id\": \"sotrudnik\",\n" +
                "                      \"id\" : null,\n" +
                "                      \"source\": \"С8\",\n" +
                "                      \"label\": \"ФИО\",\n" +
                "                      \"type\": \"server-select\",\n" +
                "                      \"value\": \"Смирнов Сергей Сергеевич\"\n" +
                "                    }\n" +
                "                  ]\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"section_id\": \"s2\",\n" +
                "          \"integrity-fixed\": false,\n" +
                "          \"list\": [\n" +
                "            {\n" +
                "              \"list-id\": \"sotrudnik_otki\",\n" +
                "              \"group\": [\n" +
                "                {\n" +
                "                  \"group-id\": \"nachalnik_otki\",\n" +
                "                  \"show_as\": \"group\",\n" +
                "                  \"item\": [\n" +
                "                    {\n" +
                "                      \"item-id\": \"dolzhnostj\",\n" +
                "                      \"label\": \"Начальник ОТКИ\",\n" +
                "                      \"type\": \"string\",\n" +
                "                      \"value\": \"Главный специалист\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"item-id\": \"podpisj\",\n" +
                "                      \"date\": \"01.01.2018\",\n" +
                "                      \"label\": \"Подпись\",\n" +
                "                      \"type\": \"signature\",\n" +
                "                      \"transition\": \"soglasovatj\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"item-id\": \"sotrudnik\",\n" +
                "                      \"source\": \"С8\",\n" +
                "                      \"label\": \"ФИО\",\n" +
                "                      \"type\": \"server-select\",\n" +
                "                      \"value\": \"Сергеев Сергей Сергеевич\"\n" +
                "                    }\n" +
                "                  ]\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";
    }

    private String getJson2() {
        return "{\n" +
                "  \"entity\": {\n" +
                "    \"meta\": {\n" +
                "      \"uid\": null\n" +
                "    },\n" +
                "    \"data\": {\n" +
                "      \"data-id\": \"data\",\n" +
                "      \"integrity-fixed\": false,\n" +
                "      \"section\": [\n" +
                "        {\n" +
                "          \"section_id\": \"s1\",\n" +
                "          \"integrity-fixed\": false,\n" +
                "          \"section_type\": \"persist\",\n" +
                "          \"list\": [\n" +
                "            {\n" +
                "              \"list-id\": \"l1\",\n" +
                "              \"list-type\": \"various\",\n" +
                "              \"group\": [\n" +
                "                {\n" +
                "                  \"group-id\": \"g1\",\n" +
                "                  \"item\": [\n" +
                "                    {\n" +
                "                      \"item-id\": \"nomer_osn_protokola\",\n" +
                "                      \"label\": \"Номер основного протокола\",\n" +
                "                      \"type\": \"string\",\n" +
                "                      \"doc_proc_context\": \"always\",\n" +
                "                      \"context\": \"always\",\n" +
                "                      \"value\": 10\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"item-id\": \"zayavitelj\",\n" +
                "                      \"id\": 1,\n" +
                "                      \"source\": \"С1\",\n" +
                "                      \"label\": \"Заявитель\",\n" +
                "                      \"type\": \"server-select\",\n" +
                "                      \"context\": \"always\",\n" +
                "                      \"value\": \"Объект 1\"\n" +
                "                    }\n" +
                "                  ]\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"list-id\": \"sotrudnik_otki\",\n" +
                "              \"list-type\": \"similar\",\n" +
                "              \"group\": [\n" +
                "                {\n" +
                "                  \"group-id\": \"g1\",\n" +
                "                  \"show_as\": \"card\",\n" +
                "                  \"group-num\": null,\n" +
                "                  \"item\": [\n" +
                "                    {\n" +
                "                      \"item-id\": \"sotrudnik\",\n" +
                "                      \"id\": null,\n" +
                "                      \"label\": \"ФИО\",\n" +
                "                      \"type\": \"server-select\",\n" +
                "                      \"value\": \"Кузнецов Андрей Александрович\",\n" +
                "                      \"source\": \"С8\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"item-id\": \"dolzhnostj\",\n" +
                "                      \"label\": \"Должность\",\n" +
                "                      \"type\": \"string\",\n" +
                "                      \"value\": \"Главный специалист\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"item-id\": \"podpisj\",\n" +
                "                      \"date\": \"01.01.2018\",\n" +
                "                      \"label\": \"Подпись\",\n" +
                "                      \"type\": \"signature\",\n" +
                "                      \"transition\": \"peredatj_na_podpisj_nachalniku_otki\"\n" +
                "                    }\n" +
                "                  ]\n" +
                "                },\n" +
                "                {\n" +
                "                  \"group-id\": \"g2\",\n" +
                "                  \"show_as\": \"card\",\n" +
                "                  \"group-num\": null,\n" +
                "                  \"item\": [\n" +
                "                    {\n" +
                "                      \"item-id\": \"sotrudnik\",\n" +
                "                      \"id\": null,\n" +
                "                      \"label\": \"ФИО\",\n" +
                "                      \"type\": \"server-select\",\n" +
                "                      \"value\": \"Смирнов Сергей Сергеевич\",\n" +
                "                      \"source\": \"С8\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"item-id\": \"dolzhnostj\",\n" +
                "                      \"label\": \"Должность\",\n" +
                "                      \"type\": \"string\",\n" +
                "                      \"value\": \"Главный специалист\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"item-id\": \"podpisj\",\n" +
                "                      \"date\": \"01.01.2018\",\n" +
                "                      \"label\": \"Подпись\",\n" +
                "                      \"type\": \"signature\",\n" +
                "                      \"transition\": \"peredatj_na_podpisj_nachalniku_otki\"\n" +
                "                    }\n" +
                "                  ]\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"section_id\": \"s2\",\n" +
                "          \"integrity-fixed\": false,\n" +
                "          \"list\": [\n" +
                "            {\n" +
                "              \"list-id\": \"nachalnik_otki\",\n" +
                "              \"list-type\": \"various\",\n" +
                "              \"group\": [\n" +
                "                {\n" +
                "                  \"group-id\": \"nachalnik_otki\",\n" +
                "                  \"show_as\": \"group\",\n" +
                "                  \"item\": [\n" +
                "                    {\n" +
                "                      \"item-id\": \"sotrudnik\",\n" +
                "                      \"id\": null,\n" +
                "                      \"label\": \"ФИО\",\n" +
                "                      \"type\": \"server-select\",\n" +
                "                      \"value\": \"Сергеев Сергей Сергеевич\",\n" +
                "                      \"source\": \"С8\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"item-id\": \"dolzhnostj\",\n" +
                "                      \"label\": \"Начальник ОТКИ\",\n" +
                "                      \"type\": \"string\",\n" +
                "                      \"value\": \"Главный специалист\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"item-id\": \"podpisj\",\n" +
                "                      \"date\": \"01.01.2018\",\n" +
                "                      \"label\": \"Подпись\",\n" +
                "                      \"type\": \"signature\",\n" +
                "                      \"transition\": \"soglasovatj\"\n" +
                "                    }\n" +
                "                  ]\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";
    }
}