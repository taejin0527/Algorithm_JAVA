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

 * @Description : 5656. [모의 SW 역량테스트] 벽돌 깨기
 * @Link : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRQm6qfL0DFAUo&categoryId=AWXRQm6qfL0DFAUo&categoryType=CODE&&&
 * 
 */

public class SWEA_5656 {
	
	static int N, W, H, ans;
	static int map[][], temp[][], selected[];
	static boolean v[][];
	static Queue<Brick> q;
	
	static final int dx[] = {-1, 1, 0, 0}, dy[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder out = new StringBuilder();
		
		for(int tc=1; tc<=T; ++tc) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			for(int i=0; i<H; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = Integer.MAX_VALUE;
			selected = new int[N];
			comb(0);
			
			out.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(out);
		br.close();
	}

	private static void comb(int cnt) {
		if(cnt == N) {
			temp = copyMap();
			
			for(int i=0; i<N; ++i) {
				v = new boolean[H][W];
				crush(selected[i]);
				fall();
			}
			
			int remain = 0;
			for(int i=0; i<H; ++i) {
				for(int j=0; j<W; ++j) {
					if(temp[i][j] > 0) remain++;
				}
			}
			
			ans = Math.min(ans,  remain);
			
			return;
		}
		
		for(int i=0; i<W; ++i) {
			selected[cnt] = i;
			comb(cnt+1);
		}
	}
	
	private static void fall() {
		Queue<Integer> tq = new LinkedList<>();
		
		for(int i=0; i<W; ++i) {
			for(int j=H-1; j >= 0; --j) {
				if(temp[j][i] > 0) tq.add(temp[j][i]);
			}
			
			for(int j=H-1; j >= 0; --j) {
				temp[j][i] = !tq.isEmpty() ? tq.poll() : 0;
			}
		}
	}

	private static void crush(int w) {
		q = new LinkedList<>();
		
		for(int h=0; h<H; ++h) {
			if(temp[h][w] > 0) {
				q.add(new Brick(h, w, temp[h][w]));
				break;
			}
		}
		
		while(!q.isEmpty()) {
			Brick cur = q.poll();
			
			for(int i=0; i<4; ++i) {
				for(int j=0; j<cur.r; ++j) {
					int nx = cur.x + dx[i]*j;
					int ny = cur.y + dy[i]*j;
					
					if(0 > nx || nx >= H || 0 > ny || ny >= W) continue;
					if(v[nx][ny]) continue;
					v[nx][ny] = true;
					q.add(new Brick(nx, ny, temp[nx][ny]));
					temp[nx][ny] = 0;
				}
			}
		}
	}

	private static int[][] copyMap() {
		int[][] temp = new int[H][W];
		
		for(int i=0; i<H; ++i) for(int j=0; j<W; ++j) temp[i][j] = map[i][j];
		return temp;
	}

	static class Brick {
		int x, y, r;
		public Brick(int x, int y, int r) {
			this.x = x; this.y = y; this.r = r;
		}
	}
}
