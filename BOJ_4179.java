package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4179 {
    static int R, C;
    static char[][] maze;
    static boolean[][] v;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static Queue<int[]> J, F;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        maze = new char[R][C]; v = new boolean[R][C];
        J = new LinkedList<>(); F = new LinkedList<>();
        for(int i=0; i<R; ++i) {
            String row = br.readLine();
            for(int j=0; j<C; ++j) {
                maze[i][j] = row.charAt(j);
                if(maze[i][j] == 'F') {
                    F.offer(new int[]{i, j}); v[i][j] = true;
                }
                if(maze[i][j] == 'J') {
                    J.offer(new int[]{i, j, 0}); v[i][j] = true;
                }
            }
        }

        int ans = BFS();
        System.out.println(ans > 0 ? ans : "IMPOSSIBLE");

        br.close();
    }

    static int BFS() {
        while(true) {
            if(J.isEmpty()) break;

            int size = F.size();
            for(int i=0; i<size; ++i) {
                int[] cur = F.poll();

                for(int j=0; j<4; ++j) {
                    int nx = cur[0] + dx[j];
                    int ny = cur[1] + dy[j];

                    if(0 > nx || nx >= R || 0 > ny || ny >= C) continue;
                    if(v[nx][ny] || maze[nx][ny] == '#') continue;
                    maze[nx][ny] = 'F';
                    F.offer(new int[]{nx, ny});
                    v[nx][ny] = true;
                }
            }

            size = J.size();
            for(int i=0; i<size; ++i) {
                int[] cur = J.poll();

                for(int j=0; j<4; ++j) {
                    int nx = cur[0] + dx[j];
                    int ny = cur[1] + dy[j];

                    if(0 > nx || nx >= R || 0 > ny || ny >= C) return cur[2]+1;
                    if(v[nx][ny] || maze[nx][ny] == '#' || maze[nx][ny] == 'F') continue;
                    maze[nx][ny] = 'J';
                    J.offer(new int[]{nx, ny, cur[2] + 1});
                    v[nx][ny] = true;
                }
            }
        }
        //throw new IllegalArgumentException("IMPOSSIBLE");
        return -1;
    }
}


