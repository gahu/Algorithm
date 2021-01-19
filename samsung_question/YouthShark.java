package samsung_question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
	
	public static int ate;
	static int[][] direction = {	// 1, 2, 3, 4, 5, 6, 7, 8 --> 8가지 방향으로 1~8번
			{-1, -1, 0, 1, 1, 1, 0, -1}, // x
			{0, -1, -1, -1, 0, 1, 1, 1}, // y
	};
	
	static void sharkMove(int[][] arr, Shark shark, List<Fish> fishes) {
		if(ate < shark.ate) ate = shark.ate;
		
		fishesMove(arr, fishes);
		
		for (int i = 1; i < 4; i++) {
			int nx = shark.x + direction[0][shark.dir] * i;
			int ny = shark.y + direction[1][shark.dir] * i;
			
			if(0 <= nx && nx < 4 && 0 <= ny && ny < 4 && arr[nx][ny] > 0) {	// 잡아먹는다.
				int[][] copy = arrayCopy(arr);
				List<Fish> fishesCopy = copyFishes(fishes);
				
				copy[shark.x][shark.y] = 0;
				Fish tmp = fishesCopy.get(copy[nx][ny] - 1);
				Shark newShark = new Shark(shark.ate + tmp.no, tmp.dir, tmp.x, tmp.y);
				tmp.isAlive = false;
				copy[nx][ny] = -1;
				
				sharkMove(copy, newShark, fishesCopy);
			}
		}
	}
	
	static void fishesMove(int[][] aqua, List<Fish> fishes) {
		for(Fish fish : fishes) {
			if(!fish.isAlive) continue;
			
			for(int i = 0; i < 8; i++) {
				int direc = (fish.dir + i) % 8;
				int nx = fish.x + direction[0][direc];
				int ny = fish.y + direction[1][direc];
				if(0 <= nx && nx < 4 && 0 <= ny && ny < 4 && aqua[nx][ny] > -1) {
					aqua[fish.x][fish.y] = 0;
					
					if(aqua[nx][ny] == 0) {		// 빈 칸일 경우
						fish.x = nx;
						fish.y = ny;
					} else {					// 물고기가 있는 경우
						Fish temp = fishes.get(aqua[nx][ny] - 1);
	                    temp.x = fish.x;
	                    temp.y = fish.y;
	                    aqua[fish.x][fish.y] = temp.no;

	                    fish.x = nx;
	                    fish.y = ny;
					}
					
					aqua[nx][ny] = fish.no;
					fish.dir = direc;
					break;
				}
			}
		}
	}
	
	static int[][] arrayCopy(int[][] original) {
		int[][] copy = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				copy[i][j] = original[i][j];
			}
		}
		return copy;
	}
	
	static List<Fish> copyFishes(List<Fish> fishes) {
		List<Fish> temp = new ArrayList<>();
        fishes.forEach(e -> temp.add(new Fish(e.no, e.dir, e.x, e.y, e.isAlive)));
        return temp;
    }

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		List<Fish> fishes = new ArrayList<>();
		int[][] aqua = new int[4][4];
		Shark shark = null;
		for (int i = 0; i < 4; i++) {
			if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int no = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;
				aqua[i][j] = no;
				fishes.add(new Fish(no, dir, i, j, true));
				if(i == 0 && j == 0) {
					shark = new Shark(no, dir, i, j);
					aqua[0][0] = -1;
					ate = no;
					fishes.get(0).isAlive = false;
				}
			}
		}
		
		Collections.sort(fishes);
		
		sharkMove(aqua, shark, fishes);
		System.out.println(ate);
	}
	
	static class Shark {
		int ate, x, y, dir;
		public Shark(int ate, int dir, int x, int y) {
			this.ate = ate;
			this.dir = dir;
			this.x = x;
			this.y = y;
		}
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
