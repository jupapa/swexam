package swexam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = null;
		// TODO Auto-generated method stub
		
		try {
			System.setIn(new FileInputStream(new File("/Users/actmember/eclipse-workspace/swexam/src/swexam/input.txt")));
			sc = new Scanner(System.in);
			int tc = sc.nextInt();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
