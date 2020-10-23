import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 23 
 * @author : "AoN"

 * @Description : BOJ 1929 - 소수 구하기
 * @Link : https://www.acmicpc.net/problem/1929
 * 
 */

public class BOJ_1929 {
    public static boolean[] primeNum;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int startNum = Integer.parseInt(st.nextToken());
        int endNum = Integer.parseInt(st.nextToken());
        
        primeNum = new boolean[endNum+1];
        getPrimeNum(); // 에라토스테네스의 체로 소수를 구한다.
        
        StringBuilder sb = new StringBuilder();
        for(int i = startNum; i <= endNum; i++) {
            if(!primeNum[i]) {
                sb.append(i + "\n");
            }
        }
        
        System.out.println(sb.toString());
        
        br.close();
    }
    
    public static void getPrimeNum() {
        primeNum[1] = true;
        
        for(int i= 2; i < primeNum.length; i++) {
            for(int j = 2; i*j < primeNum.length; j++) {
                primeNum[i*j] = true;
            }
        }
    }
}