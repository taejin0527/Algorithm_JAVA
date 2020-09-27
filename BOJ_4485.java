package d0927;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * FileName : Main.java
 * Project : JAVA8_e
 * <p>
 * Description : BOJ 4485 - 녹색 옷 입은 애가 젤다지?
 * Link : https://www.acmicpc.net/problem/4485
 * <p>
 * Created by AoN on 27/09/2020.
 */

public class BOJ_4485 {

    static int N;
    static int map[][], dist[][];

    static final int INF = 125*125*9+1;
    static final int dx[] = {-1, 1, 0, 0}, dy[] = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int tc = 0;
        while(true){
            N = Integer.parseInt(br.readLine());
            if(N == 0) break;

            map = new int[N][N];
            dist = new int[N][N];
            for(int i=0; i<N; ++i) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; ++j) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = INF;
                }
            }

            out.append("Problem ").append(++tc).append(": ").append(dijkstra()).append("\n");
        }

        System.out.println(out);
        br.close();
    }

    static int dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(0, 0, map[0][0]));
        dist[0][0] = map[0][0];

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            for(int i=0; i<4; ++i) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(0 > nx || nx > N-1 || 0 > ny || ny > N-1) continue;
                if(dist[nx][ny] > dist[cur.x][cur.y] + map[nx][ny]) {
                    dist[nx][ny] = dist[cur.x][cur.y] + map[nx][ny];
                    pq.offer(new Node(nx, ny, map[nx][ny]));
                }
            }
        }

        return dist[N-1][N-1];
    }

    static class Node implements Comparable<Node> {
        int x, y, w;

        Node(int x, int y, int w) {
            this.x = x; this.y = y; this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}
