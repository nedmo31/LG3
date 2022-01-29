package golf3;

/**
 * Write a description of class Terrain here.
 *
 * @author Nathan Edmondson
 * @version (a version number or a date)
 */
public class TerrainChunk
{
    // instance variables - replace the example below with your own
    private double x1, x2, x3, x4, y1, y2, y3, y4;
    private double ang;
    private String type;
    private boolean solid;
    /*
     * Chunks are polygons.top left corner 1
     * 
     * Types are grass, sand, water
     */
    /**
     * 4 sided polygon numbered top left top right bot left bot right
     * Defaults to grass type
     */
    public TerrainChunk(double x1, double x2, double x3, double x4, double y1, 
    	    double y2, double y3, double y4)
    	    {
    	        this.x1 = x1; this.x2 = x2; this.y1 = y1; this.y2 = y2; 
    	        this.x3 = x3; this.x4 = x4; this.y3 = y3; this.y4 = y4;
    	        ang = - Math.atan( (y2 - y1) / (x2 - x1 ) );
    	        this.type = "grass"; solid = true;
    	    }
    public TerrainChunk(double x1, double x2, double x3, double x4, double y1, 
    double y2, double y3, double y4, boolean b)
    {
        this.x1 = x1; this.x2 = x2; this.y1 = y1; this.y2 = y2; 
        this.x3 = x3; this.x4 = x4; this.y3 = y3; this.y4 = y4;
        ang = - Math.atan( (y2 - y1) / (x2 - x1 ) );
        this.type = "grass"; solid = b;
    }
    
    /*
     * Short cut for simple chunks
     */
    public TerrainChunk(double x1, double x2, double y1, 
    	    double y2)
    	    {
    	        this.x1 = x1; this.x2 = x2; this.y1 = y1; this.y2 = y2; 
    	        this.x3 = x1; this.x4 = x2; this.y3 = 800; this.y4 = 800;
    	        ang = - Math.atan( (y2 - y1) / (x2 - x1 ) );
    	        this.type = "grass"; solid = true;
    	    }
    public TerrainChunk(double x1, double x2, double y1, 
    	    double y2, String s, boolean b)
    	    {
    	        this.x1 = x1; this.x2 = x2; this.y1 = y1; this.y2 = y2; 
    	        this.x3 = x1; this.x4 = x2; this.y3 = 800; this.y4 = 800;
    	        ang = - Math.atan( (y2 - y1) / (x2 - x1 ) );
    	        this.type = s; solid = b;
    	    }
    public TerrainChunk(double x1, double x2, double y1, 
    	    double y2, String type)
    	    {
    	        this.x1 = x1; this.x2 = x2; this.y1 = y1; this.y2 = y2; 
    	        this.x3 = x1; this.x4 = x2; this.y3 = 800; this.y4 = 800;
    	        ang = - Math.atan( (y2 - y1) / (x2 - x1 ) );
    	        this.type = type; solid = true;
    	    }
    public TerrainChunk(double x1, double x2, double x3, double x4, double y1, 
    double y2, double y3, double y4, String type)
    {
        this.x1 = x1; this.x2 = x2; this.y1 = y1; this.y2 = y2; 
        this.x3 = x3; this.x4 = x4; this.y3 = y3; this.y4 = y4;
        ang = - Math.atan( (y2 - y1) / (x2 - x1 ) );
        this.type = type; solid = true;
    }
    /*
     * For chunks directly to the left and right of the hole that 
     * make up the walls 
     */
    public TerrainChunk(TerrainChunk h, boolean left) {
        if (left) {
        	this.x1 = h.x1-1; this.x2 = h.x1; this.y1 = h.y1; this.y2 = h.y1+20;
        	this.x3 = h.x1-1; this.x4 = h.x1; this.y3 = 800; this.y4 = 800; 
        	this.type = "grass"; solid = true; } 
        else {
        	this.x1 = h.x2; this.x2 = h.x2+1; this.y1 = h.y1+20; this.y2 = h.y1;
            this.x3 = h.x2; this.x4 = h.x2+1; this.y3 = 800; this.y4 = 800; 
            this.type = "grass";  solid = true;
        }
    }
    /*
     * Chunk under the Hole
     */
    public TerrainChunk(double x1, double x2, double y) {
        this.x1 = x1; this.x2 = x2; this.y1 = y; this.y2 = y;
        this.x3 = x1; this.x4 = x2; this.y3 = 800; this.y4 = 800; 
        this.type = "hole"; solid = true;
    }
    
    public boolean inDrink(double x, double y) {
        if (this.type.equals("water")
        && x >= this.x1 && x<= this.x2
        && 600-y >= this.getTerrY(x)-.05) {
            return true;
        }
        return false;
    }
    public boolean inSandChunk(double x, double y) {
        if (this.type.equals("sand")
        && x >= this.x1 && x<= this.x2
        && 600-y >= this.getTerrY(x)-.05) {
            return true;
        }
        return false;
    }
    public double getx1() { return x1; }
    public double getx2() { return x2; }
    public double gety1() { return y1; }
    public double gety2() { return y2; }
    public double getx3() { return x3; }
    public double getx4() { return x4; }
    public double gety3() { return y3; }
    public double gety4() { return y4; }
    public int getx12() { return (int)x1; }
    public int getx22() { return (int)x2; }
    public int gety12() { return (int)y1; }
    public int gety22() { return (int)y2; }
    public int getx32() { return (int)x3; }
    public int getx42() { return (int)x4; }
    public int gety32() { return (int)y3; }
    public int gety42() { return (int)y4; }
    public boolean getSolid() { return solid; }
    public double getTerrY(double xx) { 
        return y1 + (xx - x1)*((y2-y1)/(x2-x1)); 
    }
    public double getTerrAng() {
        return ang;
    }
    public String getType() {
        return type;
    }
}
