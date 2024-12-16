package CrimeHotSpotsProject;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapred.*;

public class CrimeMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, CrimePoint> {

    public void map(LongWritable key, Text value, OutputCollector<Text, CrimePoint> output, Reporter reporter) throws IOException {
        // Convert the line to a string
        String valueString = value.toString();

        // Split the CSV line into fields
        String[] fields = valueString.split(",");

        // Ensure there are enough fields
        if (fields.length >= 1) {
            try {
                // Extract relevant fields
                String area = fields[3].trim(); // Area column index
                String crimeType = fields[6].trim(); // Crime type column index
                double lat = Double.parseDouble(fields[13].trim()); // Latitude column
                double lon = Double.parseDouble(fields[14].trim()); // Longitude column
                String crimeCode = fields[5].trim(); // Crime code

                // Form a composite key: Area + Crime Type
                String compositeKey = area + "|" + crimeType;

                // Emit the composite key with the CrimePoint object
                output.collect(new Text(compositeKey), new CrimePoint(lat, lon, crimeCode));

            } catch (Exception e) {
                // Log and skip any erroneous records
                System.err.println("Error processing record: " + valueString);
            }
        } else {
            // If the row has an invalid number of columns, log and skip it
            System.err.println("Skipping invalid record: " + valueString);
        }
    }
}
