package CrimeHotSpotsProject;

import java.util.HashSet;
import java.util.Set;

public class HouseCluster {
    private int id;
    private Set<HousePoint> points;

    // initialize the cluster with an ID
    public HouseCluster(int id) {
        this.id = id;
        this.points = new HashSet<>();
    }

    // getter for the cluster ID
    public int getId() {
        return id;
    }

    // retrieving the set of house points in the cluster
    public Set<HousePoint> getPoints() {
        return points;
    }

    // adding a house point to the cluster
    public void addPoint(HousePoint point) {
        points.add(point);
    }

    // checking if a house point is already in the cluster
    public boolean containsPoint(HousePoint point) {
        return points.contains(point);
    }
}
