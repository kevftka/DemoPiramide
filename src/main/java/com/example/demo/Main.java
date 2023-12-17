package com.example.demo;

import com.google.gson.Gson;

import static spark.Spark.*;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        staticFiles.location("public");


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
                    saveRequest.getMaxPathRoute(),
                    saveRequest.getMaxRouteSum(),
                    saveRequest.getHeight()
            );
            pyramidStorage.savePyramid(pyramidData);

            return "Pyramid saved successfully!";
        });

        get("/generate-pyramid", Main::generatePyramid, new JsonTransformer());

    }

    private static PyramidResponse generatePyramid(Request req, Response res) {
        int height = Integer.parseInt(req.queryParams("height"));
        List<List<Integer>> pyramid = new ArrayList<>();

        for (int i = 1; i <= height; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                int randomNumber = new Random().nextInt(99) + 1;
                temp.add(randomNumber);
            }
            pyramid.add(temp);
        }

        PyramidPathResult pathResult = calculateMaxRoutePath(pyramid, height);
        return new PyramidResponse(pyramid, pathResult.getMaxRouteSum(), pathResult.getMaxPathRoute());
    }

    private static PyramidPathResult calculateMaxRoutePath(List<List<Integer>> pyramid, int height) {
        List<Integer> maxPathRoute = new ArrayList<>();
    
        int maxRouteSum = 0;
        int currentIndex = 0;
        maxPathRoute.add(0);

        for (int i = 1; i < pyramid.size(); i++) {
            int leftValue = pyramid.get(i).get(currentIndex);
            int rightValue = pyramid.get(i).get(currentIndex+1);

            if (leftValue > rightValue) {
                maxRouteSum += leftValue;
                maxPathRoute.add(currentIndex);
                // currentIndex = i;
            } else if (leftValue <= rightValue) {
                maxRouteSum += rightValue;
                maxPathRoute.add(currentIndex+1);
                currentIndex += 1;
            }
        }
    
        return new PyramidPathResult(maxRouteSum, maxPathRoute);
    }
}
