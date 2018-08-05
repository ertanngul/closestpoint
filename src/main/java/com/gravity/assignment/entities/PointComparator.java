package com.gravity.assignment.entities;

import java.util.Comparator;

public class PointComparator implements Comparator<Point> {

  private int dimension;

  public PointComparator(int dimension) {
    this.dimension = dimension;
  }

  public int compare(Point p1, Point p2) {
    return p1.getCurrentDimensionValue(dimension)
        .compareTo(p2.getCurrentDimensionValue(dimension));
  }
}
