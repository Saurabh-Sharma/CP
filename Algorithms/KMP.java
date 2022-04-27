class KMP {
	public static void KMP(char[] s, int[] pi)  {
        	for(int i=1; i<s.length; i++) {
            	int j = pi[i-1];
            	while(j>0 && s[j]!=s[i])
                j = pi[j-1];
            	if(s[j]==s[i])
                	j++;
            	pi[i] = j;
        	}
    	}
}