package com.gravity.assignment.services;

import com.gravity.assignment.entities.DistanceCalculationResult;
import com.gravity.assignment.entities.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;

public class PointService {

  public static Map<Integer, List<Point>> sortedPointsForDimensions;
  private static int dimensionCount;

  private List<Point> sort(List<Point> points, int dimension) {
    List<Point> sortedPoints = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(points) && !(dimension < 0 && points.size() <= dimension)) {
      Collections.sort(points, (Point p1, Point p2) -> p1.getCurrentDimensionValue(dimension)
          .compareTo(p2.getCurrentDimensionValue(dimension)));
      sortedPoints.addAll(points);
    }
    return sortedPoints;
  }

  public List<Point> filter(List<Point> points, double median,
      DistanceCalculationResult shortestDistanceResult, int dimension) {
    List<Point> pointList = new ArrayList<>();
    List<Point> sortedPointList = sortedPointsForDimensions.get(dimension + 1);
    if (CollectionUtils.isNotEmpty(points)) {
      if (dimension < PointService.getDimensionCount() - 1) {
        double minIntervalValue = median - shortestDistanceResult.getDistance();
        double maxIntervalValue = median + shortestDistanceResult.getDistance();
        for (Point p : sortedPointList) {
          if (points.contains(p) && (p.getCurrentDimensionValue(dimension) > minIntervalValue) && (
              p.getCurrentDimensionValue(dimension) < maxIntervalValue)) {
            pointList.add(p);
          }
        }
      }
    }
    return pointList;
  }

//  private List<Point> filter(List<Point> points, double median,
//      DistanceCalculationResult shortestDistanceResult, int dimension) {
//    List<Point> pointList = new ArrayList<>();
//    if (CollectionUtils.isNotEmpty(points)) {
//      double minIntervalValue = median - shortestDistanceResult.getDistance();
//      double maxIntervalValue = median + shortestDistanceResult.getDistance();
//      for (Point p : points) {
//        if (p.getCurrentDimensionValue(dimension) > minIntervalValue &&
//            p.getCurrentDimensionValue(dimension) < maxIntervalValue) {
//          pointList.add(p);
//        }
//      }
//    }
//    return pointList;
//  }

  public Map<Integer, List<Point>> buildSortedPointsForDimensions(List<Point> points) {
    Map<Integer, List<Point>> sortedPointsForDimensions = new HashMap<>();
    if (CollectionUtils.isNotEmpty(points)) {
      for (int i = 0; i < getDimensionCount(); i++) {
        List<Point> sortedPoints = sort(points, i);
        sortedPointsForDimensions.put(i, sortedPoints);
      }
    }
    return sortedPointsForDimensions;
  }

//  public Map<Integer, List<Point>> filterPointsForDimensions(Map<Integer, List<Point>> pointsMap,
//      double median,
//      DistanceCalculationResult shortestDistanceResult, int dimension) {
//    Map<Integer, List<Point>> sortedPointsForDimensions = new HashMap<>();
//    if (CollectionUtils.isNotEmpty(pointsMap.keySet())) {
//      for (int i = dimension+1; i < getDimensionCount(); i++) {
//        sortedPointsForDimensions.put(i,filter(pointsMap.get(i),median,shortestDistanceResult,dimension));
//      }
//    }
//    System.out.println("sortedPointsForDimensions.size()->" + sortedPointsForDimensions.size() +", dimension->"+ dimension);
//    return sortedPointsForDimensions;
//  }

  public static int getDimensionCount() {
    return dimensionCount;
  }

  public static void setDimensionCount(int dimensionCount) {
    PointService.dimensionCount = dimensionCount;
  }
}
