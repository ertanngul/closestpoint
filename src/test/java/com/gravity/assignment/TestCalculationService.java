package com.gravity.assignment;

import com.gravity.assignment.entities.DistanceCalculationResult;
import com.gravity.assignment.entities.Point;
import com.gravity.assignment.services.CalculationService;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

public class TestCalculationService {

  @Test
  public void testCalculateShortestDistanceWithEmptyArray() {
    CalculationService calculationService = new CalculationService();
    DistanceCalculationResult result = calculationService
        .calculateShortestDistance(new ArrayList<Point>());

    assertNotNull(result);
  }

  @Test
  public void testCalculateShortestDistanceWithOneDimension() {
    CalculationService calculationService = new CalculationService();

    Point p1 = new Point(1, Arrays.asList(-3.0));
    Point p2 = new Point(2, Arrays.asList(-11.0));
    Point p3 = new Point(3, Arrays.asList(3.0));
    Point p4 = new Point(4, Arrays.asList(-1.0));
    Point p5 = new Point(5, Arrays.asList(4.0));

    DistanceCalculationResult result = calculationService
        .calculateShortestDistance(Arrays.asList(p1, p2, p3, p4, p5));

    DistanceCalculationResult expectedResult = new DistanceCalculationResult();
    expectedResult.setFirstPoint(p3);
    expectedResult.setSecondPoint(p5);

    assertTrue(result.equals(expectedResult));
  }

  @Test
  public void testCalculateShortestDistanceWithTwoPoints() {
    CalculationService calculationService = new CalculationService();

    Point p1 = new Point(1, Arrays.asList(-262972.0, 508697.0));
    Point p2 = new Point(2, Arrays.asList(-311943.65362731507, 370239.3559213022));
    Point p3 = new Point(3, Arrays.asList(742431.0, -772652.0));
    Point p4 = new Point(4, Arrays.asList(-346046.0, 696615.3537438104));
    Point p5 = new Point(5, Arrays.asList(194172.0, 103527.0));
    Point p6 = new Point(6, Arrays.asList(726621.8167057682, -813087.8844925504));
    Point p7 = new Point(7, Arrays.asList(167923.0, -312455.0459619701));
    Point p8 = new Point(8, Arrays.asList(499664.42762545496, 72395.09685360803));

    DistanceCalculationResult result = calculationService
        .calculateShortestDistance(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8));

    DistanceCalculationResult expectedResult = new DistanceCalculationResult();
    expectedResult.setFirstPoint(p3);
    expectedResult.setSecondPoint(p6);

    assertTrue(result.equals(expectedResult));
  }

  /*
  * Three dimension is the smallest value to test recursive func.
  * */
  @Test
  public void testCalculateShortestDistanceWithThreeDimensions() {
    CalculationService calculationService = new CalculationService();

    Point p1 = new Point(1,
        Arrays.asList(-22099.96174623957, -818114.8779741323, -657943.7554244546));
    Point p2 = new Point(2, Arrays.asList(-699568.714534241, 528996.0, -575465.0));
    Point p3 = new Point(3, Arrays.asList(293052.0, -619261.0, -575381.7317664991));
    Point p4 = new Point(4, Arrays.asList(-203645.0, -120595.16591061011, -950894.9377663811));
    Point p5 = new Point(5, Arrays.asList(-158915.0, 795241.0, -526051.0));
    Point p6 = new Point(6, Arrays.asList(915309.0, -608909.0, 598038.7383574506));
    Point p7 = new Point(7, Arrays.asList(-815017.0, -541287.6770602928, -57344.0));
    Point p8 = new Point(8, Arrays.asList(-433974.0, -201238.24314075336, 142670.0));
    Point p9 = new Point(9, Arrays.asList(-699569.8, 528997.7, -575466.0));
    Point p10 = new Point(10, Arrays.asList(92623.34709925135, -994060.0, -387206.87213890103));

    DistanceCalculationResult result = calculationService
        .calculateShortestDistance(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));

    DistanceCalculationResult expectedResult = new DistanceCalculationResult();
    expectedResult.setFirstPoint(p2);
    expectedResult.setSecondPoint(p9);

    assertTrue(result.equals(expectedResult));

  }

  @Test
  public void testCalculateShortestDistanceForBruteForce() {
    CalculationService calculationService = new CalculationService();

    Point p1 = new Point(1, Arrays.asList(-262972.0, 508697.0));
    Point p2 = new Point(2, Arrays.asList(-311943.65362731507, 370239.3559213022));
    Point p3 = new Point(3, Arrays.asList(742431.0, -772652.0));
    Point p4 = new Point(4, Arrays.asList(-346046.0, 696615.3537438104));
    Point p5 = new Point(5, Arrays.asList(194172.0, 103527.0));
    Point p6 = new Point(6, Arrays.asList(726621.8167057682, -813087.8844925504));
    Point p7 = new Point(7, Arrays.asList(167923.0, -312455.0459619701));
    Point p8 = new Point(8, Arrays.asList(499664.42762545496, 72395.09685360803));

    DistanceCalculationResult result = calculationService
        .shortestDistanceFoundBrutally(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8));

    DistanceCalculationResult expectedResult = new DistanceCalculationResult();
    expectedResult.setFirstPoint(p3);
    expectedResult.setSecondPoint(p6);

    assertTrue(result.equals(expectedResult));

  }

}
