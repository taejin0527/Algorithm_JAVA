import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 26 
 * @author : "AoN"

 * @Description : BOJ 14405 - 피카츄
 * @Link : https://www.acmicpc.net/problem/14405
 * 
 */

public class BOJ_14405 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		if(str.matches("((pi|ka|chu)+)")) System.out.println("YES");
		else System.out.println("NO");
		
		sc.close();
	}
}
