

public class Algorithem {
	public static void main(String[] args){
		double[] xy = Algorithem.trilateration(3,5,2.88,5,2,2.45,1,0,2.58);
		System.out.println(xy[0]+"::"+xy[1]);
//		double[] xy = Algorithem.trilateration(0, 0, 2, Math.sqrt(3), 3, 2, 2*Math.sqrt(3), 0, 2);
//		System.out.println(xy[0]+"::"+xy[1]);
//		3, 519, 96, 600, 519, 60, 300, 0, 10
	}
	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param d1
	 * @param x2
	 * @param y2
	 * @param d2
	 * @param x3
	 * @param y3
	 * @param d3
	 * @return
	 * 
	 * 
	 */
	public static double[] trilateration(double x1,double y1,double d1,
								double x2, double y2,double d2,
								double x3, double y3, double d3){
		double []d={0.0,0.0};
		double a11 = 2*(x1-x3);
		double a12 = 2*(y1-y3);
		double b1 = Math.pow(x1,2)-Math.pow(x3,2)
							+Math.pow(y1,2)-Math.pow(y3,2)
							+Math.pow(d3,2)-Math.pow(d1,2);
		double a21 = 2*(x2-x3);
		double a22 = 2*(y2-y3);
		double b2 = Math.pow(x2,2)-Math.pow(x3,2)
							+Math.pow(y2,2)-Math.pow(y3,2)
							+Math.pow(d3,2)-Math.pow(d2,2);
		
		d[0]=(b1*a22-a12*b2)/(a11*a22-a12*a21);
		d[1]=(a11*b2-b1*a21)/(a11*a22-a12*a21);
		
		return d;
		
	}
}
