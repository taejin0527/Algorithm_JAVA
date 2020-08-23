package Algorithm_JAVA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @FileName : Boggle1.java
 * @Project : ALGO
 * @Date : 2020. 8. 17
 * @author : "AoN"
 * @Description : 링크 : https://algospot.com/judge/problem/read/BOGGLE
 * 
 *              동적 계획법(DP)을 활용
 * 
 */


public class Boggle2 {
    char[][] boggle;
    boolean[][][] visited;
    String word;
	final int[] dx = { 1,1,1,0, 0,-1,-1,-1};
	final int[] dy = {-1,0,1,1,-1, 1, 0,-1};

    public static void main(String[] args) throws IOException {
        new Boggle2().solution();
    }

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int C = Integer.parseInt(br.readLine());	// C <= 50
		while(--C >= 0) {
            boggle = new char[5][5];

            for (int i = 0; i < 5; ++i) {
                String str = br.readLine();
                boggle[i] = str.toCharArray();
           }
           int N = Integer.parseInt(br.readLine());
           for(int i=0; i<N; ++i) {
                word = br.readLine();
                boolean flag = hasWord(); 

                System.out.println(word + (flag ? " YES": " NO"));
            }
        }

        br.close();
    }

    public boolean hasWord() {
        visited = new boolean[5][5][word.length()];

        for(int i = 0; i < 5; ++i) { 
            for(int j = 0; j < 5; ++j) { 
                if(dfs(i, j, 0)) {
                    return true; 
                }
            } 
        }

        return false;
    }

    public boolean dfs(int x, int y, int wordIdx) {
        
        visited[x][y][wordIdx] = true;

        // (base case) 1. 보글 보드의 글자가 단어와 일치하지 않으면 (중간) 실패
        if(boggle[x][y] != word.charAt(wordIdx)) 
            return false; 
            
        // (base case) 2. 모두 일치하고 단어의 길이가 같으면 (최종) 성공
        if(wordIdx == (word.length()-1)) {
            return true; 
        }

        // (recursive) 단어가 일치하고 있으면 재귀적으로 계속 탐색(8방향 델타 탐색)
        for(int i = 0; i < 8; ++i) {
             int nx = x + dx[i]; 
             int ny = y + dy[i]; 
             // 우선 보드 범위 (5x5)를 벗어나지 않고
             if(0<= nx && nx < 5 && 0<=ny && ny < 5) { 
                 // 이전에 탐색했던 단어라면 스킵(중복 제거)
                 if(visited[nx][ny][wordIdx+1]) continue;
                 if(dfs(nx, ny, wordIdx+1)) return true;
            }
        }

        return false;
    }
}