package CrimeHotSpotsProject;
import java.util.Iterator;
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class HouseReducer extends MapReduceBase implements Reducer<Text, HousePoint, Text, HousePoint> {

    // for processing the HousePoint objects and outputting them
    public void reduce(Text key, Iterator<HousePoint> values, OutputCollector<Text, HousePoint> output, Reporter reporter) throws IOException {
        // this loop indiscrimately emits all processed HousePoint objects
        while (values.hasNext()) {
            HousePoint housePoint = values.next();
            // collects the HousePoint objects, associating them with a specific key
            output.collect(key, housePoint);
        }
    }
}
