package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502 {

    static int N, M, maxSafe;
    static int[][] lab, virtualLab;
    static Queue<int[]> virus = new LinkedList<>(), virusQ;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lab = new int[N][M];
        for(int i=0; i<N; ++i) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; ++j) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if(lab[i][j] == 2) {
                    virus.offer(new int[] {i,j});
                }
            }
        }

        simulation(0, 0);

        System.out.println(maxSafe);
        br.close();
    }

    static void simulation(int wall, int cur) {
        if(wall == 3) {
            virtualLab = Arrays.stream(lab).map(int[]::clone).toArray(int[][]::new);
            virusQ = new LinkedList<>(virus);
            spreadVirus();
            maxSafe = Math.max(maxSafe, countSafe());

            return;
        }

        for(int i=cur; i<N*M; ++i) {
            int x = i/M;
            int y = i%M;

            if(lab[x][y] == 0) {
                lab[x][y] = 1;
                simulation(wall + 1, i + 1);
                lab[x][y] = 0;
            }
        }
    }

    static void spreadVirus() {
        while(!virusQ.isEmpty()) {
            int[] cur = virusQ.poll();

            for(int i=0; i<4; ++i) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(0 > nx || nx >= N || 0 > ny || ny >= M) continue;
                if(virtualLab[nx][ny] != 0) continue;
                virtualLab[nx][ny] = 2;
                virusQ.offer(new int[] {nx, ny});
            }
        }
    }

    static int countSafe() {
        int cnt=0;
        for(int[] row : virtualLab) {
            for (int col : row) {
                if (col == 0) ++cnt;
            }
        }

        return cnt;
    }

}
