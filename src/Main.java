import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Kmeans kmeans = new Kmeans(9);
        kmeans.ReadData(Path.of("data/dataset.csv"));
        kmeans.clustering();
    }
}