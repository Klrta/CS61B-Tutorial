package byog.Core;

import byog.TileEngine.TETile;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final TETile floor;
    private final TETile wall;
    private final TETile[][] world;

    public Room(Point p,int widthP,int heightP,TETile floorP,TETile wallP,TETile[][] worldP){
        this(p.x(),p.y(),widthP,heightP,floorP,wallP,worldP);
    }

    public Room(int xp,int yp,int widthP,int heightP,TETile floorP,TETile wallP,TETile[][] worldP){
        this.x = xp;
        this.y = yp;
        this.width = widthP;
        this.height = heightP;
        this.floor = floorP;
        this.wall =wallP;
        this.world = worldP;

        if(x < 0){
            throw new RuntimeException("Room out of bounds, x:"+x+".");
        }
        if(y < 0){
            throw new RuntimeException("Room out of bounds, x:"+y+".");
        }
        if(world.length < x + width){
            throw new RuntimeException("Room out of bounds, x:"+x+",width:"+width+".");
        }
        if(world[0].length < y + height){
            throw new RuntimeException("Room out of bounds, y: " + y + ", height: " + height + ".");
        }
    }

    public int x(){ return x; }
    public int y(){ return y; }
    public int width(){ return width; }
    public int height(){ return height; }
    public TETile floor(){ return floor; }
    public TETile wall(){ return wall; }
    public TETile[][] world(){ return world; }

    static boolean overlapOnX(Room r1, Room r2){
        return (r1.x <= r2.x && r2.x <= r1.x + r1.width - 1) || (r2.x <= r1.x && r1.x <= r2.x + r2.width - 1);
    }

    static boolean overlapOnY(Room r1, Room r2){
        return (r1.y <= r2.y && r2.y <= r1.y + r1.height - 1) || (r2.y <= r1.y && r1.y <= r2.y + r2.height - 1);
    }

    public List<Point> getPoints(){
        List<Point> points = new ArrayList<>();
        for(int i = x; i < x + width;i++){
            for(int j = y; j < y + height;j++){
                points.add(new Point(i,j));
            }
        }
        return points;
    }

    public void draw(){
        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                world[i][j] = floor;
            }
        }
    }

}
