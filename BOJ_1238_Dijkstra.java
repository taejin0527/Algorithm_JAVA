package d0927;

import java.io.*;
import java.util.PriorityQueue;

/**
 * FileName : Main.java
 * Project : JAVA8_e
 *
 * Description : BOJ 1238 - 파티
 * Link : https://www.acmicpc.net/problem/1238
 *
 * Created by AoN on 27/09/2020.
 */

public class BOJ_1238_Dijkstra {

    static int N, M, X;
    static int map[][], revMap[][];
    static int dist[], revDist[];

    static final int INF = (int) 1e5+1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line[] = br.readLine().split(" ");

        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        X = Integer.parseInt(line[2]) - 1;

        map = new int[N][N]; revMap = new int[N][N];
        dist = new int[N]; revDist = new int[N];

        int i = 0;
        for(i=0; i<N; ++i){
            dist[i] = revDist[i] = INF;
            for(int j=0; j<N; ++j)
                map[i][j] = revMap[i][j] = INF;
        }

        for(i=0; i<M; ++i){
            line = br.readLine().split(" ");

            int start = Integer.parseInt(line[0])-1;
            int end = Integer.parseInt(line[1])-1;
            int time = Integer.parseInt(line[2]);

            map[start][end] = revMap[end][start] = time;
        }

        dijkstra(X, map, dist); //목적지에서 각 마을로 가는 최단 거리 탐색
        dijkstra(X, revMap, revDist); //각 마을에서 목적지로 돌아오는 최단 거리 탐색

        int maxDist = 0;
        for(i=0; i<N; ++i)
            maxDist = Math.max(maxDist, dist[i] + revDist[i]);

        System.out.println(maxDist);
        br.close();
    }

    private static void dijkstra(int start, int map[][], int dist[]){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(start);
        map[start][start] = 0;
        dist[start] = 0;

        while(!pq.isEmpty()){
            int x = pq.poll();
            for(int i=0; i<N; ++i)
                if(dist[i] > map[x][i] + dist[x]){
                    dist[i] = map[x][i] + dist[x];
                    pq.offer(i);
                }
        }
    }
}