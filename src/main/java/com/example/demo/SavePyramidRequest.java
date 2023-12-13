package com.example.demo;
import java.util.List;

public class SavePyramidRequest {
    private List<Integer> pyramidData;
    private List<Integer> maxPath;
    private int maxPathSum;
    private int height;

    public SavePyramidRequest(List<Integer> pyramidData, List<Integer> maxPath, int maxPathSum, int height) {
        this.pyramidData = pyramidData;
        this.maxPath = maxPath;
        this.maxPathSum = maxPathSum;
        this.height = height;
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
