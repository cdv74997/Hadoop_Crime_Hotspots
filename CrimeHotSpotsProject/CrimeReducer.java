package CrimeHotSpotsProject;
import java.util.Iterator;
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class CrimeReducer extends MapReduceBase implements Reducer<Text, CrimePoint, Text, CrimePoint> {

    public void reduce(Text key, Iterator<CrimePoint> values, OutputCollector<Text, CrimePoint> output, Reporter reporter) throws IOException {
        // this file indiscriminately emits all CrimePoint objects
        while (values.hasNext()) {
            CrimePoint crimePoint = values.next();
            output.collect(key, crimePoint);
        }
    }
}
