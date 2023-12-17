package com.example.demo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Pyramid {
    private List<List<Integer>> pyramidData;
    private List<Integer> maxPathRoute;
    private int maxRouteSum;
    private int height;

    // public Pyramid() {}

    @JsonCreator
    public Pyramid(@JsonProperty("pyramidData") List<List<Integer>> pyramidData, @JsonProperty("maxPathRoute") List<Integer> maxPathRoute, @JsonProperty("maxRouteSum") int maxRouteSum, @JsonProperty("height") int height) {
        this.pyramidData = pyramidData;
        this.maxPathRoute = maxPathRoute;
        this.height = height;
        this.maxRouteSum = maxRouteSum;
    }

    public List<List<Integer>> getPyramidData() {
        return pyramidData;
    }

    public void setPyramidData(List<List<Integer>> pyramidData) {
        this.pyramidData = pyramidData;
    }

    public List<Integer> getMaxPathRoute() {
        return maxPathRoute;
    }

    public void setMaxPath(List<Integer> maxPathRoute) {
        this.maxPathRoute = maxPathRoute;
    }

    public int getMaxRouteSum() {
        return maxRouteSum;
    }

    public void setMaxRouteSum(int maxRouteSum) {
        this.maxRouteSum = maxRouteSum;
    }
    
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
