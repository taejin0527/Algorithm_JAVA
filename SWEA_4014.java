import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @FileName : Solution.java
 * @Project : Algorithm
 * @Date : 2020. 10. 29 
 * @author : "AoN"

 * @Description : 4014. [모의 SW 역량테스트] 활주로 건설
 * @Link : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeW7FakkUDFAVH&categoryId=AWIeW7FakkUDFAVH&categoryType=CODE
 * 
 */

public class SWEA_4014 {
	
	static int N, X, map[][], rmap[][], cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		for(int tc=1; tc<=T; ++tc) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			cnt = 0;
			map = new int[N][N];
			rmap = new int[N][N];
			
			for(int i=0; i<N; ++i) {
				st = new StringTokenizer(br.readLine());
				
				for(int j=0; j<N; ++j) {
					rmap[j][i] = map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			process();
			
			System.out.println("#"+tc+" "+cnt);
		}

		br.close();
	}
	
	private static void process() {
		for (int i=0; i<N; ++i) {
			if(makeRoad(map[i])) ++cnt;
			if(makeRoad(rmap[i])) ++cnt;
		}
	}

	private static boolean makeRoad(int[] road) {
		int beforeHeight = road[0];
		int size = 1;
		
		for(int j=1; j<N; ++j) {
			if(beforeHeight == road[j]) {
				++size;
			}else if(beforeHeight+1 == road[j]) {
				if(size<X) return false;
				beforeHeight++;
				size = 1;
			}else if(beforeHeight-1 == road[j]) {
				int count = 0;
				for(int k=j; k<N; ++k) {
					if(road[k] != beforeHeight-1) break;
					count++;
				}
				if(count<X) return false;
				j += X-1;
				beforeHeight--;
				size = 0;
			}else {
				return false;
			}
		}
		return true;
	}
}
