The primary objectives of the project were:

Cluster crime data based on geographic proximity to identify regions with a high density of crimes.
Identify isolated crime events (outliers) that do not fit into any clusters.
Efficiently process large-scale crime data using Hadoop MapReduce.
Files
1. Cluster.class
Contains logic for managing and representing crime clusters. It stores cluster data and methods for handling cluster operations.
2. DBSCANDriver.class
The driver class that sets up and runs the DBSCAN MapReduce job. It configures the job, links the Mapper and Reducer, and starts the clustering process.
3. DBSCANJob.jar
A compiled JAR file containing the MapReduce job for DBSCAN. It includes all the necessary classes for running the clustering algorithm on Hadoop.
4. DBSCANReducer.class
The reducer class that processes the results from the Mapper, combining data from different map tasks and performing the final steps of DBSCAN to form clusters and identify noise points.
5. results.txt
A text file that contains the output of the DBSCAN algorithm. It includes the clustered crime data and identified noise points.
6. DBSCANDriver.java
The source code for the DBSCANDriver class. This file sets up and configures the MapReduce job and starts the DBSCAN algorithm.
7. DBSCANMapper.class
The Mapper class that processes crime records, calculates the geographic proximity of crimes, and prepares the data for the DBSCAN algorithm.
8. DBSCANReducer.java
The source code for the DBSCANReducer class. It contains logic for clustering the data and identifying noise points after receiving the intermediate output from the Mapper.
9. DBSCAN.class
The core DBSCAN algorithm implemented in this class. It includes methods for identifying core points, expanding clusters, and detecting noise.
10. dbscan-job.jar
Another JAR file similar to DBSCANJob.jar, containing the MapReduce job for DBSCAN, including the core logic for clustering.
11. DBSCANMapper.java
The source code for the DBSCANMapper class. It processes crime records to determine the geographic neighborhoods for the DBSCAN clustering process.
12. Point.class
Defines a class representing a point (crime location) with latitude and longitude. This class is used throughout the MapReduce pipeline to manage individual crime records.
How to Run
Set up Hadoop: Make sure you have Hadoop installed and configured on your system or cluster. You will need access to Hadoop's HDFS for storing and reading input/output files.

Compile the Java files:

javac *.java
Create the JAR file: After compiling the source files, create a JAR file:

jar cf dbscan-job.jar *.class
Upload your crime data to HDFS: Upload your crime dataset (CSV file) to Hadoop's HDFS. This dataset should include latitude and longitude for each crime record.

hdfs dfs -put crime_data.csv /input
Run the DBSCAN MapReduce job: Execute the DBSCAN algorithm using the Hadoop MapReduce framework:

hadoop jar dbscan-job.jar DBSCANDriver /input/crime_data.csv /output
Check the results: After the job completes, you can view the output in the results.txt file.

hdfs dfs -cat /output/part-*
Expected Output
The expected output includes:

Crime Clusters: Groups of crime locations that are geographically close, identified as clusters by DBSCAN.
Noise Points: Isolated crime records that don't fit into any clusters, representing noise.
Clustered Data: A structured output with the assigned cluster IDs or noise labels for each crime record.
