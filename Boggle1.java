/**
 * @FileName : Boggle1.java
 * @Project : ALGO
 * @Date : 2020. 8. 9 
 * @author : "AoN"

 * @Description :
 * 링크 : https://algospot.com/judge/problem/read/BOGGLE
 * 
 * 완전탐색(Exhaustive Search)을 통해 문제를 해결하려하면 시간초과 뜬다
 * 동적 계획법(DP)을 활용하여 시간을 줄이는 방법을 고민해보자
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Boggle1 {

	static char[][] boggle = new char[5][5];
	static int[] dx = { 1,1,1,0, 0,-1,-1,-1};
	static int[] dy = {-1,0,1,1,-1, 1, 0,-1};
	
	public static void main(String[] args) throws IOException {
		Boggle1 main = new Boggle1();
		
		main.getInput();
	}
	
	public void getInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int C = Integer.parseInt(br.readLine());	// C <= 50
		
		for (int tc = 0; tc < C; ++tc) {
			// 5줄에 5글자로 보글 게임판이 주어짐(모두 대문자)
			for (int i = 0; i < 5; ++i) {
				 String str = br.readLine();
				 boggle[i] = str.toCharArray();
			}
			int N = Integer.parseInt(br.readLine());	// 1 <= N <= 10
			// N개의 단어가 주어짐
			for (int i = 0; i < N; ++i) {
				String word = br.readLine();
				boolean flag = false;
				for (int x = 0; x < 5; ++x) {
					for (int y = 0; y < 5; ++y) {
						if (!flag && hasWord(x, y, word)) {
							flag = true;
						}
					}
				}
				
				System.out.println(word + " " + (flag ? "YES" : "NO"));
			}
		}
		br.close();
	}
	
	static boolean hasWord(int x, int y, String word) {
		/***기저 조건***/
		// 1. 시작 위치가 범위 밖이면 실패
		if (x < 0 || x >=5 || y < 0 || y >=5) {
			return false;
		}
		// 2. 첫 글자가 일치하지 않으면 실패
		if (boggle[x][y] != word.charAt(0)) {
			return false;
		}
		// 3. 단어가 일치하면서 원하는 단어가 한 글자면 성공
		if (word.length() == 1) {
			return true;
		}
		
		/***델타 재귀***/ 
		for (int i = 0; i < 8; ++i) {
			int nX = x + dx[i];
			int nY = y + dy[i];
			
			if (hasWord(nX, nY, word.substring(1)))
				return true;
		}
		
		return false;
	}
}
	
/* input
1
URLPM
XPRET
GIAET
XTNZY
XOQRS
6
PRETTY
GIRL
REPEAT
KARA
PANDORA
GIAZAPX
*/
