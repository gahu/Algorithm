package samsung_question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 14501. 퇴사
 * @author bgg89
 * Input : N - 퇴사전까지 남은 기간, Ti - 상담을 완료하는데 걸리는 기간, Pi - 상담을 했을 때 받을 수 있는 금액
 * Output : 상담을 적절히 했을 때, 백준이가 얻을 수 있는 최대 수익
 *
 */

// 완전탐색 방식
public class Resignation {
	static int[] t, p;
	static int N, income;
	
	static void calcMaxIncome(int day, int cost) {
		if(day > N) {
			return;
		}
		
		income = income > cost ? income : cost;
		
		for (int i = day; i < N; i++) {
			calcMaxIncome(i + t[i], cost + p[i]);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		t = new int[N];
		p = new int[N];
		for (int i = 0; i < N; i++) {
			if (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
			t[i] = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		calcMaxIncome(0, 0);
		System.out.println(income);
	}
}

/** DP 방식
public class Resignation {
	static int[] t, p, dp;
	
	static void calcMaxIncome(int dDay) {
		int next;
		
		for (int i = dDay; i > 0; i--) {
	        next = i + t[i];
	        if (next > dDay + 1) {
	            dp[i] = dp[i + 1];
	        } else {
	            dp[i] = dp[i + 1] > dp[next] + p[i] ? dp[i + 1] : dp[next] + p[i];
	        }
	    }
		
		System.out.println(dp[1]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		t = new int[N+2];
		p = new int[N+2];
		dp = new int[N+2];
		for (int i = 1; i <= N; i++) {
			if (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
			t[i] = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		calcMaxIncome(N);
	}
}
*/