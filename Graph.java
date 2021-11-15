import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
class Graph extends Canvas{  
  private static Scanner input = new Scanner(System.in);
  private String name;
  private int xDim, yDim, multiplier;
  private ArrayList<Vector> vectors;
  private ArrayList<double[]> vBreakdowns;
  private int mode; // 0: Auto(all resultants are graphed from the origin, all normal vectors are graphed end-to-end), 1: All end to end, 2: All from origin.
  public Graph(String name, int xDim, int yDim, int multiplier, ArrayList<Vector> vectors, int mode) {  
    this.xDim = xDim;
    this.yDim = yDim;
    this.multiplier = multiplier;
    this.vectors = vectors;
    this.vBreakdowns = new ArrayList<double[]>();
    this.mode = mode;
    this.name = name;
    if(this.mode == 0)
      this.name += " (auto) ";
    else if(this.mode == 1)
      this.name += " (end to end) ";
    else if (this.mode  == 2)
      this.name += " (all from origin) ";
    for(int i = 0; i < vectors.size(); i++){
      this.name += vectors.get(i).getName();
      if(i < vectors.size() - 1)
        this.name += ", ";
    }
    this.name += "]";
    
    setBackground(Color.BLACK);
    setSize(xDim, yDim);
    if(this.mode == 0)
      this.vBreakdowns = breakdownAuto(this.vectors);
    else if(this.mode == 1)
      this.vBreakdowns = breakdownEToE(this.vectors);
    else if(this.mode == 2)
      this.vBreakdowns = breakdown(this.vectors);
    
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
      g.drawLine((int)Math.round(a[0]), (int)Math.round(a[1]), (int)Math.round(a[2]), (int)Math.round(a[3])); // Rounding :(

  }

  public double[] breakdown(Vector v){ // Returns an integer array used to graph a vector in the Graph class. (converts iHat, jHat into (x1, x2, y1, y2))
    double x1, y1, x2, y2;
    x1 = this.xDim / 2;
    y1 = this.yDim / 2;
    x2 = x1 + this.multiplier * v.getIHat();
    y2 = y1 - this.multiplier * v.getJHat();

    return new double[]{x1, y1, x2, y2};
  }

  public ArrayList<double[]> breakdown(ArrayList<Vector> vectors){ // Returns an Vector ArrayList used to graph a vector in the Graph class.(converts iHat, jHat into (x1, x2, y1, y2))
    ArrayList<double[]> output = new ArrayList<double[]>();
    for(Vector v : vectors){
    double x1, y1, x2, y2;
      x1 = this.xDim / 2;
      y1 = this.yDim / 2;
      x2 = x1 + this.multiplier * v.getIHat();
      y2 = y1 - this.multiplier * v.getJHat();

      output.add(new double[]{x1, y1, x2, y2});
    }
    return output;
  }

  public ArrayList<double[]> breakdownEToE(ArrayList<Vector> vectors){
    ArrayList<double[]> output = new ArrayList<double[]>();
    for(int i = 0; i < vectors.size(); i++){
      double x1, y1, x2, y2;
      if(i == 0){
        x1 = this.xDim / 2;
        y1 = this.yDim / 2;
      }else{
        x1 = output.get(i - 1)[2];
        y1 = output.get(i - 1)[3];
      }
        x2 = x1 + this.multiplier * vectors.get(i).getIHat();
        y2 = y1 - this.multiplier * vectors.get(i).getJHat();
        output.add(new double[]{x1, y1, x2, y2});
      }
      return output;
  }

  public ArrayList<double[]> breakdownAuto(ArrayList<Vector> vectors){
    ArrayList<double[]> output = new ArrayList<double[]>();
    ArrayList<double[]> resultants = new ArrayList<double[]>();
    for(int i = 0; i < vectors.size(); i++){
      if (vectors.get(i).getName().contains("Resultant"))
        resultants.add(breakdown(vectors.get(i)));
      else{
        double x1, y1, x2, y2;
        if(i == 0){
          x1 = this.xDim / 2;
          y1 = this.yDim / 2;
        }else{
          x1 = output.get(i - 1)[2];
          y1 = output.get(i - 1)[3];
        }
          x2 = x1 + this.multiplier * vectors.get(i).getIHat();
          y2 = y1 - this.multiplier * vectors.get(i).getJHat();
          output.add(new double[]{x1, y1, x2, y2});
        }
      }
      for(double[] d : resultants)
        output.add(d);
      return output;
  }
  

  public String toString(){
    return this.name + " has been graphed.";
  }

  public static void main(String[] args){
    ArrayList<Vector> v = new ArrayList<Vector>();
    v.add(new Vector("v0", -14.142, 14.142));
    v.add(new Vector("v1", 0, -20));
    v.add(new Vector("v2", 30, 45));
    v.add(v.get(0).plus(v.get(1)));
    v.add(v.get(2).plus(v.get(1)));

    Graph graph = new Graph("Graph", 700, 700, 5, v, 0);
    
    
    // Exit stuff
    String in = "...";
    while(!in.equals("")){
      System.out.print("Press [Enter] to close graph(s).");
      in = input.nextLine();
    }
    System.exit(0);
  }
}       