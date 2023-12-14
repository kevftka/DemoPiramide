package com.example.demo;

import com.google.gson.Gson;

import static spark.Spark.*;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Version;


public class Main {


    public static void main(String[] args) {
        port(3000);

        PyramidStorage pyramidStorage = new PyramidStorage();
        Gson gson = new Gson();


        Configuration configuration = new Configuration(new Version(2, 3, 23));
        configuration.setTemplateLoader(new ClassTemplateLoader(Main.class, "/templates"));


        // Set the static file location
        staticFiles.location("/public");


        // API to list all pyramids
        get("/stored-pyramids", (req, res) -> pyramidStorage.getAllPyramids(), gson::toJson);

        // API to save a new pyramid
        post("/save-pyramid", (req, res) -> {
            String requestBody = req.body();
            
            // Parse the JSON request body
            SavePyramidRequest saveRequest = gson.fromJson(requestBody, SavePyramidRequest.class);

            // Store pyramid data, path, and max weight
            Pyramid pyramidData = new Pyramid(
                    saveRequest.getPyramidData(),
                    saveRequest.getMaxPath(),
                    saveRequest.getMaxPathSum(),
                    saveRequest.getHeight()
            );
            pyramidStorage.savePyramid(pyramidData);


            return "Pyramid saved successfully!";
        });

    }
}
