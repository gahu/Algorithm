package swexpert_lv3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 10059. 유효기간
 * @author bgg89
 *
 */
public class Validity {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			int num = Integer.parseInt(br.readLine());
			int prev = num / 100;
			int post = num % 100;
			String result = "";
			if(prev >= 1 && prev <= 12) {
				if(post >= 1 && post <= 12) result = "AMBIGUOUS";
				else result = "MMYY";
			} else {
				if(post >= 1 && post <= 12) result = "YYMM";
				else result = "NA";
			}
			
			System.out.println("#" + (i+1) + " " + result);
		}
	}

}
