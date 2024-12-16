package CrimeHotSpotsProject;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;

public class HousePoint implements Writable {
    private double lat;
    private double lon;
    private String housePriceCategory;

    public HousePoint() {
        // Default constructor
    }

    public HousePoint(double lat, double lon, String housePriceCategory) {
        this.lat = lat;
        this.lon = lon;
        // the point is marked by the housing price categorized
        this.housePriceCategory = housePriceCategory;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getHousePriceCategory() {
        return housePriceCategory;
    }
    // writes the data with utf to standardize the string writing
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeDouble(lat);
        out.writeDouble(lon);
        out.writeUTF(housePriceCategory);
    }
   // reads the data with utf for standardization best practices
    @Override
    public void readFields(DataInput in) throws IOException {
        lat = in.readDouble();
        lon = in.readDouble();
        housePriceCategory = in.readUTF();
    }

    @Override
    public String toString() {
        return lat + "," + lon + "," + housePriceCategory;
    }
}
