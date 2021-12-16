package subway.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
	private static final Scanner sc = new Scanner(System.in);

	public static String commend() {
		System.out.println();
		System.out.println("## 원하는 기능을 선택하세요.");
		return sc.nextLine();
	}

	public static List<String> stationNames() {
		List<String> stationNames = new ArrayList<>();

		System.out.println();
		System.out.println("## 출발역을 입력하세요.");
		stationNames.add(sc.nextLine());
		System.out.println();
		System.out.println("## 도착역을 입력하세요.");
		stationNames.add(sc.nextLine());

		return stationNames;
	}
}
