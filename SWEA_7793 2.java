package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_7793 {

    static int N, M, step;
    static boolean[][] v;
    static char[][] map;
    static Queue<int[]> S, D;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder output = new StringBuilder();
        for(int tc=1; tc<=T; ++tc) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new char[N][M];
            v = new boolean[N][M];
            S = new LinkedList<>();
            D = new LinkedList<>();
            for (int i = 0; i < N; ++i) {
                String row = br.readLine();
                for (int j = 0; j < M; ++j) {
                    map[i][j] = row.charAt(j);
                    if (map[i][j] == 'S') {// 수연이 위치
                        S.offer(new int[]{i, j, 0});
                        v[i][j] = true;
                    } else if (map[i][j] == '*') {// 악마 위치
                        D.offer(new int[]{i, j, 0});
                        v[i][j] = true;
                    }
                }
            }

            step = 0;
            bfs();

            output.append("#").append(tc).append(" ").append(step != 0 ? step : "GAME OVER").append("\n");
        }
        System.out.println(output);
        br.close();
    }

    static void bfs() {
        while(!S.isEmpty()) {
            int len = D.size();
            for(int i=0; i<len; ++i) {
                int[] cur = D.poll();
                for(int d=0; d<4; ++d) {
                    int nx = cur[0] + dx[d];
                    int ny = cur[1] + dy[d];

                    if(0 > nx || nx >= N || 0 > ny || ny >= M) continue;
                    if(v[nx][ny]) continue;
                    if(map[nx][ny] == 'X' || map[nx][ny] == 'D') continue;

                    map[nx][ny] = '*';
                    D.offer(new int[] {nx, ny, 0});
                    v[nx][ny] = true;
                }
            }

            len = S.size();
            for(int i=0; i<len; ++i) {
                int[] cur = S.poll();
                for(int d=0; d<4; ++d) {
                    int nx = cur[0] + dx[d];
                    int ny = cur[1] + dy[d];

                    if(0 > nx || nx >= N || 0 > ny || ny >= M) continue;
                    if(v[nx][ny]) continue;
                    if(map[nx][ny] == 'X' || map[nx][ny] == '*') continue;
                    if(map[nx][ny] =='D') { step = cur[2]+ 1;  return; }

                    map[nx][ny] = 'S';
                    S.offer(new int[] {nx, ny, cur[2] + 1});
                    v[nx][ny] = true;
                }
            }
        }
    }
}