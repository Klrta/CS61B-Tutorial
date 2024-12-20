package byog.Core;

import byog.TileEngine.TETile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Walls {
    private final List<Point> points;   //墙壁位置集合
    private final List<Point> gPoints;  //房间和走廊集合
    private final List<Room> rooms; //房间
    private final List<Hallway> hallways; // 走廊
    private final TETile[][] world; //画板
    private final TETile floor;
    private final TETile wall;//floor 和 wall 分别代表房间和走廊的地面瓷砖类型以及墙壁的瓷砖类型。

    public Walls(Room[] roomsP,Hallway[] hallwaysP,TETile floorP, TETile wallP, TETile[][] worldP){
        this.rooms = Arrays.asList(roomsP);
        this.hallways = Arrays.asList(hallwaysP);
        this.world = worldP;
        this.wall = wallP;
        this.floor = floorP;
        this.points = new ArrayList<>();
        this.gPoints = new ArrayList<>();
        populateGeometryPoints();//该方法填充 gPoints 列表，首先遍历房间和走廊，将它们占据的所有坐标点添加到 gPoints 中。
        generateWalls();//该方法负责生成墙壁。它使用三次遍历世界的方式来添加不同位置的墙壁：
    }

    public Walls(List<Room> roomsP,List<Hallway> hallwaysP,TETile floorP, TETile wallP, TETile[][] worldP){
        this.rooms = roomsP;
        this.hallways = hallwaysP;
        this.world = worldP;
        this.wall = wallP;
        this.floor = floorP;
        this.points = new ArrayList<>();
        this.gPoints = new ArrayList<>();
        populateGeometryPoints();//该方法填充 gPoints 列表，首先遍历房间和走廊，将它们占据的所有坐标点添加到 gPoints 中。
        generateWalls();//该方法负责生成墙壁。它使用三次遍历世界的方式来添加不同位置的墙壁：
    }

    public Walls(List<Point> geometryPoints,TETile floorP, TETile wallP, TETile[][] worldP){
        this.rooms = null;
        this.hallways = null;
        this.world = worldP;
        this.wall = wallP;
        this.floor = floorP;
        this.points = new ArrayList<>();
        this.gPoints = geometryPoints;
        generateWalls();
    }

    /**
     * Populates List<Point> gPoints, which contains all the points corresponding to rooms
     * and walls that are in the world.
     */
    private void populateGeometryPoints() {
        for (Room room : rooms) {
            List<Point> roomPoints = room.getPoints();
            for (Point p : roomPoints) {
                if (!gPoints.contains(p)) {
                    gPoints.add(p);
                }
            }
        }
        for (Hallway hallway : hallways) {
            List<Point> hallwayPoints = hallway.getPoints();
            for (Point p : hallwayPoints) {
                if (!gPoints.contains(p)) {
                    gPoints.add(p);
                }
            }
        }
    }

    /**
     * Generates all the walls for the world.
     * First, sweep the world left-right and bottom-up, to add right and upper walls,
     * and top-right corners.
     * Second, sweep the world right-left and top-down, to add left and lower walls,
     * and bottom-left corners.
     * Third, sweep the world left-right and bottom-up, to add top-left and bottom-right corners.
     */
    private void generateWalls() {
        /* 1-sweep left-right and bottom-up, draw right and upper walls, and top-right corners */
        for (int x = 1; x < world.length; x++) {
            for (int y = 1; y < world[x].length; y++) {
                Point current = new Point(x, y);
                Point below = new Point(x, y - 1);
                Point left = new Point(x - 1, y);
                Point belowLeft = new Point(x - 1, y - 1);
                if (gPoints.contains(current)) {
                    continue;
                }
                if (gPoints.contains(left)) {
                    addWall(x, y);
                }
                if (gPoints.contains(below)) {
                    addWall(x, y);
                }
                if (points.contains(left) && points.contains(below)
                        && gPoints.contains(belowLeft)) {
                    addWall(x, y);
                }
            }
        }
        /* 2-sweep right-left and top-down, draw left and lower walls, and bottom-left corners */
        for (int x = world.length - 2; x >= 0; x--) {
            for (int y = world[x].length - 2; y >= 0; y--) {
                Point current = new Point(x, y);
                Point above = new Point(x, y + 1);
                Point right = new Point(x + 1, y);
                Point aboveRight = new Point(x + 1, y + 1);
                if (gPoints.contains(current) || points.contains(current)) {
                    continue;
                }
                if (gPoints.contains(right)) {
                    addWall(x, y);
                }
                if (gPoints.contains(above)) {
                    addWall(x, y);
                }
                if (points.contains(right) && points.contains(above)
                        && gPoints.contains(aboveRight)) {
                    addWall(x, y);
                }
            }
        }
        /* 3-sweep left-right and bottom-up, draw top-left and bottom-right corners */
        for (int x = 1; x < world.length; x++) {
            for (int y = 1; y < world[x].length; y++) {
                Point current = new Point(x, y);
                Point below = new Point(x, y - 1);
                Point left = new Point(x - 1, y);
                Point belowLeft = new Point(x - 1, y - 1);
                if (!points.contains(current)) {
                    continue;
                }
                if (gPoints.contains(below) && !gPoints.contains(left)
                        && points.contains(belowLeft)) {
                    addWall(x - 1, y);
                }
                if (!gPoints.contains(below) && gPoints.contains(left)
                        && points.contains(belowLeft)) {
                    addWall(x, y - 1);
                }
            }
        }
    }

    /**
     * Adds a wall, on the world, at the coordinates passed as parameter.
     * @param x is the x coordinate on which to add a single-tile wall.
     * @param y is the y coordinate on which to add a single-tile wall.
     */
    private void addWall(int x, int y) {
        Point p = new Point(x, y);
        points.add(p);
    }

    /**
     * Adds a wall, on the world, at the point passed as parameter.
     * @param p the point on which to add a single-tile wall.
     */
    private void addWall(Point p) {
        points.add(p);
    }
    /**
     * Return the Walls representation as a  List of Points, representing the set of coordinates
     * occupied by the all the walls in the world.
     * @return the set of points that the walls occupy.
     */
    public List<Point> getPoints() {
        return points;
    }

    /**
     * Draws the Walls on the world which they reference.
     */
    public void draw() {
        for (Point p : points) {
            world[p.x()][p.y()] = wall;
        }
    }

}
