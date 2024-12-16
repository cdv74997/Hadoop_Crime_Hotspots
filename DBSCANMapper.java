import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class DBSCANMapper extends Mapper<Object, Text, Text, Text> {

    private static final String COMMA_DELIMITER = ",";

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        // Skip header or invalid lines
        if (line.contains("LAT,LON")) return;
        
        String[] fields = line.split(COMMA_DELIMITER);
        
        // Assuming LAT is at index 11 and LON at index 12 (adjust accordingly)
        String lat = fields[11].trim();
        String lon = fields[12].trim();
        
        // Emit key as (latitude, longitude) and value as (crime record details)
        context.write(new Text(lat + "," + lon), value);
    }
}
