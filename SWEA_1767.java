package d0902;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @FileName : Solution.java
 * @Project : Algorithm
 * @Date : 2020. 9. 2 
 * @author : "AoN"

 * @Description : SWEA 1767. [SW Test 샘플문제] 프로세서 연결하기
 * @Link : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV4suNtaXFEDFAUf&categoryId=AV4suNtaXFEDFAUf&categoryType=CODE
 * 
 */

public class SWEA_1767 {
	static int N, maxCnt, minLen, wireLen;
	static int[][] mexinos;
	static ArrayList<int[]> cores;
	static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder output = new StringBuilder();
		
		int T = sc.nextInt();
		for(int tc=1; tc<=T; ++tc) {
			maxCnt=0; minLen = 12*12+1;	
			N = sc.nextInt();
			mexinos = new int[N][N];
			cores = new ArrayList<>();
			
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					mexinos[i][j] = sc.nextInt();
					if(mexinos[i][j] == 1) cores.add(new int[] {i,j});
				}
			}
			
			linkCore(0, 0, 0);
			
			output.append("#").append(tc).append(" ").append(minLen).append("\n");
		}
		
		System.out.println(output);
		sc.close();
	}
	
	static void linkCore(int coreNum, int coreCnt, int sum) {
		if(coreNum == N) {	
			if(coreCnt > maxCnt) {
				maxCnt = coreCnt;
				minLen = sum;
			}else if(coreCnt == maxCnt) {
				minLen = Math.min(minLen, sum);
			}
			return;
		}
		
		for(int dir=0; dir<4; ++dir) {
			int[] cur = cores.get(coreNum);
			
			if(isConnectable(cur, dir)) {
				setWire(cur, dir, 2);
				linkCore(coreNum + 1, coreCnt + 1, sum + wireLen);
				setWire(cur, dir, 0);
			}
		}
		linkCore(coreNum + 1, coreCnt, sum);
	}
	
	static boolean isConnectable(int[] pos, int dir) {
		
		int x = pos[0], y = pos[1];
		
		
		while(true) {
			x += dx[dir]; y += dy[dir];
			
			if(0 > x || x >= N || 0 > y || y >= N) break;
			if(mexinos[x][y] != 0) return false;
		}
		return true;
	}
	
	static void setWire(int[] pos, int dir, int value) {
		int x = pos[0], y = pos[1];
		wireLen = 0;
		
		while(true) {
			x += dx[dir]; y += dy[dir];
			
			if(0 > x || x >= N || 0 > y || y >= N) break;
			mexinos[x][y] = value;
			++wireLen;
		}
	}
}
