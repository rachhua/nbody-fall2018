
public class Body {
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName = new String();
	
	/**
	 * Constructor creates a Body from parameters
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity 
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 * 
	 */
	public Body(double xp, double yp, double xv, double yv, double mass, String filename) {
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
		
	}
	
	/**
	 * Mutator method, does not return a value
	 * Updates the state/instance variables of Body object on which it's called
	 * @param deltaT times steps in which this method will update the body's position and velocity
	 * @param xforce net forces in x direction exerted on this body by all other bodies
	 * @param yforce net forces in y direction exerted on this body by all other bodies
	 */
	public void update(double deltaT, double xforce, double yforce) {
		double accX = 0.0;
		double accY = 0.0;
		double nvx = 0.0;
		double nvy = 0.0;
		double nx = 0.0;
		double ny = 0.0;
		accX = xforce / this.getMass();
		accY = yforce / this.getMass();
		nvx = myXVel + deltaT * accX;
		nvy = myYVel + deltaT * accY;
		nx = myXPos + deltaT * nvx;
		ny = myYPos + deltaT * nvy;
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;
		
	}
	
	/**
	 * Copy constructor: copy instance variables from one
	 * body to this body
	 * @param b used to initialize this body
	 */
	public Body(Body b) {
		myXPos = b.getX();
		myYPos = b.getY();
		myXVel = b.getXVel();
		myYVel = b.getYVel();
		myMass = b.getMass();
		myFileName = b.myFileName;
	}
	
	/**
	 * Getter method that returns x position of this body
	 * @return value of x position
	 */
	public double getX() {
		return myXPos;
	}
	
	/**
	 * Getter method that returns y position of this body
	 * @return value of y position
	 */
	public double getY() {
		return myYPos;
	}
	
	/**
	 * Getter method that returns x velocity of this body
	 * @return value of x velocity
	 */
	public double getXVel() {
		return myXVel;
	}
	
	/**
	 * Getter method that returns y velocity of this body
	 * @return value of y velocity
	 */
	public double getYVel() {
		return myYVel;
	}
	
	/**
	 * Getter method that returns mass of this body
	 * @return value of mass
	 */
	public double getMass() {
		return myMass;
	}
	
	/**
	 * Getter method that returns name of the file
	 * @return name of the file
	 */
	public String getName() {
		return myFileName;
	}
	
	/**
	 * Return distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(Body b) {
		double dist;
		dist = Math.sqrt(Math.pow(b.getX()-this.getX(),2)+Math.pow(b.getY()-this.getY(), 2)); //check this
		return dist;
	}
	
	/**
	 * Returns force exerted on this body by body specified in parameter
	 * @param p the body that is exerting force onto this one
	 * @return force exerted on this body by body p
	 */
	public double calcForceExertedBy(Body p) {
		double force;
		force = ((6.67*1e-11) * p.getMass() * this.getMass()) / ((this.calcDistance(p)) * (this.calcDistance(p)));
		return force;
	}
	
	/**
	 * Returns force exerted in x direction
	 * @param p the body that is exerting force in the x direction
	 * @return force exerted by p in the x direction
	 */
	public double calcForceExertedByX(Body p) {
		double forceX;
		forceX = (this.calcForceExertedBy(p) * (p.getX()-this.getX())) / this.calcDistance(p);
		return forceX;
	}
	/**
	 * Returns force exerted in the y direction
	 * @param p the body that is exerting force in the y direction
	 * @return force exerted by p in the y direction
	 */
	public double calcForceExertedByY(Body p) {
		double forceY;
		forceY = (this.calcForceExertedBy(p) * (p.getY()-this.getY())) / this.calcDistance(p);
		return forceY;
	}
	
	/**
	 * Returns total/net force exerted on this body by all the bodies in array parameter
	 * @param bodies array of all the bodies that are exerting force on this body
	 * @return net force exerted on this body in X direction by all bodies in the parameter
	 */
	public double calcNetForceExertedByX(Body[] bodies) {
		double sum = 0;
		for (Body b: bodies) {
			if(! b.equals(this)) {
				sum += this.calcForceExertedByX(b);
			}
		}
		return sum;
	}
	
	/**
	 * Returns total/net force exerted on this body by all the bodies in array parameter
	 * @param bodies array of all the bodies that are exerting force on this body
	 * @return net force exerted on this body in y direction by all bodies in the parameter
	 */
	public double calcNetForceExertedByY(Body[] bodies) {
		double sum = 0;
		for (Body b: bodies) {
			if(! b.equals(this)) {
				sum += this.calcForceExertedByY(b);
			}
		}
		return sum;
				
	}
	
	/**
	 * Call to this void method draws the image
	 */
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
	
}
