package com.gravity.assignment.entities;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class Point {

  private List<Double> coordinates;
  private int index;
  public static int currentDimension = 0;

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
