package com.leony.home;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

public class ConsumingJSONWithDOMApi {
    private final File PERSONS_JSON_FILE = new File("src/com/leony/resources/persons.json");

    public void readJSONFile() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(PERSONS_JSON_FILE);
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        System.out.println(objectWriter.writeValueAsString(jsonNode));
    }

    public void readAndParseJsonTree() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(PERSONS_JSON_FILE);
        printJSONTree(root);
    }

    private void printJSONTree(JsonNode root) {
        Iterator<Map.Entry<String, JsonNode>> iterator = root.fields();
        while (iterator.hasNext()) {
            Map.Entry<String, JsonNode> node = iterator.next();
            String nodeKey = node.getKey();
            JsonNode nodeValue = node.getValue();
            System.out.println("Reading node key: " + nodeKey);
            if (nodeValue.isObject()) {
                printJSONTree(nodeValue);
            } else if (nodeValue.isArray()) {
                System.out.println("Reading node array value: " + nodeValue);
            } else {
                System.out.println("Reading node test value: " + nodeValue);
            }
        }
    }
}
