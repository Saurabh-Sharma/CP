class MergeSortTree {
    List<Integer>[] st;
    int n;
    MergeSortTree(int[] ar) {
        this.n = ar.length;
        st = new ArrayList[4*n];
        for(int i=0; i<4*n; i++)
            st[i] = new ArrayList<>();
        
        build(0, 0, n-1, ar);
    }
    
    public void build(int v, int ss, int se, int[] ar) {
        if(ss==se) 
            st[v].add(ar[ss]);
        else {
            int m = (ss+se)/2;
            build(2*v+1, ss, m, ar);
            build(2*v+2, m+1, se, ar);
            
            st[v] = merge(st[2*v+1], st[2*v+2]);
        }
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
        return query(0, 0, n-1, ql, qr, val);
    }
    
    public int query(int v, int ss, int se, int qs, int qe, int val) {
        if(se<qs || qe<ss) return 0;
        else if(qs<=ss && qe>=se) {
            List<Integer> list = st[v];
            return list.size()-lowerBound(list, val)-1;
        }
        
        int m = (ss + se)/2;
        return query(2*v+1, ss, m, qs, qe, val) + query(2*v+2, m+1, se, qs, qe, val);
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