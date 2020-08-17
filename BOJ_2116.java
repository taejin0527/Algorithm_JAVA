/**
 * @FileName : DiceTower.java
 * @Project : ALGO
 * @Date : 2020. 8. 17
 * @author : "AoN"
 * @Description : BOJ 2116 - 주사위 쌓기
 * @링크 : https://www.acmicpc.net/problem/2116
 * 
 * 
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    int numOfDice, total, maxTotal = 0;
    int[][] dices;
    int[] planar = {-1, 6, 4, 5, 2, 3, 1};
    

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        Scanner sc = new Scanner(System.in);

        numOfDice = sc.nextInt();       // 주사위 개수
        dices = new int[numOfDice+1][7];  // 주사위 입력
        for (int i = 1; i <= numOfDice; ++i) {
            for (int j = 1; j <= 6; ++j) {
                dices[i][j] = sc.nextInt();
            }
        }

        // 주사위 쌓기 놀이는 아래에서부터 1번 주사위, 2번 주사위, 3번 주사위, … 의 순서로 쌓는 것이기 때문에
        // 1번 주사위만 정해지면 나머지는 맞춰지는 것이기 때문에 6번의 경우만 생각하면 된다
 
        for (int i = 1; i <= 6; ++i) {
            total = 0;
            buildDiceTower(i, 1);
            if (maxTotal < total) {
                maxTotal = total;
            }
        }

        System.out.println(maxTotal);

        sc.close();
    }

    public void buildDiceTower(int preTop, int height) {
        int bottom = dices[1][1], top = dices[1][planar[1]];
        int[] planeDice = {0, 1, 2, 3, 4, 5, 6};   

        if (height == numOfDice + 1) {
            return;
        }

        for (int i = 1; i <= 6; ++i) {
            if (dices[height][i] == preTop) {
                bottom = preTop;
                top = dices[height][planar[i]];
                break;
            }
        }

        planeDice[bottom] = 0;
        planeDice[top] = 0;
        
        Arrays.sort(planeDice);
        total += planeDice[6];
        
        buildDiceTower(top, height + 1);

    }
}