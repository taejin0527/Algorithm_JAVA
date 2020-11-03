import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @FileName : Solution.java
 * @Project : Algorithm
 * @Date : 2020. 11. 2
 * @author : "AoN"
 * 
 * @Description : 1953. [모의 SW 역량테스트] 탈주범 검거
 * @Link : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpLlKAQ4DFAUq&categoryId=AV5PpLlKAQ4DFAUq&categoryType=CODE
 * 
 */

public class SWEA_1953 {
	
	static int N, M, R, C, L, ans;
	static int map[][];
	
	static final int dx[] = {-1, 0, 1, 0}, dy[] = {0, -1, 0, 1};	// 상좌하우 (반대 방향 체크를 위해 이렇게 배치함)
	static final boolean pipe[][] = {
			{true, true, true, true}, {true, false, true, false}, {false, true, false, true}, {true, false, false, true},
			{false, false, true, true}, {false, true, true, false},	{true, true, false, false}
	};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder out = new StringBuilder();
		for (int tc = 1; tc <= T; ++tc) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			
			for(int i=0; i<N; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			Queue<Pos> q = new LinkedList<>();
			boolean v[][] = new boolean[N][M];
			
			q.add(new Pos(R, C));
			v[R][C] = true;
			ans = 0;
			
			while(L-- > 0) {
				int size = q.size();
				
				while(size-- > 0) {
					Pos cur = q.poll();
					++ans;
					
					int curPipe = map[cur.x][cur.y] - 1;
					for(int i=0; i<4; ++i) {
						int nx = cur.x + dx[i];
						int ny = cur.y + dy[i];
						
						if(0>nx || nx>=N || 0>ny || ny>=M) continue;
						if(map[nx][ny]==0 || v[nx][ny]) continue;
						
						if(pipe[curPipe][i] && pipe[map[nx][ny] - 1][(i+2)%4]) {
							q.add(new Pos(nx, ny));
							v[nx][ny] = true;
						}
					} // end for
				} // end while(size)
			} // end while(L)

			out.append("#").append(tc).append(" ").append(ans).append("\n");
		}

		System.out.println(out);
		br.close();
	}
	
	static class Pos {
		int x, y;
		public Pos(int x, int y) {
			this.x = x; this.y = y;
		}
	}
}
