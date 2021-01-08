package swexpert_lv3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 10993. 군주와 공화제
 * @author bgg89
 *
 */
public class unsolved_Monarchy_Republic {
	static int[][] city;
	// x, y, s, 위협하는도시수, 최대영향력도시, 최대영향력크기, 최대영향력도시개수
	// i가 j에 행사하는 영향력 si / (xj - xi)^2 + (yj - yi)^2
	// 군주제를 유지 K, 공화제를 유지 D, 다른 도시 체제를 따른다 -> 그 도시번호(1, 2, 3, 4....)
	public static void influence(int cnt, int tc) {
		int[] result = new int[cnt];
		
		for (int i = 0; i < cnt; i++) {
			for (int j = 0; j < cnt; j++) {
				if(i==j) continue;
				// j가 i에게 하는 영향력 계산
				int e = (int)(city[j][2] / Math.pow((city[i][0] - city[j][0]), 2) + Math.pow((city[i][1] - city[j][1]), 2));
				if(city[i][2] < e) {
					city[i][3]++;
					if(city[i][5] < e) {	// 최대영향력도시와 크기 저장
						city[i][4] = j;
						city[i][5] = e;
					}
				}
			}
		}
		
		for (int i = 0; i < cnt; i++) {
			if(city[i][3] == 0) result[i] += 'K';
			else {
				if(city[i][6] >= 2) result[i] += 'D';
				else {	// 최대 영향력 도시가 하나이면
					result[i] += city[i][4];
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cnt; i++) {
			sb.append((char)result[i] + " ");
		}
		
		System.out.println("#"+ tc + " " + sb.toString());
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int TC = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= TC; i++) {
			if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			city = new int[N][7];
			for (int j = 0; j < N; j++) {
				if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
				city[j][0] = Integer.parseInt(st.nextToken());
				city[j][1] = Integer.parseInt(st.nextToken());
				city[j][2] = Integer.parseInt(st.nextToken());
			}
			
			influence(N, i);
		}
	}
}
