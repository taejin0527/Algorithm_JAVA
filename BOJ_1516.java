import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 29 
 * @author : "AoN"

 * @Description : BOJ 1516 - 게임 개발
 * @Link : https://www.acmicpc.net/problem/1516
 * 
 */

public class BOJ_1516 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		@SuppressWarnings("unchecked")
		List<Integer> graph[] = new ArrayList[N+1];
		for(int i=1; i<=N; ++i) graph[i] = new ArrayList<>();
		int time[] = new int[N+1];
		int indegree[] = new int[N+1];
		
		for(int i=1; i<=N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int buildTime = Integer.parseInt(st.nextToken());
			int buildNo = Integer.parseInt(st.nextToken());
			
			time[i] = buildTime;
			while(true) {
				if(buildNo == -1) break;
				
				graph[buildNo].add(i);
				indegree[i]++;
				buildNo = Integer.parseInt(st.nextToken());
			}
		}
		
		int finalTime[] = new int[N+1];
		Queue<Integer> q = new LinkedList<>();
		for(int i=1; i<=N; ++i) 
			if(indegree[i] ==0) {
				q.add(i); finalTime[i] = time[i];
			}
		
		
		while(!q.isEmpty()) {
			int cur = q.poll();

			for(int i=0; i<graph[cur].size(); ++i) {
				int nxt = graph[cur].get(i);
				
				indegree[nxt]--;
				finalTime[nxt] = Math.max(finalTime[nxt], finalTime[cur] + time[nxt]);
				
				if(indegree[nxt]==0) q.add(nxt);
			}
		}
		
		for(int i=1; i<=N; ++i) System.out.println(finalTime[i]);
		br.close();
	}
}
