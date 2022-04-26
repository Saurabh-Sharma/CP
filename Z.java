class Z{
    public static void Z(int[] z, char[] s) {
        int l=0,r=0;
        for(int i=1; i<z.length; i++ ) {
            if( i<=r ) {
                z[i] = Math.min(r-i+1, z[i-l]);
            }
            while( i+z[i]<z.length && s[i+z[i]]==s[z[i]]) {
                ++z[i];
            }
            if( r< i+z[i]-1) {
                r = i+z[i]-1;
                l=i;
            }
        }
    }
}