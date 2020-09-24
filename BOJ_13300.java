package IM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 24 
 * @author : "AoN"

 * @Description : BOJ 13300 - 방 배정 
 * @Link : https://www.acmicpc.net/problem/13300
 * 
 */

public class BOJ_13300 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int grade[][] = new int[7][2];
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			
			if(S == 0) grade[Y][0] += 1;
			else grade[Y][1] += 1;
		}
		
		int room = 0;
		for(int i=0; i<=6; ++i) {
			room += grade[i][0]/K;
			if(grade[i][0]%K != 0) ++room;
			room += grade[i][1]/K;
			if(grade[i][1]%K != 0) ++room;
			
		}
		
		System.out.println(room);
		
		br.close();
	}
}
