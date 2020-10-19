package IM;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 18 
 * @author : "AoN"

 * @Description : BOJ 2578 - 빙고
 * @Link :
 * 
 */

public class BOJ_2578 {
	
	static int board[][] = new int[5][5];
	static int host[] = new int[25];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int i=0; i<5; ++i) for(int j=0; j<5; ++j) board[i][j] = sc.nextInt();
		for(int i=0; i<25; ++i) host[i] = sc.nextInt();
		
		// 12개의 빙고
		@SuppressWarnings("unchecked")
		ArrayList<Integer> bingoSet[] = new ArrayList[12];
		for(int i=0; i<12; ++i) bingoSet[i] = new ArrayList<>();
		for(int k=0; k<5; ++k) {
			for(int i=0; i<5; ++i) {
				bingoSet[k].add(board[k][i]);
				bingoSet[k+5].add(board[i][k]);
			}
		}
		for(int i=0; i<5; ++i) {
			bingoSet[10].add(board[i][i]);
			bingoSet[11].add(board[i][4-i]);
		}
		
		
		for(int i=0; i<25; ++i) {
			int cnt = 0;
			for(int k =0; k<12; ++k) {
				if(bingoSet[k].contains(host[i])) {
					bingoSet[k].remove(bingoSet[k].indexOf(host[i]));
				}
				if(bingoSet[k].size() == 0) ++cnt;
				
			}

			if(cnt>=3) {
				System.out.println(i+1);
				break;
			}
		}
		
		sc.close();
	}
}
