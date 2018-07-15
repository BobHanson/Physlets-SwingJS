package circuitsimulator;

/**
 * Used by circuitGrid. Mathematical tool for matrix actions (inversion, ...).
 * 
 * @author Toon Van Hoecke
 */
public class Matrix 
{
	int rows, cols;
	double[][] data;

	public Matrix(int r, int c)	{
	    data = new double[r][c];
	    rows = r;
	    cols = c;
	    for (int i=0;i<rows;i++) for (int j=0;j<cols;j++) data[i][j]=0;
	}
    
	public Matrix(int r, int c,double[][] elem)	{
	    data = new double[r][c];
	    rows = r;
	    cols = c;
    	for (int i = 0; i < rows; i++)
	        for (int j = 0; j < cols; j++) data[i][j]=elem[i][j];
	}
    
    
	public double get_elem(int r, int c) {
	    if (r < 0 || r >= rows) return 0;
	    if (c < 0 || c >= cols) return 0;
	    return data[r][c];
	}

	public void set_elem(int r, int c, double x) {
	    if (r < 0 || r >= rows) return;
	    if (c < 0 || c >= cols) return;
	    data[r][c] = x;
	}

	public void print()	{
	    System.out.println("Rows = " + rows + " Cols = " + cols);
	    for (int i = 0; i < rows; i ++)	{
		    StringBuffer s = new StringBuffer();
		    for (int j = 0; j < cols; j ++) {
			    s.append(get_elem(i, j));
			    if (j < cols - 1) s.append(",\t");
			}
		    System.out.println(s.toString());
		}
	}

	public void matmul(Matrix a, Matrix b) {
	    if ((a.cols != b.rows) || (a.rows != rows) ||(b.cols != cols)) return;
    	for (int i = 0; i < rows; i ++)
	    	for (int j = 0; j < cols; j ++)	{
			    double s = 0;
			    for (int k = 0; k < a.cols; k ++) s += a.data[i][k] * b.data[k][j];
			    set_elem(i, j, s);
			}
	}

    public double[] matmul(double[] x) {
        double[] sol = new double[rows]; 
	    for (int j = 0; j < rows; j ++)	{
		    sol[j]=0;
		    for (int k = 0; k < cols; k ++)	sol[j] += data[j][k]*x[k];
		}
        return sol;
    }
    
    public void resolve(double[] x) {
        double sol;
	    for (int j = 0; j < rows; j ++)	{
		    sol=0;
		    for (int k = 0; k < cols; k ++)	sol += data[j][k]*x[k];
		    x[j]=sol;
		}
    }
    

	public void copy(Matrix a) {
	    for (int i = 0; i < rows; i ++)
		    for (int j = 0; j < cols; j ++) set_elem(i, j, a.get_elem(i, j));
	}

	public void matinv(Matrix a) {
	    double te;
	    if (a.rows < 1 || a.rows != a.cols || a.rows != rows || a.cols != cols) return;
	    if (a.rows == 1) {
	        te = a.get_elem(0, 0);
		    set_elem(0, 0, 1.0 / (te==0?1e-30:te) );
		    return;
		}
    	Matrix b = new Matrix(rows, cols);
	    b.copy(a);
	    int n = rows;
	    for (int i = 0; i < n; i ++)
		    for (int j = 0; j < n; j ++)
			    if (i == j) set_elem(i, j, 1); else set_elem(i, j, 0);
        for (int i = 0; i < n; i ++) { 
    		// find pivot
    		double mag = 0;
	    	int pivot = -1;
    		for (int j = i; j < n; j ++) {
			    double mag2 = Math.abs(b.get_elem(j, i));
			    if (mag2 > mag) {mag = mag2; pivot = j;}
			}
    		if (pivot == -1 || mag == 0) return; // no pivot (error)
    		// move pivot row into position
    		if (pivot != i) { 
	    		double temp;
			    for (int j = i; j < n; j ++) {
				    temp = b.get_elem(i, j);
				    b.set_elem(i, j, b.get_elem(pivot, j));
				    b.set_elem(pivot, j, temp);
				}
    			for (int j = 0; j < n; j ++) {
				    temp = get_elem(i, j);
				    set_elem(i, j, get_elem(pivot, j));
				    set_elem(pivot, j, temp);
				}
			}
    		// normalize pivot row
    		mag = b.get_elem(i, i); 
    		mag = mag==0? 1e-30:mag;
	    	for (int j = i; j < n; j ++) b.set_elem(i, j, b.get_elem(i, j) / mag);
		    for (int j = 0; j < n; j ++) set_elem(i, j, get_elem(i, j) / mag);
    		// eliminate pivot row component from other rows
    		for (int k = 0; k < n; k ++) {
			    if (k == i) continue;
    			double mag2 = b.get_elem(k, i);
    			for (int j=i; j<n; j++) b.set_elem(k,j,b.get_elem(k,j) - mag2*b.get_elem(i,j));
			    for (int j=0; j<n; j++) set_elem(k,j,get_elem(k,j) - mag2*get_elem(i,j));
			}
		}
	}
}
