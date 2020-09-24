package IM;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 18 
 * @author : "AoN"

 * @Description : BOJ 2309 - 일곱 난쟁이
 * @Link : https://www.acmicpc.net/problem/2309
 * 
 */

public class BOJ_2309 {
	
	static int dwarf[];
	static int diff;
	static boolean flag;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int sum = 0;
		dwarf = new int[10];
		for(int i=1; i<10; ++i) {
			dwarf[i] = sc.nextInt();
			sum += dwarf[i];
		}
		
		Arrays.sort(dwarf);
		
		diff = sum - 100;
		
		comb(0, 1, 0);
		
		sc.close();
	}
	
	static void comb(int cnt, int cur, int v) {
		if(flag) return;
		if(cnt == 2) {
			
			int sum = 0;
			
			for(int i=1; i<10; ++i) {
				if((v&1<<i) != 0) sum += dwarf[i];
			}

			if(sum == diff) {
				for(int i=1; i<10; ++i) {
					if((v&1<<i) != 0) continue;
					System.out.println(dwarf[i]);
				}
				flag = true;
			}
			return;
		}
		for(int i=cur; i<10; ++i) {
			if((v&1<<i) != 0) continue;
			comb(cnt+1, i, v|1<<i);
		}
	}
}
