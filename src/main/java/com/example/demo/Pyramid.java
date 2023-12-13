package com.example.demo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Pyramid {
    private List<Integer> pyramidData;
    private List<Integer> maxPath;
    private int maxPathSum;
    private int height;

    // public Pyramid() {}

    @JsonCreator
    public Pyramid(@JsonProperty("pyramidData") List<Integer> pyramidData, @JsonProperty("maxPath") List<Integer> maxPath, @JsonProperty("maxPathSum") int maxPathSum, @JsonProperty("height") int height) {
        this.pyramidData = pyramidData;
        this.maxPath = maxPath;
        this.height = height;
        this.maxPathSum = maxPathSum;
    }

    public List<Integer> getPyramidData() {
        return pyramidData;
    }

    public void setPyramidData(List<Integer> pyramidData) {
        this.pyramidData = pyramidData;
    }

    public List<Integer> getMaxPath() {
        return maxPath;
    }

    public void setMaxPath(List<Integer> maxPath) {
        this.maxPath = maxPath;
    }

    public int getMaxPathSum() {
        return maxPathSum;
    }

    public void setMaxPathSum(int maxPathSum) {
        this.maxPathSum = maxPathSum;
    }
    
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
