package byog.Core;

import byog.TileEngine.TETile;

public class BentHallway extends Hallway {
    private final Point end1;
    private final Point corner;
    private final Point end2;

    public BentHallway(Point p1,Point p2,Point p3,TETile[][] world,TETile floor,TETile wall){
        if(p1 == null || p2 == null || p3 == null || world == null || floor == null || wall == null){
            throw new IllegalArgumentException("Trying to initialize "
                    + getClass() + " with null argument(s).");
        }
        if(!(Point.alignedOnX(p1,p2)&&(Point.alignedOnY(p2,p3)))
                &&
                !(Point.alignedOnY(p1,p2)&&(Point.alignedOnX(p2,p3)))){
            throw new IllegalArgumentException("Trying to initialize "
                    + getClass() + " with points that don't form two orthogonal segments.");
        }
        this.end1 = p1;
        this.corner = p2;
        this.end2 = p3;
        segments = new Segment[2];
        segments[0] =new Segment(end1,corner);
        segments[1] =new Segment(corner,end2);
        initialize(world,floor,wall);
    }

    @Override
    void initialize(TETile[][] worldP, TETile floorP, TETile wallP) {
        this.world = worldP;
        this.floor = floorP;
        this.wall =wallP;
    }
}
