# K-Means Clustering Algorithm

This project implements the K-Means clustering algorithm in **Java**. The program loads a file containing examples, executes the K-Means algorithm with M centroids, and finally saves the coordinates of the cluster centers. The initial position of each centroid is randomly selected from the examples.

## Usage

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/your-repo.git

2. **Navigate to the project directory:**

   ```bash
   cd your-repo
   ```

3. **Run the program:**

   ```bash
   python k_means_algorithm.py data_file.txt M
   ```
  data_file.txt: File containing examples.
  M: Number of centroids.

  View the results:

    The program will output the coordinates of the cluster centers and calculate and print the clustering error.

#### Example Data

The input data file (data_file.txt) should contain examples in a specific format.

#### Implementation Details

The initial position of each centroid is randomly selected from the examples.

The clustering error is calculated using the Euclidean distance:

∣∣xi−μk∣∣2∣∣xi​−μk​∣∣2


Feel free to contribute and report issues!
