package samsung_question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MagicianShark_Tornado {
	/**
	 * Input : 첫째 줄에 격자의 크기 N이 주어진다. 둘째 줄부터 N개의 줄에는 격자의 각 칸에 있는 모래가 주어진다. r번째 줄에서 c번째 주어지는 정수는 A[r][c] 이다.
	 * Output : 격자의 밖으로 나간 모래의 양
	 * x가 y로 갈 때, y의 전체가 a 로 이동하면서 퍼짐
	 *      2
	 * 1    7   10
	 * x -> y    a 5
	 * 1    7   10
	 *      2
	 */
	public static int[][] grid;
	public static int N;
	public static long sand;
	public static int[][] cur = {
			{0, 1, 0, -1},
			{-1, 0, 1, 0}
	};
	
	public static void magic(int corner, int count, int size, int dir, int x, int y) {
		if(x == 0 && y == 0) return;
	
		int nx = x + cur[0][dir];
		int ny = y + cur[1][dir];
		
		wind(x, y, nx, ny, dir);
		
		count++;
		if(count == size) {
			corner++;
			dir++;
			dir %= 4;
			count = 0;
		}
		if(corner == 2) {
			corner = 0;
			size++;
		}
		
		magic(corner, count, size, dir, nx, ny);
	}
	
	public static void wind(int x, int y, int nx, int ny, int dir) {
		int temp = grid[nx][ny];
		grid[nx][ny] = 0;
		int p1 = (int)(temp * 0.01);
		int p2 = (int)(temp * 0.02);
		int p5 = (int)(temp * 0.05);
		int p7 = (int)(temp * 0.07);
		int p10 = (int)(temp * 0.1);
		int a = temp - 2 * (p1+p2+p7+p10) - p5;
		
		if(dir == 0 || dir == 2) {	// 좌, 우
			// 1%
			for (int i = 0; i < 2; i++) {
				int sx = x + cur[0][1 + 2 * i];
				int sy = y + cur[1][1 + 2 * i];
				if(!checkOutRange(sx, sy)) grid[sx][sy] += p1;
				else sand += p1;
			}
			// 2%
			for (int i = 0; i < 2; i++) {
				int sx = nx + cur[0][1 + 2 * i] * 2;
				int sy = ny + cur[1][1 + 2 * i] * 2;
				if(!checkOutRange(sx, sy)) grid[sx][sy] += p2;
				else sand += p2;
			}
			// 7%
			for (int i = 0; i < 2; i++) {
				int sx = nx + cur[0][1 + 2 * i];
				int sy = ny + cur[1][1 + 2 * i];
				if(!checkOutRange(sx, sy)) grid[sx][sy] += p7;
				else sand += p7;
			}
			// 10%
			for (int i = 0; i < 2; i++) {
				int sx = nx + cur[0][dir] + cur[0][1 + 2 * i];
				int sy = ny + cur[1][dir] + cur[1][1 + 2 * i];
				if(!checkOutRange(sx, sy)) grid[sx][sy] += p10;
				else sand += p10;
			}
			
			// 5%
			int sx = nx + cur[0][dir] * 2;
			int sy = ny + cur[1][dir] * 2;
			if(!checkOutRange(sx, sy)) grid[sx][sy] += p5;
			else sand += p5;
			
			// a
			sx = nx + cur[0][dir];
			sy = ny + cur[1][dir];
			if(!checkOutRange(sx, sy)) grid[sx][sy] += a;
			else sand += a;
		} else {	// 하, 상
			// 1%
			for (int i = 0; i < 2; i++) {
				int sx = x + cur[0][2 * i];
				int sy = y + cur[1][2 * i];
				if(!checkOutRange(sx, sy)) grid[sx][sy] += p1;
				else sand += p1;
			}
			// 2%
			for (int i = 0; i < 2; i++) {
				int sx = nx + cur[0][2 * i] * 2;
				int sy = ny + cur[1][2 * i] * 2;
				if(!checkOutRange(sx, sy)) grid[sx][sy] += p2;
				else sand += p2;
			}
			// 7%
			for (int i = 0; i < 2; i++) {
				int sx = nx + cur[0][2 * i];
				int sy = ny + cur[1][2 * i];
				if(!checkOutRange(sx, sy)) grid[sx][sy] += p7;
				else sand += p7;
			}
			// 10%
			for (int i = 0; i < 2; i++) {
				int sx = nx + cur[0][dir] + cur[0][2 * i];
				int sy = ny + cur[1][dir] + cur[1][2 * i];
				if(!checkOutRange(sx, sy)) grid[sx][sy] += p10;
				else sand += p10;
			}
			
			// 5%
			int sx = nx + cur[0][dir] * 2;
			int sy = ny + cur[1][dir] * 2;
			if(!checkOutRange(sx, sy)) grid[sx][sy] += p5;
			else sand += p5;
			
			// a
			sx = nx + cur[0][dir];
			sy = ny + cur[1][dir];
			if(!checkOutRange(sx, sy)) grid[sx][sy] += a;
			else sand += a;
		}
	}
	
	public static boolean checkOutRange(int x, int y) {
		return x < 0 || N <= x || y < 0 || N <= y;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		grid = new int[N][N];
		for (int i = 0; i < N; i++) {
			if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		sand = 0;
		magic(0, 0, 1, 0, N/2, N/2);
		
		System.out.println(sand);
	}

}
