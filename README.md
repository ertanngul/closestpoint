# The closestpoint:
This implementation finds the closest pair between a set of multi-dimensional points.
## Prerequisite Conditions:
java version should be `version`>1.5

This implementation needs maven installation to build & run.  For `maven` installation please refer to https://maven.apache.org/install.html.
## Program Execution:
Download or clone the code and navigate the root directory of the project.

Build the code with:
```
mvn compile
```
Run the code with:
```
mvn exec:java -Dexec.mainClass="com.gravity.assignment.Main" -Dexec.args=“full path of input file”
```
Run the tests with:
```
mvn test
```
The result of the implementation, the closest pair points, is displayed on the console and written in an output file, on the same path with the input file and has `output_` prefix.  
## Algorithm and Complexity
Two types of algorithms are implemented in this project. One of them is the `closest pair algorithm` which is defined in [ClosestPair.pdf](https://www.cs.ucsb.edu/~suri/cs235/ClosestPair.pdf) and [DIVIDE-AND-CONQUER IN MULTIDIMENSIONAL SPACE](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.366.9611&rep=rep1&type=pdf), the other one is the `brute-force` algorithm. The algorithm to be applied is determined according to the dimension of the input points. 

In the implementation, first of all, the points are sorted according to the first dimension. Then, the points are divided towards the median into sections till the number of the points is lower than 4. The closest pair, so minimum distance, is calculated using brute-force among them. It is applied to the left and right side of the median. Then, the points, in the narrow space between (median-d) and (median+d) for the first dimension, are filtered and sorted among the second dimension. Then, the same procedure is applied to the next dimensions recursively till the last dimension. For the  last dimension, the points are sorted and the shortest distance is checked in the delta distance for each point. 

The complexity of the `closest pair algorithm` is
```
O(n(log n)^(d−1))
```
whereas the complexity of `brute-force algorithm` is
```
O(d(n)^(2))
```

If we compare the complexity of the algorithms, it is figured out that for small dimensions, the `closest pair algorithm` shows better performance than the brute-force one. Moreover, the brute-force algorithm is more appropriate for the bigger dimension. Because of that,   `brute-force algorithm` is used for the dimension bigger than 10. 
