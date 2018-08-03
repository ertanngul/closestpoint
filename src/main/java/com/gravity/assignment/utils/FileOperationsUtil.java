package com.gravity.assignment.utils;

import com.gravity.assignment.entities.Point;
import com.gravity.assignment.exceptions.MalStructuredInputFileException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;

public class FileOperationsUtil {

  public List<Point> parseFile(String fileName) throws IOException, MalStructuredInputFileException {
    List<Point> pointList = new ArrayList<>();

    BufferedReader br = new BufferedReader(new FileReader(fileName));
    String sCurrentLine;
    int index = 0;

    while ((sCurrentLine = br.readLine()) != null) {
      List<Double> coordinateList = Arrays.stream(sCurrentLine.split("\\s+")).map(String::trim)
          .mapToDouble(Double::parseDouble).boxed().collect(Collectors.toList());

      if(CollectionUtils.isNotEmpty(pointList) && pointList.get(pointList.size()-1).getCoordinates().size() != coordinateList.size()){
        throw new MalStructuredInputFileException("There is an inconsistency between the dimension of points in the input file. Please restructure your input file. ");
      }

      Point point = new Point();
      point.setIndex(++index);
      point.setCoordinates(coordinateList);
      pointList.add(point);
    }

    return pointList;
  }
}
