package com.gravity.assignment;

import com.gravity.assignment.entities.DistanceCalculationResult;
import com.gravity.assignment.entities.Point;
import com.gravity.assignment.exceptions.MissingInputFileException;
import com.gravity.assignment.services.CalculationService;
import com.gravity.assignment.services.PointService;
import com.gravity.assignment.utils.FileOperationsUtil;
import java.util.List;

public class Main {

  private static int ALGORITHM_THRESHOLD = 10;

  public static void main(String[] args) {
    FileOperationsUtil fileOperationsUtil = new FileOperationsUtil();
    PointService pointService = new PointService();
    CalculationService calculationService = new CalculationService();

    try {
      if (args.length == 0) {
        throw new MissingInputFileException(
            "Input file is not provided. Please provide full path of input file as an argument like:\n mvn exec:java "
                + "-Dexec.mainClass=\"com.gravity.assignment.Main\" -Dexec.args=\"full_path_of_the_input_file\"");
      }

      String inputFilePath = args[0];
      List<Point> pointList = fileOperationsUtil.parseFile(inputFilePath);
      PointService.setDimensionCount(pointList.get(0).getCoordinates().size());
      DistanceCalculationResult d = null;


      if (PointService.getDimensionCount() < ALGORITHM_THRESHOLD) {
        System.out.println("Closest points algorithm is utilizing...");
        d = calculationService.calculateShortestDistance(pointList);
      } else {
        System.out.println("Brute-force algorithm is utilizing...");
        d = calculationService.shortestDistanceFoundBrutally(pointList);
      }

      System.out.println("###############################################################");
      System.out.println("#");
      System.out.println("#");
      System.out.println(d.toString());
      fileOperationsUtil.writeResultToFile(inputFilePath, d);
      System.out.println("#");
      System.out.println("#");
      System.out.println("###############################################################");
    } catch (Exception e) {
      System.err.println("#########################EXCEPTION#############################");
      System.err.println(e.getMessage());
      System.err.println("###############################################################");
    }

  }
}
