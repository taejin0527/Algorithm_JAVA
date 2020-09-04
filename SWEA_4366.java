package d0904;

import java.util.Scanner;

/**
 * @FileName : Solution.java
 * @Project : Algorithm
 * @Date : 2020. 9. 4 
 * @author : "AoN"

 * @Description : SWEA 4366. 정식이의 은행업무
 * @Link : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWMeRLz6kC0DFAXd&categoryId=AWMeRLz6kC0DFAXd&categoryType=CODE&&&
 * 
 */

public class SWEA_4366 {
	private static int ans;
	private static String binary, ternary;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder output = new StringBuilder();
		
		int T = sc.nextInt();
		for(int tc=1; tc<=T; ++tc) {
			binary = sc.next();
			ternary = sc.next();

			predict();
			
			output.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(output);
		sc.close();
	}
	
	private static void predict() {
		char[] copyB, copyT;
		
		for(int i=0; i<binary.length(); ++i) {
			copyB = binary.toCharArray();
			for(int bi=0; bi<2; ++bi) {
				copyB[i] = (char) ('0' + bi);
				for(int j=0; j<ternary.length(); ++j) {
					copyT = ternary.toCharArray();
					for(int ti=0; ti<3; ++ti) {
						copyT[j] = (char) ('0' + ti);
						if(Integer.parseInt(String.valueOf(copyB), 2) == Integer.parseInt(String.valueOf(copyT), 3)) {
							ans = Integer.parseInt(String.valueOf(copyB), 2);
							return;
						}
					}
				}
			}
		}
		return;
	}
}
