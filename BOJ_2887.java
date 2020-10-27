import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 27 
 * @author : "AoN"

 * @Description : BOJ 2887 - 행성 터널
 * @Link : https://www.acmicpc.net/problem/2887
 * 
 */

public class BOJ_2887 {
	
	static int N, p[];
	static Pos planet[];
	static List<Node> graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		planet = new Pos[N];
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			planet[i] = new Pos(x, y, z, i);
		}
		
		graph = new ArrayList<>();
// 메모리 초과....
//		for(int i=0; i<N-1; ++i) {
//			Pos A = planet[i];
//			for(int j=i+1; j<N; ++j) {
//				Pos B = planet[j];
//				
//				int diff[] = {Math.abs(A.x - B.x), Math.abs(A.y - B.y), Math.abs(A.z - B.z)};
//				int dist = Arrays.stream(diff).reduce(Integer.MAX_VALUE, (a, b) -> a<b ? a : b);
//				
//				graph.add(new Node(i, j, dist));
//			}
//		}
		
		// 최소 간선만 추가!!!
		// x 기준
		Arrays.sort(planet, (a, b) -> a.x-b.x);
		for(int i=1; i<N; ++i) graph.add(new Node(planet[i-1].idx, planet[i].idx, Math.abs(planet[i-1].x - planet[i].x)));
		// y 기준
		Arrays.sort(planet, (a, b) -> a.y-b.y);
		for(int i=1; i<N; ++i) graph.add(new Node(planet[i-1].idx, planet[i].idx, Math.abs(planet[i-1].y - planet[i].y)));
		// z 기준
		Arrays.sort(planet, (a, b) -> a.z-b.z);
		for(int i=1; i<N; ++i) graph.add(new Node(planet[i-1].idx, planet[i].idx, Math.abs(planet[i-1].z - planet[i].z)));
		
		graph.sort(null);
		
		makeSet();
		
		int ans = 0, size = 0;
		for(Node node : graph) {
			if(find(node.start)!=find(node.end)) {
				union(node.start, node.end);
				ans += node.dist;
				if(size == N-1) break;
			}
		}
		
		System.out.println(ans);
		br.close();
	}
	
	static void makeSet() {
		p = new int[N];
		for(int i=0; i<N; ++i) p[i] = i;
	}
	
	static int find(int x) {
		if(p[x]==x) return x;
		else return p[x] = find(p[x]);
	}
	
	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x==y) return false;
		p[y] = x;
		return true;
	}
	
	static class Pos {
		int x, y, z;
		int idx;
		
		public Pos(int x, int y, int z, int idx) {
			this.x = x; this.y = y; this.z = z;
			this.idx = idx;
		}
	}
	
	static class Node implements Comparable<Node> {
		int start, end, dist;
		
		public Node(int start, int end, int dist) {
			this.start = start; this.end = end; this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return this.dist - o.dist;
		}
	}
}
