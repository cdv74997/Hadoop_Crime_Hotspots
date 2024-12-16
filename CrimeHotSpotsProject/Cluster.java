package CrimeHotSpotsProject;

import java.util.HashSet;
import java.util.Set;

public class Cluster {
    private int id;
    private Set<CrimePoint> points;

    public Cluster(int id) {
        this.id = id;
        this.points = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public Set<CrimePoint> getPoints() {
        return points;
    }

    public void addPoint(CrimePoint point) {
        points.add(point);
    }

    public boolean containsPoint(CrimePoint point) {
        return points.contains(point);
    }
}
