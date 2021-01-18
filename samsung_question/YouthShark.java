package samsung_question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class YouthShark {
	// 각 칸의 물고기는 1보다 크거나 같고 16보다 작거나 같다. 방향을 가지고 있다. 방향은 8가지 방향이다.
	// 각 물고기는 고유번호를 가지고 있으며, 겹치는 경우는 없다.
	// 상어는 처음에 (0, 0)의 물고기를 먹고 해당 위치로 들어간다.
	// 그리고 물고기가 이동
	// 물고기는 번호가 작은 물고기부터 이동한다.
	//   - 이동 가능한 칸 : 빈칸 또는 다른 물고기가 있는 칸(서로의 위치를 바꾼다.)
	//   - 이동 불가능 칸 : 상어가 있는 칸 또는 공간의 경계를 넘는 칸
	//   - 방향은 이동할 수 있는 칸을 찾을 때까지 45도 반시계 회전
	//   - 이동 할 수 있는 칸이 없으면 이동을 하지 않는다.
	// 이후 상어의 이동
	//   - 방향에 있는 칸으로 이동할 수 있는데, 한 번에 여러 개의 칸을 이동할 수 있다.
	//   - 물고기가 있는 칸으로 이동 시, 그 물고기를 먹고, 그 물고기의 방향을 가진다.
	//   - 이동 중 지나가는 칸의 물고기는 먹지 않는다. 빈 칸으로는 이동 불가
	//   - 상어가 이동할 수 있는 칸이 없으면 공간에서 벗어나 집으로 간다.
	// Input : 물고기의 정보가 a, b로 들어오고 a는 물고기의 번호, b는 방향을 의미
	//         위, 좌상, 좌, 좌하, 하, 우하, 우, 우상
	// Output : 상어가 먹을 수 있는 물고기 번호의 합의 최댓값
	
	static ArrayList<Fish> fishes;
	public static int ate;
	static Fish[][] aqua;
	static int[][] direction = {	// 0, 1, 2, 3, 4, 5, 6, 7, 8 --> 8가지 방향으로 1~8번
			{0, -1, -1, 0, 1, 1, 1, 0, -1},
			{0, 0, -1, -1, -1, 0, 1, 1, 1}
	};
	
	static void sharkMove(int eat, Fish shark) {
		if(ate < eat) ate = eat;
		
		fishesMove();
		
		for (int i = 1; i < 4; i++) {
			int nx = shark.x + direction[1][shark.dir] * i;
			int ny = shark.y + direction[0][shark.dir] * i;
			
			if(0 <= nx && nx < 4 && 0 <= ny && ny < 4 && aqua[nx][ny] != null && aqua[nx][ny].no > 0) {
				Fish tmp = aqua[nx][ny];
				Fish newShark = new Fish(-1, tmp.dir, tmp.x, tmp.y, true);
				tmp.isAlive = false;
				aqua[nx][ny] = newShark;
				
				sharkMove(eat + tmp.no, newShark);
			}
		}
	}
	
	static void fishesMove() {
		int fishesMax = fishes.size();
		int cnt = 0;
		while(cnt < fishesMax) {
			Fish fish = fishes.get(cnt);
			int dir = fish.dir;
			int x = fish.x;
			int y = fish.y;
			
			for(;;) {
				int nx = x + direction[1][dir];
				int ny = y + direction[0][dir];
				if(0 <= nx && nx < 4 && 0 <= ny && ny < 4) {
					if(aqua[nx][ny] == null) {	// 빈 칸일 경우
						aqua[nx][ny] = aqua[x][y];
						aqua[x][y] = null;
						break;
					} else if(aqua[nx][ny].no == -1) {	// 상어인 경우
						dir++;
						if(dir == 8) dir = 1;
					} else {					// 물고기가 있는 경우
						Fish tmp = aqua[nx][ny];
						aqua[nx][ny] = aqua[x][y];
						aqua[x][y] = tmp;
						break;
					}
				}
			}
			cnt++;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		fishes = new ArrayList<>();
		aqua = new Fish[4][4];
		Fish shark = null;
		for (int i = 0; i < 4; i++) {
			if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int no = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				if(i == 0 && j == 0) {
					shark = new Fish(-1, dir, i, j, true);
				}
				aqua[i][j] = new Fish(no, dir, i, j, true);
				fishes.add(aqua[i][j]);
			}
		}
		
		Collections.sort(fishes);
		
		ate = 0;	// 먹은 번호의 합 초기화
		sharkMove(0, shark);
		System.out.println(ate);
	}
	
	static class Fish implements Comparable<Fish>{
		int no, dir, x, y;
		boolean isAlive;
		public Fish(int no, int dir, int x, int y, boolean isAlive) {
			this.no = no;
			this.dir = dir;
			this.x = x;
			this.y = y;
			this.isAlive = isAlive;
		}
		
		public int getNo() {
			return no;
		}

		public void setNo(int no) {
			this.no = no;
		}

		@Override
	    public int compareTo(Fish s) {
	        if (this.no < s.getNo()) {
	            return -1;
	        } else if (this.no > s.getNo()) {
	            return 1;
	        }
	        return 0;
	    }
	}
}
