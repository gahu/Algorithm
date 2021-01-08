package swexpert_lv3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Income_inequality {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for (int i = 0; i < T; i++) {
			if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] income = new int[N];
			double avg = 0;
			if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				income[j] = Integer.parseInt(st.nextToken());
				avg += income[j];
			}
			avg = avg / N;
			int result = 0;
			for (int j = 0; j < N; j++) {
				if(income[j] <= avg) result++; 
			}
			
			System.out.println("#" + (i+1) + " " + result);
		}
	}
}
