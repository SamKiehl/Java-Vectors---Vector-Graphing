import java.awt.*;
import java.util.ArrayList;
class Graph extends Canvas{  
  private String name;
  private int xDim, yDim;
  private ArrayList<Vector> vectors;
  private ArrayList<double[]> vBreakdowns;
  public Graph(String name, int xDim, int yDim, ArrayList<Vector> vectors) {  
    this.name = name + ": ["; 
    for(int i = 0; i < vectors.size(); i++){
      this.name += vectors.get(i).getName();
      if(i < vectors.size() - 1)
        this.name += ", ";
    }
    this.name += "]";
    
    this.xDim = xDim;
    this.yDim = yDim;
    this.vectors = vectors;
    this.vBreakdowns = new ArrayList<double[]>();
    setBackground(Color.BLACK);
    setSize(xDim, yDim);
    for(Vector v : this.vectors)
      this.vBreakdowns.add(this.breakdown(v));
    this.setup();
  }    

  public void setup(){
    Frame f = new Frame(this.name); 
    f.add(this);
    f.setLayout(null);    
    f.setSize(this.xDim, this.yDim);    
    f.setVisible(true);  
    System.out.println(this);
  }
  
     // paint() method to draw inside the canvas  
  public void paint(Graphics g){    
    // adding specifications  
    g.setColor(Color.BLUE);    
    g.drawLine(this.xDim / 2, 0, this.xDim / 2, this.yDim);
    g.drawLine(0, this.yDim / 2, this.xDim, this.yDim / 2);


    g.setColor(Color.GREEN); 

    for(double[] a : vBreakdowns)
      g.drawLine((int)(a[0]), (int)(a[1]), (int)(a[2]), (int)(a[3])); // Rounding :(

  }

  public double[] breakdown(Vector v){ // Returns an integer array used to graph a vector in the Graph class.
    double x1, y1, x2, y2;
    x1 = this.xDim / 2;
    y1 = this.yDim / 2;             //FIX
    x2 = x1 + v.getIHat();
    y2 = y1 - v.getJHat();

    return new double[]{x1, y1, x2, y2};
  }

  public String toString(){
    return this.name + " has been graphed.";
  }

  public static void main(String[] args){
    ArrayList<Vector> v = new ArrayList<Vector>();
    v.add(new Vector("v1", 50, 50));
    v.add(new Vector("v2", -50, 150));
    v.add(new Vector("v3", 190, -120));
    v.add(v.get(0).plus(v.get(1)));
    v.add(v.get(1).minus(v.get(2)));

    Graph graph = new Graph("Graph", 700, 700, v);

  }
}       