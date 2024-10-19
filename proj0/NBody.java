import examples.In;
import examples.StdDraw;

import java.io.File;

public class NBody {
    private static final String BACKGROUND = "starfield.jpg";
    private static final String IMAGE_PATH = "images/";

    private static double T;
    private static double dt;
    private static String filename;
    private static double radius;
    private static Planet[] planets;

    public static void main(String[] args) {
        T = Double.parseDouble(args[0]);
        dt = Double.parseDouble(args[1]);
        filename = args[2];
        radius = readRadius(filename);
        planets = readPlanets(filename);

        //设置比例，使其与宇宙半径相匹配
        StdDraw.setScale(-radius,radius);
        StdDraw.enableDoubleBuffering();

        StdDraw.clear();
        StdDraw.picture(0,0,IMAGE_PATH + BACKGROUND);
        StdDraw.show();
        drawPlanets(planets);
        for(double time =0 ; time < T; time += dt){
            double[] xForces = calcNetXForces(planets);
            double[] yForces = calcNetYForces(planets);
            updatePlanetsPositions(planets,xForces,yForces);
            StdDraw.clear();
            StdDraw.picture(0,0,IMAGE_PATH+BACKGROUND);
            drawPlanets(planets);
            StdDraw.show();
            StdDraw.pause(10);

        }
        printToStdOut();

    }

    private static void drawPlanets(Planet[] planets){
        for (Planet planet : planets) {
            StdDraw.picture(planet.xxPos,planet.yyPos,IMAGE_PATH + planet.imgFileName);
        }
    }

    private static void updatePlanetsPositions(Planet[] planets,double[] xForces,double[] yForces){
        for (int i = 0; i < planets.length; i++) {
            planets[i].update(dt,xForces[i],yForces[i]);
        }
    }

    public static double readRadius(String file){
        In in = new In(file);

        int PlanetNum = in.readInt();
        double radiusOfuniverse = in.readDouble();

//        while(!in.isEmpty()){
//            double xxPos = in.readDouble();
//            double yyPos = in.readDouble();
//            double xxVel = in.readDouble();
//            double yyVel = in.readDouble();
//            double mass = in.readDouble();
//            String img = in.readString();
//        }

        return radiusOfuniverse;
    }

    public static Planet[] readPlanets(String filename) {
        File file = new File(filename);
        In in = new In(file);
        Planet[] planets;
        int n = in.readInt();
        planets = new Planet[n];
        in.readDouble();
        for (int i = 0; i < n; i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            double vx = in.readDouble();
            double vy = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planets[i] = new Planet(x, y, vx, vy, mass, imgFileName);
        }
        in.close();
        return planets;
    }

    //获取x方向上 每个行星净力的数组
    public static double[] calcNetXForces(Planet[] planets){
        double[] XForces = new double[planets.length];
        for (int i = 0; i < planets.length; i++) {
            XForces[i] = planets[i].calcNetForceExertedByX(planets);
        }
        return XForces;
    }
    //获取y方向上 每个行星净力的数组
    public static double[] calcNetYForces(Planet[] planets){
        double[] YForces = new double[planets.length];
        for (int i = 0; i < planets.length; i++) {
            YForces[i] = planets[i].calcNetForceExertedByY(planets);
        }
        return YForces;
    }

    private static void printToStdOut() {
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
