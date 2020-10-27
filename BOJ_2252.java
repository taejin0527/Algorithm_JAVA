import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 27 
 * @author : "AoN"

 * @Description : BOJ 2252 - 줄 세우기
 * @Link : https://www.acmicpc.net/problem/2252
 * 
 */

public class BOJ_2252 {
	
	static int N, M;
	static List<Integer> graph[];
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		
		int indegree[] = new int[N+1];
		graph = new ArrayList[N+1];
		for(int i=1; i<=N; ++i) graph[i] = new ArrayList<>();
		
		for(int i=0; i<M; ++i) {
			String[] ab = br.readLine().split(" ");
			int a = Integer.parseInt(ab[0]);
			int b = Integer.parseInt(ab[1]);
			
			graph[a].add(b);
			++indegree[b];
		}
		
		// Topological sort
		Queue<Integer> q = new LinkedList<>();

		for(int i=1; i<=N; ++i) {
			if(indegree[i]==0) 
				q.add(i);
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			System.out.print(cur + " ");
			for(int i=0; i<graph[cur].size(); ++i) {
				int nxt = graph[cur].get(i);
				
				--indegree[nxt];
				if(indegree[nxt]==0)
					q.add(nxt);
			}
		}
		
		br.close();
	}
}
