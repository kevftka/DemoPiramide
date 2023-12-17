package com.example.demo;
import java.util.List;

public class SavePyramidRequest {
    private List<List<Integer>> pyramidData;
    private List<Integer> maxPathRoute;
    private int maxRouteSum;
    private int height;

    public SavePyramidRequest(List<List<Integer>> pyramidData, List<Integer> maxPathRoute, int maxRouteSum, int height) {
        this.pyramidData = pyramidData;
        this.maxPathRoute = maxPathRoute;
        this.maxRouteSum = maxRouteSum;
        this.height = height;
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
