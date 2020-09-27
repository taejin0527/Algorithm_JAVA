package d0927;

import java.io.*;
import java.util.StringTokenizer;

/**
 * FileName : Main.java
 * Project : JAVA8_e
 *
 * Description : BOJ 1238 - 파티
 * Link : https://www.acmicpc.net/problem/1238
 *
 * Created by AoN on 27/09/2020.
 */
public class BOJ_1238_Floyd_Warshall {

    static int N, M, X;
    static int graph[][];

    static final int INF = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;

        graph = new int[N][N];
        for(int i=0; i<N; ++i) for(int j=0; j<N; ++j) if(i!=j) graph[i][j] = INF;

        for(int i=0; i<M; ++i) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int time = Integer.parseInt(st.nextToken());

            // 단방향 그래프
            graph[start][end] = time;
        }

        for(int k=0; k<N; ++k) {
            for(int i=0; i<N; ++i) {
                for(int j=0; j<N; ++j) {
                    if(graph[i][j] > graph[i][k] + graph[k][j])
                        graph[i][j] = graph[i][k] + graph[k][j];
                }
            }
        }

        int maxTime = 0;
        for(int i=0; i<N; ++i) {
            maxTime = Math.max(maxTime, graph[i][X] + graph[X][i]);
        }

        System.out.println(maxTime);

        br.close();
    }
}
