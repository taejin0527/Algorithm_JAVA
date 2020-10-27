import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 27 
 * @author : "AoN"

 * @Description : BOJ 4386 - 별자리 만들기
 * @Link : https://www.acmicpc.net/problem/4386
 * 
 */

public class BOJ_4386 {
	
	static int n;
	static int p[];
	static Pos star[];
	static List<Node> graph;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		
		star = new Pos[n];
		graph = new ArrayList<>();
		for(int i=0; i<n; ++i) {
			double x = sc.nextFloat();
			double y = sc.nextFloat();
			star[i] = new Pos(x, y);
		}
		
		for(int i=0; i<n-1; ++i) {
			Pos A = star[i];
			for(int j=i+1; j<n; ++j) {
				Pos B = star[j];

				double dist = Math.sqrt(Math.pow(A.x - B.x, 2) + Math.pow(A.y - B.y, 2));
				graph.add(new Node(i, j, dist));
			}
		}
			
		graph.sort(null);
		p = new int[n];
		for(int i=0; i<n; ++i) p[i] = i;
		
		float ans = 0;
		int size = 0;
		for(Node node : graph) {
			if(union(node.start, node.end)) {
				ans += node.dist;
				if(++size == n-1) break;
			}
		}
		
		System.out.printf("%.2f", ans);

		sc.close();
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
		double x, y;
		
		public Pos(double x, double y) {
			this.x = x; this.y = y;
		}
	}
	static class Node implements Comparable<Node> {
		int start, end;
		double dist;
		
		public Node(int start, int end, double dist) {
			this.start = start; this.end = end; this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Double.compare(this.dist, o.dist);
		}
	}
}
