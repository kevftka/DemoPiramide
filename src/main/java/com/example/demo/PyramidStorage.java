package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PyramidStorage {
    private List<Pyramid> pyramids = new ArrayList<>();
    private final String JSON_FILE = "pyramids.json";
    private ObjectMapper objectMapper = new ObjectMapper();

    public PyramidStorage() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        loadPyramidsFromJson();
    }

    private void loadPyramidsFromJson() {
        try {
            File file = new File(JSON_FILE);
            if (file.exists()) {
                CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Pyramid.class);
                pyramids = objectMapper.readValue(file, listType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void savePyramidsToJson() {
        try {
            objectMapper.writeValue(new File(JSON_FILE), pyramids);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Pyramid> getAllPyramids() {
        return pyramids;
    }

    public void savePyramid(Pyramid pyramid) {
        pyramids.add(pyramid);
        savePyramidsToJson();
    }
}
