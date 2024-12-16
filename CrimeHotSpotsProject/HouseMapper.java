package CrimeHotSpotsProject;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class HouseMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, HousePoint> {

    @Override
    public void map(LongWritable key, Text value, OutputCollector<Text, HousePoint> output, Reporter reporter) throws IOException {
        // converting the line into a string
        String valueString = value.toString();

        // splitting CSV data line into fields
        String[] fields = valueString.split(",");

   
        if (fields.length >= 16) {
            try {
                // indexing referenced in last line of jupyter cell for house data prep and clean file
                String medianPrice = fields[16].trim(); 
                double lat = Double.parseDouble(fields[7].trim()); 
                double lon = Double.parseDouble(fields[8].trim()); 
                // creating a HousePoint object with the extracted information
                HousePoint housePoint = new HousePoint(lat, lon, medianPrice);

                // price of median home categorized is the key we wish to return here
                
                // price as key, HousePoint as value
                output.collect(new Text(medianPrice), housePoint);

            } catch (Exception e) {
                // skip any and all erroneous records
                System.err.println("Error processing record: " + valueString);
            }
        } else {
            // if this row has an invalid number of columns, log and skip it
            System.err.println("Skipping invalid record: " + valueString);
        }
    }
}
