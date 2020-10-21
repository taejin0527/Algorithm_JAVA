import java.util.HashMap;
import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 20
 * @author : "AoN"
 * 
 * @Description : BOJ 1076 - 저항
 * @Link : https://www.acmicpc.net/problem/1076
 * 
 */

public class BOJ_1076 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String a = sc.next();
		String b = sc.next();
		String c = sc.next();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("black", "0,1");
		map.put("brown", "1,10");
		map.put("red", "2,100");
		map.put("orange", "3,1000");
		map.put("yellow", "4,10000");
		map.put("green", "5,100000");
		map.put("blue", "6,1000000");
		map.put("violet", "7,10000000");
		map.put("grey", "8,100000000");
		map.put("white", "9,1000000000");

		String[] get1 = map.get(a).split(",");
		String[] get2 = map.get(b).split(",");
		String[] get3 = map.get(c).split(",");

		Long result = Long.parseLong(get1[0] + get2[0]) * Long.parseLong(get3[1]);
		System.out.println(result);
		sc.close();
	}
}
