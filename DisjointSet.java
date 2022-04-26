import java.util.*;

class DisjointSet {
    int[] rank;
    int[] parent;
    DisjointSet(int n) {
        rank = new int[n];
        Arrays.setAll(parent = new int[n], i -> i);
    }
    
    public boolean union(int a, int b) {
        a = find(a); b = find(b);
        if(a==b) return false;
        if(rank[a]<=rank[b]) {
            parent[b] = a;
            if(rank[a]==rank[b]) rank[a]++;
        }
        else
            parent[a] = b;
        return true;
    }
    
    public int find(int v) {
        return v==parent[v]?v:(parent[v] = find(parent[v]));
    }
}