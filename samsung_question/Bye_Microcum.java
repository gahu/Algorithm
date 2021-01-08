package samsung_question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Bye_Microcum {
	// (6 <= R,C <= 50, 1<= T <= 1000) (-1 <= Arc <= 1000)
	// 공기청정기 위치는 -1, -1은 위아래 붙어있고, 가장 윗 행, 아랫 행과 두 칸이상 떨어져 있다.
	static int R, C, T, result;
	static int[][] A, B;
	static int[][] idx = {
			{-1, 0, 1, 0},
			{0, -1, 0, 1}
	};
	static int upR, upC, downR, downC;
	
	static void diffusion() {
		B = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(A[i][j] == -1) {
					B[i][j] = -1;
					continue;
				}
				int cnt = 0;
				if(A[i][j] != 0) {					
					for (int k = 0; k < 4; k++) {
						int nxR = i + idx[0][k];
						int nxC = j + idx[1][k];
						if(nxR < 0 || nxC < 0 || nxR >= R || nxC >= C || A[nxR][nxC] == -1) {
							continue;
						} else {
							B[nxR][nxC] += A[i][j] / 5;
							cnt++;
						}
					}
					B[i][j] += A[i][j] - ((A[i][j]/5)*cnt);
				}
			}
		}
		
		// 배열 복사
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				A[i][j] = B[i][j];
			}
		}
	}
	
	static void air_cleanning() {
		int temp, nextTemp, dir;
		
		int[][] up = {
				{0, -1, 0, 1},
				{1, 0, -1, 0},
		};
		int i = upR, j = upC + 1; dir = 0;
		nextTemp = A[i][j];
		A[i][j] = 0;
		while(true) {
			temp = nextTemp;
			int nR = i + up[0][dir];
			int nC = j + up[1][dir];
			if(nR < 0 || R <= nR || nC < 0 || C <= nC) {
				dir++;
				nR = i + up[0][dir];
				nC = j + up[1][dir];
			} else if(A[nR][nC] == -1) break;
			nextTemp = A[nR][nC];
			A[nR][nC] = temp;
			i = nR; j = nC;
		}
		
		int[][] down = {
				{0, 1, 0, -1},
				{1, 0, -1, 0},
		};
		i = downR; j = downC + 1; dir = 0;
		nextTemp = A[i][j];
		A[i][j] = 0;
		while(true) {
			temp = nextTemp;
			int nR = i + down[0][dir];
			int nC = j + down[1][dir];
			if(nR < 0 || R <= nR || nC < 0 || C <= nC) {
				dir++;
				nR = i + down[0][dir];
				nC = j + down[1][dir];
			} else if(A[nR][nC] == -1) break;
			nextTemp = A[nR][nC];
			A[nR][nC] = temp;
			i = nR; j = nC;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		A = new int[R][C];
		boolean air_condition = false;
		for (int i = 0; i < R; i++) {
			if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				if(A[i][j] == -1 && !air_condition) {
					upR = i; upC = j;
					downR = i + 1; downC = j;
					air_condition = true;
				}
			}
			
		}
		
		for (int i = 0; i < T; i++) {
			diffusion();
			air_cleanning();
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(A[i][j] == -1) continue;
				result += A[i][j];
			}
		}
		
		System.out.println(result);
	}
}
