public class Vector{
    private static boolean rad = false; // We'll use degrees by default.
    private String name;
    private double magnitude, iHat, jHat, theta;

    public Vector(String name, double iHat, double jHat){
        this.name = name;
        this.iHat = iHat;
        this.jHat = jHat;

        // Compute magnitude using Pythagoras' Theorem.
        this.magnitude = Math.sqrt((Math.pow(this.iHat, 2) + Math.pow(this.jHat, 2)));

        if(iHat == 0 && jHat == 0)
            this.theta = 0; // No Vector
        else if(iHat == 0 && jHat > 0)
            this.theta = 90;
        else if(iHat == 0 && jHat < 0)
            this.theta = 270;
        else if(jHat == 0 && iHat > 0)
            this.theta = 0;
        else if(jHat == 0 && iHat < 0)
            this.theta = 180;
        else{
            this.theta = (180/Math.PI) * Math.atan(Math.abs(jHat / iHat));

            if(iHat < 0 && jHat > 0) // 2nd Quadrant
                    this.theta = (90 + (90-this.theta));

            else if(iHat < 0 && jHat < 0) // 3rd Quadrant
                    this.theta += 180;

            else if(iHat > 0 && jHat < 0) // 4th Quadrant
                    this.theta = (270 + (90 - this.theta));
            if(rad)
                this.theta *= (Math.PI / 180);
        }
    }

    public String getName(){return this.name;}
    public double getIHat(){return this.iHat;}
    public double getJHat(){return this.jHat;}
    public double getMagnitude(){return this.magnitude;}
    public double getTheta(){return this.theta;}

    public Vector plus(Vector other){
        String nName = this.name + " + " + other.name + " (Resultant)";
        double nIHat, nJHat;
        nIHat = this.iHat + other.iHat;
        nJHat = this.jHat + other.jHat;
        return new Vector(nName, nIHat, nJHat);
    }

    public Vector negate(){
        return new Vector("-" + this.name, -this.iHat, -this.jHat);
    }

    public Vector minus(Vector other){
        String nName = this.name + " - " + other.name + " (Resultant)";
        Vector temp = other.negate();
        double nIHat, nJHat;
        nIHat = this.iHat + temp.iHat;
        nJHat = this.jHat + temp.jHat;
        return new Vector(nName, nIHat, nJHat);
    }

    public String toString(){
        return this.name + " is " + magnitude + " at " + theta + " degrees.\n"
         + this.name + ": " + iHat + "î + " + jHat + "ĵ.";
    }
    public static void main(String[] args){

        Vector A = new Vector("A", -14.142, 14.142);
        System.out.println(A);

        Vector B = new Vector("B", 0, -20);
        System.out.println(B);

        System.out.println(A.minus(B));

    }
}