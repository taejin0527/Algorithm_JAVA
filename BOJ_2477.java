package IM;

import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 19 
 * @author : "AoN"

 * @Description : BOJ 2477 - 참외밭
 * @Link : https://www.acmicpc.net/problem/2477
 * 
 */

public class BOJ_2477 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int K = sc.nextInt();
		
		int point[] = new int[6];
		for(int i=0; i<6; ++i) {
			sc.nextInt();
			point[i] = sc.nextInt();
		}
		
		int wMax = 0, hMax = 0;
		for(int i=0; i<6; ++i) {
			if(i%2 == 0) {
				hMax = Math.max(hMax, point[i]);
			}else{
				wMax = Math.max(wMax, point[i]);
			}
		}
		
		int wMin = wMax, hMin = hMax;
		for(int i=0; i<6; ++i) {
			if(i%2 != 0) {
				if(hMax == point[(i+5)%6] + point[(i+1)%6]) hMin = point[i];
			}else{
				if(wMax == point[(i+5)%6] + point[(i+1)%6]) wMin = point[i];
			}
		}
		
		int ans = K * (wMax*hMax - wMin*hMin);
		System.out.println(ans);
		
		sc.close();
	}
}
