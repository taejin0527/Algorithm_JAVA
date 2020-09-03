package d0830;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 8. 30 
 * @author : "AoN"

 * @Description : BOJ 1339 - 단어 수학
 * @Link : https://www.acmicpc.net/problem/1339
 * 
 * 수학적으로 생각하면 그리디한 방식으로 풀이가 가능하고, 혹시 모를 반례가 걱정되어 모든 경우를 따지고 싶다면 조합을 통한 풀이가 가능하다
 */

public class BOJ_1339 {
	/**
	 * Approach 1 : Greedy
	 * 
	 * ABC => 100A + 10B + C
	 * BCD => 100B + 10C + D
	 * 정렬하면,
	 * ABC + BCD = 110B + 100A + 10C + D
	 * 끝으로 앞에서 부터 큰 수를 넣으면 된다
	 */
	/*
	static int N;
	static int[] alpha = new int[26];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		for(int i=0; i<N; ++i) {
			char[] row = sc.next().toCharArray();
			int pos = (int) Math.pow(10,  row.length - 1);
			
			// 각 자리수를 배열에 할당한다
			for(int j=0; j<row.length; ++j) {
				alpha[row[j]-'A'] += pos;
				pos /= 10;
			}
		}
		
		Arrays.sort(alpha);
		
		int num = 9, ans = 0;
		for(int i=25; i>=0; --i) {
			if(alpha[i] == 0) break;
			
			ans += alpha[i] * num;
			--num;
		}
		
		System.out.println(ans);
		
		sc.close();
	}
	*/
	
	
	/**
	 * Approach 2 : Permutation (backtracking)
	 * 
	 * 모든 순열을 탐색하여 최대값을 구하는 방식
	 */
	static int N, max;
    static String[] words;
    static Map<Character, Integer> alphabet;
    static boolean[] visit;
    static int[] data;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        words = new String[N];

        alphabet = new HashMap<Character, Integer>();
        int count = 0;
        for (int i = 0; i < N; i++) {
            words[i] = sc.next();
            for (int j = 0; j < words[i].length(); j++) {
                if (!alphabet.containsKey(words[i].charAt(j))) {
                    alphabet.put(words[i].charAt(j), count++);
                }
            }
        }
        data = new int[alphabet.size()];
        visit = new boolean[10];
        max = Integer.MIN_VALUE;
        solve(0, 0);
        System.out.println(max);
    }

    private static void solve(int index, int depth) {
        if (depth == data.length) {
            check();
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (!visit[i]) {
                visit[i] = true;
                data[depth] = i;
                solve(i, depth + 1);
                visit[i] = false;
            }
        }
    }

    private static void check() {
        int ret = 0;
        for (int i = 0; i < words.length; i++) {
            int tmp = 0;
            for (int j = 0; j < words[i].length(); j++) {
                tmp += data[alphabet.get(words[i].charAt(j))];
                tmp *= 10;
            }
            ret += tmp / 10;
        }
        if (max < ret)
            max = ret;
    }
}
