package byog.Core;

import byog.TileEngine.TETile;

import java.util.ArrayList;
import java.util.List;

public abstract class Hallway {
    /*
    * 一组垂直或平行的瓦片组合
    * */
    class Segment{
        private final Point p1;//头部
        private final Point p2;//尾部
        private final Point[] points;//瓦片组合

        Segment(Point pt1,Point pt2){
            if(Point.alignedOnY(pt1,pt2)){
                if(pt1.y() < pt2.y()){
                    this.p1 = pt1;
                    this.p2 = pt2;
                }else{
                    this.p1 = pt2;
                    this.p2 = pt1;
                }
                int numPoints = this.p2.x() - this.p1.x() + 1;
                points = new Point[numPoints];
                for(int i = 0; i < numPoints; i++){
                    points[i] = new Point(this.p1.x(),this.p1.y() + i);
                }
            }else if(Point.alignedOnX(pt1,pt2)){
                if(pt1.x() < pt2.x()){
                    this.p1 = pt1;
                    this.p2 = pt2;
                }else{
                    this.p1 = pt2;
                    this.p2 = pt1;
                }
                int numPoints = this.p2.y() - this.p1.y() + 1;
                points = new Point[numPoints];
                for(int i = 0; i < numPoints; i++){
                    points[i] = new Point(this.p1.x()+ i,this.p1.y());
                }
            }else{
                throw new IllegalArgumentException("Trying to initialize "
                        + getClass() + " with points that are not aligned on x nor y.");
            }
        }
    }

    protected Segment[] segments;
    protected TETile[][] world;
    protected TETile wall;
    protected TETile floor;

    abstract void initialize(TETile[][] worldP,TETile floorP,TETile wallP);

    public boolean isStraight(){ return segments.length == 1; }

    boolean contains(Point ps){
        for(Segment segment:segments){
            for(Point p:segment.points){
                if(ps.equals(p)){
                    return true;
                }
            }
        }
        return false;
    }

    public List<Point> getPoints(){
        List<Point> points = new ArrayList<>();
        for(Segment segment:segments){
            for(Point p:segment.points){
                points.add(p);
            }
        }
        return points;
    }

    public void draw(){
        for(Segment segment:segments){
            for(Point p:segment.points){
                world[p.x()][p.y()] = floor;
            }
        }
    }
}
