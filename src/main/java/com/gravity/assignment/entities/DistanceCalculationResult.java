package com.gravity.assignment.entities;

public class DistanceCalculationResult {

  private double distance;
  private Point firstPoint;
  private Point secondPoint;

  public double getDistance() {
    return distance;
  }

  @Override
  public String toString() {
    return firstPoint.getIndex() < secondPoint.getIndex() ? (firstPoint + "\n" + secondPoint)
        : (secondPoint + "\n" + firstPoint);
  }

  public void setDistance(double distance) {
    this.distance = distance;
  }

  public Point getFirstPoint() {
    return firstPoint;
  }

  public void setFirstPoint(Point firstPoint) {
    this.firstPoint = firstPoint;
  }

  public Point getSecondPoint() {
    return secondPoint;
  }

  public void setSecondPoint(Point secondPoint) {
    this.secondPoint = secondPoint;
  }


}
