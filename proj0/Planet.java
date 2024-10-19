import examples.StdDraw;

public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        double d = calcDistance(p);//距离
        double f = (G * p.mass * this.mass) / (d * d);
        return f;
    }

    public double calcForceExertedByX(Planet p){
        double f = calcForceExertedBy(p);//p对自己施加的力
        double d = calcDistance(p);//距离
        double fx = f * (p.xxPos - this.xxPos) / d;
        return fx;
    }

    public double calcForceExertedByY(Planet p){
        double f = calcForceExertedBy(p);//p对自己施加的力
        double d = calcDistance(p);//距离
        double fy = f * (p.yyPos - this.yyPos) / d;
        return fy;
    }

    public double calcNetForceExertedByX(Planet[] ps){
        double fxNet = 0;
        for (Planet p : ps) {
            if(this.equals(p)){
                continue;
            }else {
                fxNet += calcForceExertedByX(p);
            }
        }
        return fxNet;
    }

    public double calcNetForceExertedByY(Planet[] ps){
        double fyNet = 0;
        for (Planet p : ps) {
            if(this.equals(p)){
                continue;
            }else {
                fyNet += calcForceExertedByY(p);
            }
        }
        return fyNet;
    }

    //接受fx,fy的力,在dt时间内,自己的速度和位置变化
    public void update(double dt, double fx, double fy){
        double ax = fx / this.mass;
        double ay = fy / this.mass;//求加速度

        this.xxVel = this.xxVel + dt * ax;
        this.yyVel = this.yyVel + dt * ay;//求x,y方向速度

        this.xxPos = this.xxPos + dt * xxVel;
        this.yyPos = this.yyPos + dt * yyVel; //求变更后的位置
    }

    //绘制星球
    public void draw(){
        StdDraw.picture(xxPos,yyPos,imgFileName);
    }

    //
}
