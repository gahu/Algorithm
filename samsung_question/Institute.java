package samsung_question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Institute {
	// 벽을 3개 세운 뒤, 바이러스가 퍼질 수 없는 곳을 안전 영역이라고 한다.
	// 바이러스는 상, 하, 좌, 우로 퍼져 나간다.
	// 0은 빈칸, 1은 벽, 2는 바이러스
	static int N, M, safty, listSize;
	static ArrayList<Block> list, virusList;
	static Queue<Block> virus;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dif = {
			{0, 1, -1, 0},
			{1, 0, 0, -1},
	};
	
	static void virus(int cnt, int idx) {
		if(cnt == 3) {
			visited = new boolean[N][M];
			diffusion();
			int max = searchSafy();
			if(max > safty) safty = max;
			return;
		}
		
		if(listSize <= idx) return;
		
		Block tmp = list.get(idx);
		map[tmp.x][tmp.y] = 1;
		virus(cnt + 1, idx + 1);
		map[tmp.x][tmp.y] = 0;
		virus(cnt, idx + 1);
	}
	
	static void diffusion() {
		virus.clear();
		
		for (int i = 0; i < virusList.size(); i++) {
			virus.add(virusList.get(i));
		}
		
		while(!virus.isEmpty()) {
			Block tmp = virus.poll();
			visited[tmp.x][tmp.y] = true;
			for (int i = 0; i < 4; i++) {
				int nx = tmp.x + dif[0][i];
				int ny = tmp.y + dif[1][i];
				if(0 <= nx && nx < N && 0 <= ny && ny < M && map[nx][ny] == 0 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					virus.add(new Block(nx, ny));
				}
			}
		}
	}
	
	static int searchSafy() {
		int area = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0 && !visited[i][j]) area++;
			}
		}
		
		return area;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		list = new ArrayList<>();
		virusList = new ArrayList<>();
		virus = new LinkedList<Block>();
		for (int i = 0; i < N; i++) {
			if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					list.add(new Block(i, j));
					listSize++;
				} else if(map[i][j] == 2) virusList.add(new Block(i, j));
			}
		}
		
		safty = 0;
		virus(0, 0);
		
		System.out.println(safty);
	}
	
	public static class Block {
		int x, y;
		public Block(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
