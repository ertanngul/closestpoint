package com.gravity.assignment.services;

import com.gravity.assignment.entities.DistanceCalculationResult;
import com.gravity.assignment.entities.Point;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

public class CalculationService {

  private PointService pointService;

  public CalculationService() {
    pointService = new PointService();
  }

  public DistanceCalculationResult calculateShortestDistance(List<Point> points) {
    if(CollectionUtils.isEmpty(points)){
      return new DistanceCalculationResult();
    }

    PointService.setDimensionCount(points.get(0).getCoordinates().size());
    return calculateDistance(pointService.sort(points, 0), null, 0);
  }

  public DistanceCalculationResult calculateDistance(List<Point> points,
      DistanceCalculationResult shortestDeltaResult, int currentDimension) {
    if (CollectionUtils.isEmpty(points)) {
      return shortestDeltaResult;
    }

    if (currentDimension == (PointService.getDimensionCount() - 1)) {
      return calculateShortestDistanceForLastDimension(points, shortestDeltaResult);
    }

    if (points.size() < 4) {
      DistanceCalculationResult calculatedResult = shortestDistanceFoundBrutally(points);
      return (calculatedResult != null && (shortestDeltaResult == null
          || calculatedResult.getDistance() < shortestDeltaResult.getDistance())) ? calculatedResult
          : shortestDeltaResult;
    }

    DistanceCalculationResult d1 = calculateDistance(
        points.subList(0, (points.size() / 2)), shortestDeltaResult, currentDimension);
    DistanceCalculationResult d2 = calculateDistance(
        points.subList(points.size() / 2, points.size()), shortestDeltaResult, currentDimension);
    DistanceCalculationResult tempShortestDistanceResult =
        d1.getDistance() < d2.getDistance() ? d1 : d2;

    if (shortestDeltaResult == null
        || tempShortestDistanceResult.getDistance() < shortestDeltaResult.getDistance()) {
      shortestDeltaResult = tempShortestDistanceResult;
    }

    List<Point> newPoints = pointService
        .filter(points, getMedian(points, currentDimension), shortestDeltaResult, currentDimension);

    return calculateDistance(newPoints, shortestDeltaResult, currentDimension + 1);
  }


  public DistanceCalculationResult calculateShortestDistanceForLastDimension(List<Point> points,
      DistanceCalculationResult shortestDeltaResult) {
    int dimension = PointService.getDimensionCount() - 1;
    if (CollectionUtils.isNotEmpty(points) || points.size() > 1) {

      if (shortestDeltaResult == null) {
        shortestDeltaResult = calculateDistanceForTwoPoints(points.get(0), points.get(1));
      }

      for (int i = 0; i < points.size(); i++) {
        for (int j = i + 1; j < points.size() && points.get(j).getCurrentDimensionValue(dimension)
            < points.get(i).getCurrentDimensionValue(dimension) + shortestDeltaResult.getDistance();
            j++) {
          DistanceCalculationResult distanceCalculationResult = calculateDistanceForTwoPoints(
              points.get(i), points.get(j));
          if (distanceCalculationResult.getDistance() < shortestDeltaResult.getDistance()) {
            shortestDeltaResult = distanceCalculationResult;
          }
        }
      }
    }
    return shortestDeltaResult;
  }

  public DistanceCalculationResult calculateDistanceForTwoPoints(Point p1, Point p2) {
    double totalSquareDistance = 0.0;
    for (int i = 0; i < p1.getCoordinates().size(); i++) {
      totalSquareDistance += Math.pow(p1.getCoordinates().get(i) - p2.getCoordinates().get(i), 2);
    }
    DistanceCalculationResult result = new DistanceCalculationResult();
    result.setDistance(Math.sqrt(totalSquareDistance));
    result.setFirstPoint(p1);
    result.setSecondPoint(p2);
    return result;
  }

  public DistanceCalculationResult shortestDistanceFoundBrutally(List<Point> points) {
    DistanceCalculationResult shortestDistanceResult = null;
    for (int i = 0; i < points.size(); i++) {
      for (int j = i + 1; j < points.size(); j++) {
        DistanceCalculationResult result = calculateDistanceForTwoPoints(points.get(i),
            points.get(j));
        shortestDistanceResult =
            (shortestDistanceResult == null || result.getDistance() < shortestDistanceResult
                .getDistance()) ? result : shortestDistanceResult;
      }
    }

    return shortestDistanceResult;
  }

  private double getMedian(List<Point> points, int dimension) {
    double median = 0.0;
    if (points.size() % 2 == 0) {
      median = (points.get(points.size() / 2).getCurrentDimensionValue(dimension) + points
          .get(points.size() / 2 - 1).getCurrentDimensionValue(dimension)) / 2.0;
    } else {
      median = points.get(points.size() / 2).getCurrentDimensionValue(dimension);
    }
    return median;
  }

}



