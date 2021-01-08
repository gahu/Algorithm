package swexpert_lv3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1206. [S/W 문제해결 기본] 1일차 - View
 * @author bgg89
 *
 */
public class SWsolution_1days_View {
	public static int searchView(int[] h) {
		int result = 0, t = h.length;
		
		for (int i = 2; i < t - 2; i++) {
			int fe = h[i] - h[i - 2];
			if(fe > (h[i] - h[i - 1])) fe = (h[i] - h[i - 1]);
			if(fe > (h[i] - h[i + 1])) fe = (h[i] - h[i + 1]);
			if(fe > (h[i] - h[i + 2])) fe = (h[i] - h[i + 2]);
			
			if(fe > 0) result += fe;
		}
		
		return result;
	}
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		//int T = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= 10; i++) {
			if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] height = new int[N];
			if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				height[j] = Integer.parseInt(st.nextToken());
			}
			
			sb.append("#" + i + " " + searchView(height) + "\n");
		}
		System.out.println(sb.toString());
	}
}
