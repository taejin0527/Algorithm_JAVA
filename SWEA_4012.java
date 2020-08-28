package d0828;

import java.util.Scanner;

/**
 * @FileName : Solution.java
 * @Project : Algorithm
 * @Date : 2020. 8. 28 
 * @author : "AoN"

 * @Description : 4012. [모의 SW 역량테스트] 요리사
 * @Link : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeUtVakTMDFAVH&categoryId=AWIeUtVakTMDFAVH&categoryType=CODE
 * 
 */

public class SWEA_4012  {
	
	private int N, minSum;
	private int[][] cookBook;
	private boolean[] picked;
	
	private void run() {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		StringBuffer output = new StringBuffer();
		for(int tc=1; tc<=T; ++tc) {
			N = sc.nextInt();
			
			cookBook = new int[N][N];
			picked = new boolean[N];
	
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					cookBook[i][j] = sc.nextInt();
				}
			}
			
			minSum=Integer.MAX_VALUE;
			comb(0, 0);
			
			output.append('#').append(tc).append(' ').append(minSum).append('\n');
		}
		
		System.out.println(output);
		sc.close();
	}
	
	private void comb(int cnt, int cur) {	
		if(cnt==N/2) {
			int sum = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(picked[i] && picked[j])
						sum += cookBook[i][j];
					if(!picked[i] && !picked[j])
						sum -= cookBook[i][j];
				}
			}
			minSum = Math.min(minSum, Math.abs(sum));
			
			return;
		}

		for(int i=cur; i<N; ++i) {
			picked[i] = true;
			comb(cnt+1, i+1);
			picked[i] = false;
		}
	}
	
	public static void main(String[] args) {
		new SWEA_4012().run();
	}
}
