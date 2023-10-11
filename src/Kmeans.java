import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;

public class Kmeans {
    HashMap<Point, List<Point>> clusters = new HashMap<Point, List<Point>>();
    ArrayList<Point> dataPoints = new ArrayList<Point>();
    List<Point> oldCenters = new ArrayList<>();
    private int M;

    public Kmeans(int m) {
        this.M = m;
    }

    public void ReadData(Path filename){
        try {
            File file = new File(String.valueOf(filename));
            Scanner data = new Scanner(file);
            while (data.hasNextLine()) {
                String[] point = data.nextLine().split(",");
                Float x1 = Float.valueOf(point[0]);
                Float x2 = Float.valueOf(point[1]);
                dataPoints.add(new Point(x1, x2));
            }
            data.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void selectRandomCenters(){
        for(int i=0; i<M; i++){
            Random random = new Random();
            // creating object
            Point center = dataPoints.get(random.nextInt(dataPoints.size()));
            clusters.put(center, new ArrayList<Point>());
        }
    }

    public void clustering(){
        selectRandomCenters();
        boolean t = true;
        int counter = 0;
        float minVariance = Float.POSITIVE_INFINITY;
        int iteration = 0;
        while(t) {
            for (Point point : dataPoints) {
                float minDistance = Float.POSITIVE_INFINITY;
                Point minCenter = null;
                for (Point center : clusters.keySet()) {
                    float distance = EuclideanDistance(point, center);
                    if (distance < minDistance) {
                        minDistance = distance;
                        minCenter = center;
                    }
                }
                List<Point> cluster = clusters.get(minCenter);
                cluster.add(point);
                clusters.put(minCenter, cluster);
            }
            newCenters();
            counter += 1;
            float variance = calculateVariance();
            System.out.println("Iteration " + counter + "\nTotal Variance: " + variance);
            if(variance < minVariance){
                minVariance = variance;
                iteration = counter-1;
            }
            t = checkTermination(oldCenters, clusters.keySet());
        }
        System.out.println("Iteration: " + iteration + " Minimum variance: " + minVariance);
    }

    public float EuclideanDistance(Point point, Point center){
        float distance = (float) Math.sqrt(Math.pow((point.getX1() - center.getX1()), 2)
         + Math.pow((point.getX2() - center.getX2()), 2));
        return distance;
    }

    public void newCenters(){
        HashMap<Point, List<Point>> tempClusters = new HashMap<Point, List<Point>>();
        oldCenters = new ArrayList<>();
        for(Point center: clusters.keySet()){
            Point newCenter = meanOfCluster(center);
            List<Point> cluster = clusters.get(center);
            oldCenters.add(center);
            //clusters.remove(center);
            //clusters.put(newCenter, cluster);
            tempClusters.put(newCenter, cluster);
        }
        clusters = tempClusters;
    }

    public Point meanOfCluster(Point center){
        float x1 = 0;
        float x2 = 0;
        List<Point> cluster = clusters.get(center);
        for(Point point: cluster){
            x1 += point.getX1();
            x2 += point.getX2();
        }
        float averageX1 = x1/cluster.size();
        float averageX2 = x2/cluster.size();
        return new Point(averageX1, averageX2);
    }

    public float calculateVariance(){
        float totalVariance = 0;
        for(Point center: clusters.keySet()){
            for(Point point: clusters.get(center)){
                totalVariance += EuclideanDistance(point, center);
            }
        }
        return totalVariance;
    }

    public boolean checkTermination(List<Point> oldCenters, Set<Point> newCenters){
        if(newCenters.containsAll(oldCenters)){
            return false;
        }
        return true;
    }
}
