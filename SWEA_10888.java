import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @FileName : Solution.java
 * @Project : Algorithm
 * @Date : 2020. 10. 19 
 * @author : "AoN"

 * @Description : SWEA 10888. 음식배달
 * @Link : https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AXUqw9zKYaoDFASe
 * 
 */

public class SWEA_10888 {
	
	static int N, minDist;
	static int map[][];
	static ArrayList<Point> delivery, house;
	static boolean v[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; ++tc) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N+1][N+1];				
			delivery = new ArrayList<>();
			house = new ArrayList<>();
			
			for(int i=1; i<=N; ++i) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=1; j<=N; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					if(map[i][j] == 1) {
						house.add(new Point(i, j, 0));
					}
					if(map[i][j] > 1) {
						delivery.add(new Point(i, j, map[i][j]));
					}
				}
			}
			
			v = new boolean[delivery.size()];
			minDist = Integer.MAX_VALUE;
			for(int i=1; i<=delivery.size(); ++i) {
				comb(i, 0, 0);
			}
			
			out.append("#").append(tc).append(" ").append(minDist).append("\n");
		}
		
		System.out.println(out.toString());
		br.close();
	}
	
	static void comb(int n, int r, int cur) {
		if(n == r) {
			minDist = Math.min(minDist, manhattan());
			return;
		}
		
		for(int i=cur; i < delivery.size(); ++i) {
			v[i] = true;
			comb(n, r+1, i+1);
			v[i] = false;
		}
	}
	
	static int manhattan() {
		ArrayList<Point> s = new ArrayList<>();
		
		for(int i=0; i<delivery.size(); ++i) {
			if(v[i]) s.add(delivery.get(i));
		}
		
		int tempDist = 0;
		for(int i=0; i<house.size(); ++i) {
			int dist = Integer.MAX_VALUE;
			for(int j=0; j<s.size(); ++j) {
				int m = (Math.abs(s.get(j).x - house.get(i).x) + Math.abs(s.get(j).y - house.get(i).y));
				dist = Math.min(dist, m);
			}
			tempDist += dist;
		}
		
		for(int i=0; i<s.size(); ++i) {
			tempDist += s.get(i).cost;
		}
		
		return tempDist;
	}
	
	static class Point {
		int x, y, cost;
		
		public Point(int x, int y, int cost) {
			this.x = x; this.y = y; this.cost = cost;
		}
	}
}
