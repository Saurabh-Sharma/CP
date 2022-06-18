class Point {
    double x, y;
    Point(double _x, double _y) {
        x = _x;
        y = _y;
    }
}

public class ConvexHull {
    public int orientation(Point a, Point b, Point c) {
        double v = a.x*(b.y-c.y) + b.x*(c.y-a.y) + c.x*(a.y-b.y);
        if(v<0) return -1; // clockwise
        if(v>0) return 1;   // counter - clockwise
        return 0;
    }    
    
    public boolean collinear(Point a, Point b, Point c) {
        return orientation(a, b, c) == 0;
    }
    
    public boolean clockwise(Point a, Point b, Point c, boolean include_collinear) {
        int o = orientation(a, b, c);
        return o < 0 || (include_collinear && o==0);
    } 
    
    public ArrayList<Point> convexHull(Point[] A, boolean include_collinear) {
        Point t0 = A[0];
        for(int i=1; i<A.length; i++) {
            Point p = A[i];
            if(t0.y>p.y || (t0.y==p.y && t0.x>p.x)) {
                t0 = p;
            }
        }
        final Point p0 = t0;
        Arrays.sort(A, (p1, p2) -> {
            int o = orientation(p0, p1, p2);
            if(o==0) {
                return ((p0.x-p1.x)*(p0.x-p1.x) + (p0.y-p1.y)*(p0.y-p1.y)
                < (p0.x-p2.x)*(p0.x-p2.x) + (p0.y-p2.y)*(p0.y-p2.y))?-1:1;
            }
            return (o<0)?-1:1;
        });
        
        if(include_collinear) {
            int i = A.length-1;
            while(i>=0 && collinear(p0, A[i], A[A.length-1])) i--;
            reverse(A, i+1, A.length-1);
        }
        
        ArrayList<Point> st = new ArrayList<>();
        for(int i=0; i<A.length; i++) {
            while(st.size()>1 && !clockwise(st.get(st.size()-2), st.get(st.size()-1), A[i], include_collinear)) st.remove(st.size()-1); 
            st.add(A[i]);
        }
        
        return st;
    }
    
    public void reverse(Point[] A, int i, int j) {
        while(i<j) {
            Point t = A[i];
            A[i] = A[j];
            A[j] = t;
            i++; j--;
        }
    }
}