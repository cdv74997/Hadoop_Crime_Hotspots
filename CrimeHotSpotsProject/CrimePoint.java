package CrimeHotSpotsProject;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;

public class CrimePoint implements Writable {
    private double lat;
    private double lon;
    private String crimeCode;

    public CrimePoint() {
        // Default constructor
    }

    public CrimePoint(double lat, double lon, String crimeCode) {
        this.lat = lat;
        this.lon = lon;
        this.crimeCode = crimeCode;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getCrimeCode() {
        return crimeCode;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeDouble(lat);
        out.writeDouble(lon);
        out.writeUTF(crimeCode);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        lat = in.readDouble();
        lon = in.readDouble();
        crimeCode = in.readUTF();
    }

    @Override
    public String toString() {
        return lat + "," + lon + "," + crimeCode;
    }
}
