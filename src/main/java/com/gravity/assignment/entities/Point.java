package com.gravity.assignment.entities;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class Point {

  private int index;
  private List<Double> coordinates;
  public static int currentDimension = 0;

  public Point() {
  }

  public Point(int index, List<Double> coordinates) {
    this.index = index;
    this.coordinates = coordinates;
  }

  @Override
  public String toString() {
    String coordinatesToString = StringUtils.EMPTY;
    for (Double c : coordinates) {
      coordinatesToString += c + " ";
    }
    return index + ":" + coordinatesToString;
  }

  public List<Double> getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(List<Double> coordinates) {
    this.coordinates = coordinates;
  }

  public Double getCurrentDimensionValue(int dimension) {
    return coordinates.get(dimension);
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }
}
