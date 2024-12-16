Housing Dataset (John Vega) https://www.kaggle.com/datasets/abdallahsamman/california-housing-with-name-of-counties (John Vega) 
Dataset that showed median home prices in various counties throughout California. Los Angeles area information was extracted along with latitude and longitude data and all the median pricing was categorized into increments of 100 thousand dollar increments from under 100 thousand to over half a million


John_Vega_Comp535_Housing_Data.ipynb (John Vega)
This was where the data for the housing was cleaned and preprocessed in jupyter. Work done included cutting all data out that wasn’t relevant to los angeles as well as categorizing the median home value. After this was done the data was imported into the project virtual machine to run hadoop. Indices to columns were identified in order to later create the mapper file for the housing price data


LA_Housing_Data_Categorized.csv (John Vega)
Final housing data imported into hadoop for processing. Housing data altered, stored and transferred from the Housing Data ipynb referenced in the cell immediately above (John_Vega_Comp535_Housing_Data.ipynb)
LA Crime Dataset from 2020 to 2024
https://www.kaggle.com/code/ishmaelkiptoo/usa-la-comprehensive-crimes-analysis (John Vega)
Dataset that showed type of crimes committed in Districts in Los Angeles County From 2020 to 2024 showing type of crime, dates, as well as latitude and longitude location.

John_Vega_Comp535_Crime_Data_Preprocessing.ipynb (John Vega)
Crime dataset referenced in cell above was preprocessed by categorizing the date narrowing it down to only the year, as well as trimming unnecessary serializations to files, in addition indices of the columns were identified in order to later create the mapper for the crime data

cleaned_crime.csv (John Vega)
Final crime data imported into hadoop for processing. Crime data altered, stored and transferred from the Crime Data ipynb referenced in the cell immediately above (John_Vega_Comp535_Crime_Data_Preprocessing.ipynb)


CrimePoint.java (John Vega)
Design of an object to hold latitude, longitude, and crime description, all attributes were marked private with public accessors and mutators


Cluster.java (John Vega)
Facilitated creation and identification of clusters for CrimePoints


CrimeMapper.java (John Vega)
Collected the fields of the area, crime type, tattitude, longitude, and crime code. The area and crime type were appended to each other to create a composite key, with the values being the longitude and latitude. The logic here in the larger goal of choropleth was to make the key the identifier on the map and the value to determine the location

CrimeReducer.java (John Vega)
Processes all the relevant crime data entered in sequentially and creates the collection of keys with the crimePoints



CrimeDBSCANDriver.java (John Vega)
Facilitates the job configuration and execution of the crime related mapper and reducer classes described and sets the input and output path argument numbers to be declared from the bash terminal side


CrimeHotSpotsProject.jar(John Vega)
Compiled file to execute the hadoop operation to carry out the DBSCAN clustering algorithm onto the crime dataset and store the result in the corresponding part-0000 file


HousePoint.java (John Vega)
Design of an object to hold latitude, longitude, and categorized housing price, all attributes were marked private with public accessors and mutators


HouseCluster.java (John Vega)
Facilitated creation and identification of clusters for HousePoints


HouseMapper.java (John Vega)
Collected the fields of the median price categorized, tattitude, and longitude. The median price categorized served as the key, with the values being the longitude and latitude. Once again, the logic here in the larger goal of choropleth was to make the key the identifier on the map and the value to determine the location


HouseReducer.java (John Vega)
Processes all the relevant median house price data entered in sequentially and creates the collection of keys with the HousePoints


HouseDBSCANDriver.java (John Vega)
Facilitates the job configuration and execution of the median house price related mapper and reducer classes described and sets the input and output path argument numbers to be declared from the bash terminal side


HouseCrimeHotSpotsProject.jar (John Vega)
Compiled file to execute the hadoop operation to carry out the DBSCAN clustering algorithm onto the median housing price dataset and store the result in the corresponding part-0000 file


John_Vega_Output.txt (John Vega) 
Shows all of the entire execution of hadoop map-reduce on both the crime data as well as the median home price data localized in the same geographic location to establish and display all of the data necessary to display the clusters on a choropleth map.





COMMANDS IN ORDER:
start-all.sh
//starts the hadoop file system program initialization sequence

hadoop fs -put cleaned_crime.csv /input
// loads the data into the hadoop file system in order to prepare for mapreduce execution


javac -classpath $(hadoop classpath) -d classes/ CrimeMapper.java CrimeReducer.java CrimeDBSCANDriver.java CrimePoint.java Cluster.java
// compiles the classes into the hadoop classpath as the necessary initialization step and stores in a classes directory

jar -cvf CrimeHotSpotsProject.jar -C classes/ .     
// creates a jar file in order to prepare to execute the map reducer program

hadoop jar CrimeHotSpotsProject.jar CrimeHotSpotsProject.CrimeDBSCANDriver /input/cleaned_crime.csv /output/clustered_crime_results_13
// executes the program takin the established input from the /input directory and stores in the output subdirectory specified

hdfs dfs -cat /output/clustered_crime_results_13/part-00000   
// this command allows us to view the results of the hadoop map reduction based clustering executedhdf
