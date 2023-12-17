package com.example.demo;

import java.util.List;

class PyramidPathResult {
    private int maxRouteSum;
    private List<Integer> maxPathRoute;

    PyramidPathResult(int maxRouteSum, List<Integer> maxPathRoute) {
        this.maxRouteSum = maxRouteSum;
        this.maxPathRoute = maxPathRoute;
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
