import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DBSCANReducer extends Reducer<Text, Text, Text, Text> {

    private static final double EPSILON = 0.01; // adjust as needed
    private static final int MIN_POINTS = 5;   // adjust as needed

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        List<Point> points = new ArrayList<>();
        
        // Collect points for DBSCAN logic
        for (Text val : values) {
            String[] fields = val.toString().split(",");
            // Assume the crime record is in the form "CrimeCode, VictimAge, CrimeDescription, etc."
            // Just keep the lat/lon for now
            double lat = Double.parseDouble(fields[11].trim());
            double lon = Double.parseDouble(fields[12].trim());
            points.add(new Point(lat, lon));
        }

        // Perform DBSCAN clustering logic (a simple example)
        for (Point point : points) {
            List<Point> neighbors = getNeighbors(point, points);
            if (neighbors.size() >= MIN_POINTS) {
                // If enough neighbors, this is a core point (start a new cluster)
                context.write(new Text("Cluster: "), new Text(point.toString() + " -> Core Point"));
            } else {
                // If not enough neighbors, it's noise or a border point
                context.write(new Text("Noise: "), new Text(point.toString() + " -> Noise"));
            }
        }
    }

    // Method to find neighbors within epsilon radius
    private List<Point> getNeighbors(Point point, List<Point> allPoints) {
        List<Point> neighbors = new ArrayList<>();
        for (Point other : allPoints) {
            if (point.distanceTo(other) <= EPSILON) {
                neighbors.add(other);
            }
        }
        return neighbors;
    }

    // Point class to represent (lat, lon)
    private static class Point {
        double lat, lon;

        Point(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        @Override
        public String toString() {
            return lat + "," + lon;
        }

        // Simple distance function for DBSCAN
        public double distanceTo(Point other) {
            double latDiff = this.lat - other.lat;
            double lonDiff = this.lon - other.lon;
            return Math.sqrt(latDiff * latDiff + lonDiff * lonDiff);
        }
    }
}
