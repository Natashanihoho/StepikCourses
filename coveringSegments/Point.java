package coveringSegments;

public class Point implements Comparable{
    private int segmentNumber;
    private int x;
    private String edge;

    public Point(int x, String edge, int segmentNumber) {
        this.segmentNumber = segmentNumber;
        this.edge = edge;
        this.x = x;
    }

    public int getSegmentNumber() {
        return segmentNumber;
    }

    public String getEdge() {
        return edge;
    }

    public int getX() {
        return x;
    }

    @Override
    public String toString() {
        return "Point: " + x + " Edge: " + edge + " Segment: " + segmentNumber;
    }

    @Override
    public int compareTo(Object o) {
        if(this.x > ((Point) o).x) return 1;
        else if(this.x < ((Point) o).x) return -1;
        else {
            return "L".equals(this.edge)? -1: 1;
        }
    }
}
