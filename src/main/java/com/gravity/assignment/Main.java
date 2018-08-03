package com.gravity.assignment;

import com.gravity.assignment.entities.DistanceCalculationResult;
import com.gravity.assignment.entities.Point;
import com.gravity.assignment.exceptions.MalStructuredInputFileException;
import com.gravity.assignment.exceptions.MissingInputFileException;
import com.gravity.assignment.services.CalculationService;
import com.gravity.assignment.services.PointService;
import com.gravity.assignment.utils.FileOperationsUtil;
import java.io.IOException;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    FileOperationsUtil fileOperationsUtil = new FileOperationsUtil();
    PointService pointService = new PointService();
    CalculationService calculationService = new CalculationService();

    try {
      if (args.length == 0) {
        throw new MissingInputFileException(
            "Input file is not provided. Please provide full path of input file as an argument like:\n mvn exec:java "
                + "-Dexec.mainClass=\"com.gravity.assignment.Main\" -Dexec.args=\"full/path/of/input/file\"");
      }

      List<Point> pointList = fileOperationsUtil.parseFile(args[0]);
      PointService.setDimensionCount(pointList.get(0).getCoordinates().size());
      DistanceCalculationResult d = null;

      if (PointService.getDimensionCount() < 20) {
        d = calculationService.shortestDistanceFoundBrutally(pointList);
      } else {
        d = calculationService.shortestDistanceFoundBrutally(pointList);
      }

      System.out.println("###############################################################");
      System.out.println("#");
      System.out.println("#");
      System.out.println(d.toString());
      System.out.println("#");
      System.out.println("#");
      System.out.println("###############################################################");
    } catch (IOException | MissingInputFileException | MalStructuredInputFileException e) {
      System.err.println("#########################EXCEPTION#############################");
      System.err.println(e.getMessage());
      System.err.println("###############################################################");
    }

  }
}
