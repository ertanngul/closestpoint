package com.gravity.assignment.services;

import com.gravity.assignment.entities.DistanceCalculationResult;
import com.gravity.assignment.entities.Point;
import com.gravity.assignment.entities.PointComparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;

public class PointService {

  private static int dimensionCount;

  public List<Point> sort(List<Point> points, int dimension) {
    List<Point> sortedPoints = new ArrayList();
    if (CollectionUtils.isNotEmpty(points) && !(dimension < 0 && points.size() <= dimension)) {
      Collections.sort(points, new PointComparator(dimension));
      sortedPoints.addAll(points);
    }
    return sortedPoints;
  }

  public List<Point> filter(List<Point> points, double median,
      DistanceCalculationResult shortestDistanceResult, int dimension) {
    List<Point> pointList = new ArrayList();
    if (CollectionUtils.isNotEmpty(points)) {
      double minIntervalValue = median - shortestDistanceResult.getDistance();
      double maxIntervalValue = median + shortestDistanceResult.getDistance();
      for (Point p : points) {
        if ((p.getCurrentDimensionValue(dimension) > minIntervalValue) && (
            p.getCurrentDimensionValue(dimension) < maxIntervalValue)) {
          pointList.add(p);
        }
      }
    }
    return sort(pointList, dimension + 1);
  }

  public static int getDimensionCount() {
    return dimensionCount;
  }

  public static void setDimensionCount(int dimensionCount) {
    PointService.dimensionCount = dimensionCount;
  }
}
