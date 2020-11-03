import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * @FileName : Solution.java
 * @Project : Algorithm
 * @Date : 2020. 11. 3
 * @author : "AoN"
 * 
 * @Description : 2105. [모의 SW 역량테스트] 디저트 카페
 * @Link : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5VwAr6APYDFAWu&categoryId=AV5VwAr6APYDFAWu&categoryType=CODE
 * 
 */

public class SWEA_2105 {
	static int N, ans, sx, sy;
	static int cafe[][];
	static boolean v[][];
	static HashSet<Integer> dessert;

	static final int dx[] = { 1, 1, -1, -1 }, dy[] = { 1, -1, -1, 1 }; // BR, BL, UL, UR 순

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder out = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			cafe = new int[N][N];
			ans = 0;

			for (int i = 0; i < N; ++i) {
				StringTokenizer st = new StringTokenizer(br.readLine());

				for (int j = 0; j < N; ++j) {
					cafe[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			v = new boolean[N][N];
			dessert = new HashSet<>();
			
			for (int i = 0; i < N - 1; ++i) {
				for (int j = 0; j < N - 1; ++j) {
					dessert.clear();

					sx = i;	sy = j;
					v[sx][sy] = true;
					dessert.add(cafe[i][j]);
					dfs(i, j, 0);
					v[sx][sy] = false;
					dessert.remove(cafe[i][j]);
				}
			}

			out.append('#').append(tc).append(' ').append(ans==0 ? -1 : ans).append('\n');
		}

		System.out.println(out);
		br.close();
	}

	private static void dfs(int x, int y, int dir) {
		for (int i = dir; i < 4; ++i) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (dessert.size()>3 && nx == sx && ny == sy) {
				ans = Math.max(ans, dessert.size()); 
				return;
			}
			
			if (0 > nx || nx >= N || 0 > ny || ny >= N)	continue;
			if (v[nx][ny]) continue;
			if (dessert.contains(cafe[nx][ny]))	continue;

			v[sx][sy] = true;
			dessert.add(cafe[nx][ny]);
			dfs(nx, ny, i);
			v[sx][sy] = false;
			dessert.remove(cafe[nx][ny]);
		}

	}
}
