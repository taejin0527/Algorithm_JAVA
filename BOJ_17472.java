package d0904;
import java.util.*;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 4 
 * @author : "AoN"

 * @Description : BOJ 17472 - 다리 만들기 2
 * @Link : https://www.acmicpc.net/problem/17472
 * 
 */

public class BOJ_17472 {
	
	static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x; this.y = y;
		}
	}
	
	private static int N, M, island, ans;
	private static int[][] map, graph;
	private static boolean[][] visited;
	private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
	private static final int INF = 6000;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 1) map[i][j] = -1;
			}
		}
		
		findIsland();
		makeGraph();
		connectIsland();	
		findMST();
		
		System.out.println(ans < INF ? ans : -1);
		sc.close();
	}
	
	private static void findIsland() {	
		visited = new boolean[N][M];
		
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(map[i][j] == -1) {
					++island;
					bfs(new Point(i,j));
				}
			}
		}
	}
	
	private static void makeGraph() {
		graph = new int[island+1][island+1];
		for(int[] row : graph) Arrays.fill(row, INF);
	}
	
	private static void bfs(Point loc) {
		Queue<Point> q = new LinkedList<>();
		
		visited[loc.x][loc.y] = true;
		map[loc.x][loc.y] = island; 
		q.add(loc);
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int i=0; i<4; ++i) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(0 > nx || nx >= N || 0 > ny || ny >= M) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny] == -1) {
					map[nx][ny] = island;
					visited[nx][ny] = true;
					q.offer(new Point(nx, ny));
				}
			}
		}
	}
	
	private static void connectIsland() {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(map[i][j] > 0) link(new Point(i,j), map[i][j]);
			}
		}
	}

	private static void link(Point loc, int num) {
		for(int i=0; i<4; ++i) {
			int dist = -1;
			int nx = loc.x, ny = loc.y;
			
			while(true) {
				nx += dx[i];
				ny += dy[i];
				++dist;
				
				if(0 > nx || nx >= N || 0 > ny || ny >= M || num == map[nx][ny]) break;
				if(map[nx][ny] > 0) {
					if(dist > 1 && graph[num][map[nx][ny]] > dist)
						graph[num][map[nx][ny]] = graph[map[nx][ny]][num] = dist;  
					break;
				}
			}
		}
	}
	
	private static void findMST() {
		boolean[] v = new boolean[island+1];
		int[] shortestDist = new int[island+1];
		Arrays.fill(shortestDist, INF);
		shortestDist[1] = 0;
		
		int cnt = 0;
		while(true) {
			int min = INF;
			int minNo = 0;
			
			for(int i=1; i<=island; ++i) {
				if(!v[i] && min > shortestDist[i]) {
					min = shortestDist[i];
					minNo = i;
				}
			}

			v[minNo] = true;
			if(++cnt == island) break;
			
			for(int i=1; i<=island; ++i) {
				if(!v[i] && shortestDist[i] > graph[minNo][i]) {
					shortestDist[i] = graph[minNo][i];
				}
			}
		}
		
		for(int i=1; i<=island; ++i) {
			ans += shortestDist[i];
		}
	}
}
