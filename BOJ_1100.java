import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 21 
 * @author : "AoN"

 * @Description : BOJ 1100 - 하얀 칸
 * @Link : https://www.acmicpc.net/problem/1100
 * 
 */

public class BOJ_1100 {
	
	static int board[][] = new int[8][8];
	static int chess[][] = new int[8][8];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int cnt = 0;
		for(int i=0; i<8; ++i) {
			String row = br.readLine();
			
			for(int j=0; j<8; ++j) {
				board[i][j] = (i+j)%2==0 ? 0 : 1;
				chess[i][j] = row.charAt(j) == '.' ? 0 : 1;
				
				if(board[i][j] == 0 && chess[i][j] == 1) ++cnt;
			}
		}
		
		System.out.println(cnt);
		br.close();
	}
}
