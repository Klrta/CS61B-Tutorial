package byog.Core;

import java.io.Serializable;

public class Point implements Serializable {

    private final int x;
    private final int y;

    Point(int xp,int yp){
        this.x = xp;
        this.y = yp;
    }

    public int x(){ return x;}
    public int y(){ return y;}

    public static boolean alignedOnX(Point p1,Point p2){ return p1.y == p2.y; }
    public static boolean alignedOnY(Point p1,Point p2){ return p1.x == p2.x; }

    public boolean equals(Object o){
        if(!(o instanceof Point)){
            return false;
        }
        Point other = (Point) o;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return x + y;
    }

    static boolean equals(Point p1,Point p2){
        return p1.x == p2.x && p1.y == p2.y;
    }
}
