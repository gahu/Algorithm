package swexpert_lv3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 10912. 외로운 문자
 * @author bgg89
 *
 */
public class LonleyText {
	static String searchLonley(String text) {
		String result = "";
		char[] alpha = text.toCharArray();
		int[] alphabet = new int[26];
		
		for (int i = 0; i < alpha.length; i++) {
			int tmp = alpha[i] - 'a';
			alphabet[tmp]++;
		}
		
		for (int i = 0; i < 26; i++) {
			if(alphabet[i]%2 == 1) result += (char)(i + 'a');
		}
		
		if(result.equals("")) result = "Good";
		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= T; i++) {
			if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
			String text = st.nextToken();
			System.out.println("#" + i + " " + searchLonley(text));
		}
	}

}
