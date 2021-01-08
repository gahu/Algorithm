package samsung_question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DragonCurve {
	// x, y는 드래곤 커브의 시작 점, d는 시작 방향, g는 세대 
	// 0 : x가 증가하는 방향(우), 1 : y가 감소하는 방향(상), 2 : x가 감소하는 방향(좌), 3 : y가 증가하는 방향(하)
	static int N;
	static int[][] curv;	// x, y, d, g
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		curv = new int[N][4];
		for (int i = 0; i < N; i++) {
			if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
			curv[i][0] = Integer.parseInt(st.nextToken());
			curv[i][1] = Integer.parseInt(st.nextToken());
			curv[i][2] = Integer.parseInt(st.nextToken());
			curv[i][3] = Integer.parseInt(st.nextToken());
		}
	}
}
