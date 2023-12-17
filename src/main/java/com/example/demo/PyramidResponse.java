package com.example.demo;

import java.util.List;

class PyramidResponse {
    private List<List<Integer>> pyramid;
    private int maxRouteSum;
    private List<Integer> maxPathRoute;

    PyramidResponse(List<List<Integer>> pyramid, int maxRouteSum, List<Integer> maxPathRoute) {
        this.pyramid = pyramid;
        this.maxRouteSum = maxRouteSum;
        this.maxPathRoute = maxPathRoute;
    }

    public List<List<Integer>> getPyramid() {
        return pyramid;
    }

    public void setPyramid(List<List<Integer>> pyramid) {
        this.pyramid = pyramid;
    }

    public int getMaxRouteSum() {
        return maxRouteSum;
    }

    public void setMaxRouteSum(int maxRouteSum) {
        this.maxRouteSum = maxRouteSum;
    }

    public List<Integer> getMaxPathRoute() {
        return maxPathRoute;
    }

    public void setMaxPathRoute(List<Integer> maxPathRoute) {
        this.maxPathRoute = maxPathRoute;
    }
}
