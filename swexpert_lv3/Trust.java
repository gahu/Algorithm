package swexpert_lv3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 10761. 신뢰
 * @author bgg89
 *
 */
public class Trust {
	// 오렌지와 블루는 서로 다른 복도에 있음
	// 매 1초마다, 로봇은 복도의 양 방향 중 하나로 1미터 걷거나,
	// 자기 위치에 있는 버튼을 누르거나, 아무것도 하지 않는다.
	// 두 로봇이 동시에 버튼을 누를 수 없다.
	// 4 B 2 O 1 O 2 B 4  -->  6
	static int[][] button;
	static int N;
	
	static int start() {
		int O = 1, B = 1;		// 현재 각 로봇 위치
		int Otime = 0, Btime = 0;
		for (int i = 0; i < N; i++) {
			if(button[0][i] != 0) {
				Btime += Math.abs(button[0][i] - B) + 1;
				B = button[0][i];
			}
			if(button[1][i] != 0) {
				Otime += Math.abs(button[1][i] - O) + 1;
				O = button[1][i];
			}
		}
		
		return Otime > Btime ? Otime : Btime;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int TC = Integer.parseInt(st.nextToken());
		for (int i = 0; i < TC; i++) {
			if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			button = new int[2][N];
			int Oidx = 0, Bidx = 0;
			for (int j = 0; j < N; j++) {
				char tmp = st.nextToken().charAt(0);
				if(tmp == 'B') button[0][Oidx++] = Integer.parseInt(st.nextToken());
				else button[1][Bidx++] = Integer.parseInt(st.nextToken());
			}
			
			System.out.println("#" + (i+1) + " " + start());
		}
	}
}
