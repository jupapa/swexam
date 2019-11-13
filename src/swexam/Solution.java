package swexam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = null;
		// TODO Auto-generated method stub
		// 상품은 A-Z
		
		try {
			System.setIn(new FileInputStream(new File("/Users/actmember/eclipse-workspace/swexam/src/swexam/input11.txt")));
			sc = new Scanner(System.in);
			int tc = sc.nextInt();
			System.out.println("tc:"+tc);
			
			char[] engineer = new char[5];
			
			for(int i=0; i<tc; i++) {
				int part_num = sc.nextInt(); // 부품수
				int line_num = sc.nextInt(); // 라인 수
				int engineer_num = sc.nextInt(); // 엔지니어 수
				
				engineer = sc.next().toCharArray();
				
				for(int t=0; t<engineer.length; t++)
					System.out.println(engineer[t]);
				System.out.println("-----");
				
				for(int k=0; k<line_num; k++) {
					
				}
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
