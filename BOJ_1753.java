package d0901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 1 
 * @author : "AoN"

 * @Description : BOJ 1753 - 최단경로
 * @Link : https://www.acmicpc.net/problem/1753
 * 
 */

public class BOJ_1753 {

	static int V, E, startV;
	static final int INF = 3000001;										// 가중치 최대 10, 정점 최대 300,000
	static HashMap<Integer, LinkedList<Node>> graph;
	static boolean[] visited;
	static int[] shortestDist;
	static PriorityQueue<Node> pQ;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		/**
		 * 주어진 그래프의 정보를 입력 받는다
		 */
		V = Integer.parseInt(st.nextToken());							// 1.정점(vertex)의 수
		E = Integer.parseInt(st.nextToken());							// 1.간선(edge)의 수
		startV = Integer.parseInt(br.readLine());						// 2.출발노드 번호
		
		graph = new HashMap<>();										// 그래프를 저장할 공간 확보
		for(int i=1; i<=V; ++i) {
			graph.put(i, new LinkedList<Node>());
		}
		
		for(int i=0; i<E; ++i) {										// 3.간선(E)의 수 만큼 그래프 정보를 입력 받는다
			st = new StringTokenizer(br.readLine());					// 한 줄을 읽어서 
			int from = Integer.parseInt(st.nextToken());				// 시작 노드
			int to = Integer.parseInt(st.nextToken());					// 도착 노드
			int weight = Integer.parseInt(st.nextToken());				// 가중치에 해당하는 값을 공백을 기준으로 분리하고
			
			graph.get(from).add(new Node(to, weight));					// 그래프 자료구조에 저장한다
		}
		
		/**
		 * 최단 경로를 구하기 위해 djikstra 알고리즘을 사용하고
		 * 이 알고리즘을 사용하기 위해 몇 가지 자료구조를 추가해서 사용한다
		 */
		visited = new boolean[V+1];										// 방문노드를 표시할 배열 공간 확보
		shortestDist = new int[V+1];									// 출발노드에서 각 노드까지의 경로길이를 저장할 배열 공간 확보
		Arrays.fill(shortestDist, INF);									// 배열을 무한대(INF)로 초기화
		pQ = new PriorityQueue<>();										// 최단경로 길이를 갖는 정점을 찾기위한 우선순위 큐
		
		shortestDist[startV] = 0;										// 출발노드는 경로 길이가 0
		pQ.add(new Node(startV, 0));
		
		/**
		 * 다익스트라 알고리즘을 통해 최단 경로를 greedy하게 구한다
		 */
		dijkstra();														// 다익스트라 알고리즘
		
		for (int i = 1; i <= V; i++) {
			if (shortestDist[i] == INF)
				System.out.println("INF");
			else
				System.out.println(shortestDist[i]);
		}
		
		br.close();
	}
	
	static void dijkstra() {
		while (!pQ.isEmpty()) {
			Node u = pQ.poll();
			visited[u.vertex] = true;

			for (Node adj : graph.get(u.vertex)) {
				if (!visited[adj.vertex] && shortestDist[adj.vertex] > shortestDist[u.vertex] + adj.weight) {
					shortestDist[adj.vertex] = shortestDist[u.vertex] + adj.weight;
					pQ.add(new Node(adj.vertex, shortestDist[adj.vertex]));

				}
			}
		}
	}
	
	static class Node implements Comparable<Node>{
		int vertex, weight;
		
		public Node(int edge, int weight) {
			this.vertex = edge; this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
}
