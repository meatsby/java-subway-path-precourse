package subway.view;

import java.util.Scanner;

public class InputView {
	private static final Scanner sc = new Scanner(System.in);

	public static String commend() {
		System.out.println();
		System.out.println("## 원하는 기능을 선택하세요.");
		return sc.nextLine();
	}

	public static String departure() {
		System.out.println();
		System.out.println("## 출발역을 입력하세요.");
		return sc.nextLine();
	}

	public static String arrival() {
		System.out.println();
		System.out.println("## 도착역을 입력하세요.");
		return sc.nextLine();
	}
}
