package com.gravity.assignment.utils;

import com.gravity.assignment.entities.DistanceCalculationResult;
import com.gravity.assignment.entities.Point;
import com.gravity.assignment.exceptions.MalStructuredInputFileException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class FileOperationsUtil {

  public List<Point> parseFile(String fileName)
      throws IOException, MalStructuredInputFileException {
    List<Point> pointList = new ArrayList();

    BufferedReader br = new BufferedReader(new FileReader(fileName));
    String sCurrentLine;
    int index = 0;

    while ((sCurrentLine = br.readLine()) != null) {
      List<Double> coordinateList = new ArrayList<Double>();
      String[] coordinates = sCurrentLine.split("\\s+");
      for(int i=0;i<coordinates.length;i++){
        coordinateList.add(Double.parseDouble(coordinates[i]));
      }

      if (CollectionUtils.isNotEmpty(pointList)
          && pointList.get(pointList.size() - 1).getCoordinates().size() != coordinateList.size()) {
        throw new MalStructuredInputFileException(
            "There is an inconsistency between the dimension of points in the input file. Please restructure your input file. ");
      }

      Point point = new Point();
      point.setIndex(++index);
      point.setCoordinates(coordinateList);
      pointList.add(point);
    }

    return pointList;
  }

  public void writeResultToFile(String inputFileName,
      DistanceCalculationResult distanceCalculationResult) throws IOException {
    String[] inputFileNameArray = inputFileName.split("/");
    String outputFileName = StringUtils.EMPTY;
    for(int i=0;i<inputFileNameArray.length-1;i++){
      outputFileName += inputFileNameArray[i] + "/";
    }
    outputFileName += "output_" + inputFileNameArray[inputFileNameArray.length-1];

    PrintWriter writer = new PrintWriter(outputFileName, "UTF-8");
    writer.println(distanceCalculationResult.toString());
    writer.close();
  }
}
