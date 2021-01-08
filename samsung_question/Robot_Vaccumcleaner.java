package samsung_question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Robot_Vaccumcleaner {
	// 3 <= N, M <= 50 || 0 - empty, 1 - wall
	// d : 0 - North, 1 - East, 2 - South, 3 - West
	// 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색
	// 로봇 청소기가 있는 칸의 상태는 항상 빈 칸이다.
	static int N, M, r, c, d;
	static int clean;
	static int[][] map;
	// 바라보고 있는 방향에 따라서 탐색하는 왼쪽의 좌표가 다르다.
	// 북 - (0,-1), 동 - (-1,0), 남 - (0,1), 서 - (1,0)
	static int[][] left = {
			{0, -1, 0, 1},
			{-1, 0, 1, 0},
	};
	
	static void cleanning() {
		int cnt = 0;
		while(true) {
			int nxR = r + left[0][d];
			int nxC = c + left[1][d];
			if(nxR < 0 || N <= nxR || nxC < 0 || M <= nxC || map[nxR][nxC] == -1 || map[nxR][nxC] == 1) {	// 왼쪽에 청소 할 공간이 없다면, 그 위치로 회전.
				if(cnt >= 4) {	// 네 방향 모두 청소가 되어 있거나 벽인경우 방향 그대로 뒤로 후진
					switch(d) {
						case 0: nxR = r + 1; nxC = c; 	  break;
						case 1: nxR = r; 	 nxC = c - 1; break;
						case 2: nxR = r - 1; nxC = c; 	  break;
						case 3: nxR = r; 	 nxC = c + 1; break;
					}
					if(map[nxR][nxC] == 1) {	// 네 방향 모두 청소가 됭 있고, 뒤가 벽인 경우 종료.
						break;
					}
					r = nxR; c = nxC;
					cnt = 0;
				} else {
					d -= 1;
					if(d < 0) d = 3;
					cnt++;
				}
			} else if(map[nxR][nxC] == 0) {	// 왼쪽 방향에 청소 공간이 존재한다면,
				d -= 1;
				if(d < 0) d = 3;
				map[nxR][nxC] = -1;
				clean++;
				cnt = 0;
				r = nxR; c = nxC;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		clean = 1; map[r][c] = -1;
		cleanning();
		
		System.out.println(clean);
	}
}
