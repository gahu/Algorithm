package swexpert_lv3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 10200. 구독자 전쟁 
 * @author bgg89
 *
 */
public class SubscriberWar {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for (int i = 0; i < T; i++) {
			if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			int min = A + B - N;
			if(min < 0) min = 0;
			
			int max = A;
			if(max > B) max = B;
			System.out.println("#" + (i+1) + " " + max + " " + min);
		}
	}
}
