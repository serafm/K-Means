public class Point {
    private float x1;
    private float x2;

    public Point(float x1, float x2) {
        this.x1 = x1;
        this.x2 = x2;
    }

    public float getX1() {
        return x1;
    }

    public void setX1(float x1) {
        this.x1 = x1;
    }

    public float getX2() {
        return x2;
    }

    public void setX2(float x2) {
        this.x2 = x2;
    }

    public boolean equals(Point p2){
        if(this.getX1() != p2.getX1() || this.getX2() != p2.getX2()){
            return false;
        }
        return true;
    }
}
