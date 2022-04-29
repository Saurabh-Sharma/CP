class Node {
    int ss, se;
    Node lc, rc;
    List<Integer> ar;
    Node(int ss, int se) {
        this.ss = ss; this.se = se;
        this.lc = lc; this.rc = rc;
        ar = new ArrayList<>();
    }
}

class MergeSortTree {
    Node root;
    MergeSortTree(int[] ar) {
        root = build(0, ar.length-1, ar);
    }
    
    public Node build(int ss, int se, int[] ar) {
        Node cur = new Node(ss, se);
        if(ss==se) 
            cur.ar.add(ar[ss]);
        else {
            int m = (ss+se)/2;
            cur.lc = build(ss, m, ar);
            cur.rc = build(m+1, se, ar);
            
            cur.ar = merge(cur.lc.ar, cur.rc.ar);
        }
        return cur;
    }
    
    public List<Integer> merge(List<Integer> L, List<Integer> R) {
        List<Integer> cur = new ArrayList<>();
        int i=0, j=0;
        
        while(i<L.size() && j<R.size()) {
            if(L.get(i)<R.get(j))
                cur.add(L.get(i++));
            else 
                cur.add(R.get(j++));
        }
        
        while(i<L.size())
            cur.add(L.get(i++));
        while(j<R.size())
            cur.add(R.get(j++));
        
        return cur;
    }
    
    public int query(int ql, int qr, int val) {
        return query(root, ql, qr, val);
    }
    
    public int query(Node cur, int qs, int qe, int val) {
        if(cur.se<qs || qe<cur.ss) return 0;
        else if(qs<=cur.ss && qe>=cur.se) {
            List<Integer> list = cur.ar;
            return list.size()-lowerBound(list, val)-1;
        }
        
        int m = (cur.ss + cur.se)/2;
        return query(cur.lc, qs, qe, val) + query(cur.rc, qs, qe, val);
    }
    
    public int lowerBound(List<Integer> A, int val) {
        int lo=0, hi=A.size()-1;
        int ans=-1;
        while(lo<=hi) {
            int m = lo + (hi-lo)/2;
            if(A.get(m)<val) {
                ans = m;
                lo = m+1;
            }
            else
                hi = m-1;
        }
        return ans;
    }

    public int upperBound(List<Integer> A, int val) {
        int lo=0, hi=A.size()-1;
        int ans=hi+1;
        while(lo<=hi) {
            int m = lo + (hi-lo)/2;
            if(A.get(m)>=val) {
                ans = m;
                hi = m-1;
            }
            else
                lo = m+1;
        }
        return ans;
    }
}
