package plugin.FRCustom;

public class FRPoint3D {
    protected boolean isNull;
    protected double x;
    protected double y;
    protected double z;
    
    public FRPoint3D(double x, double y, double z) {
        this.isNull = false;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public FRPoint3D(int x, int y, int z) {
        this.isNull = false;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public FRPoint3D() {
        this.isNull = true;
    }
    
    public boolean isNull() {
        return isNull;
    }
    
    public void setNull(boolean Boolean) {
        isNull = Boolean;
    }
    
    public double getX() {
        return x;
    }
    public void setX(int x) {
        this.isNull = false;
        this.x = x;
    }
    public void setX(double x) {
        this.isNull = false;
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(int y) {
        this.isNull = false;
        this.y = y;
    }
    public void setY(double y) {
        this.isNull = false;
        this.y = y;
    }
    public double getZ() {
        return z;
    }
    public void setZ(int z) {
        this.isNull = false;
        this.z = z;
    }
    public void setZ(double z) {
        this.isNull = false;
        this.z = z;
    }
    public double distance(FRPoint3D pt) { 
        return Math.sqrt( Math.pow((pt.getX()-this.x),2) + Math.pow((pt.getY()-this.y),2) + Math.pow((pt.getZ()-this.z),2) );
    }
    public double distance(double px, double py, double pz) { 
        return Math.sqrt( Math.pow((px-this.x),2) + Math.pow((py-this.y),2) + Math.pow((pz-this.z),2) );
    }
    public static double distance(double x1, double y1, double z1, double x2, double y2, double z2) { 
        return Math.sqrt( Math.pow((x1-x2),2) + Math.pow((y1-y2),2) + Math.pow((z1-z2),2) );
    }
    public boolean equals(Object obj) {
        if (isNull) return false;
        if (obj instanceof FRPoint3D) {
            FRPoint3D pt = (FRPoint3D)obj;
            return (x == pt.x) && (y == pt.y) && (z == pt.z);
        }
        return false;
    }
    @Override
    public String toString() {
        if (isNull) return "null";
        return "x = "+x+" | y = "+y+" | z = "+z;
    }
}
