package subway.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
	private static final String MENU_INPUT_MESSAGE = "## 원하는 기능을 선택하세요.";
	private static final String DEPARTURE_INPUT_MESSAGE = "## 출발역을 입력하세요.";
	private static final String ARRIVAL_INPUT_MESSAGE = "## 도착역을 입력하세요.";
	private static final Scanner sc = new Scanner(System.in);

	public static String commend() {
		System.out.println();
		System.out.println(MENU_INPUT_MESSAGE);
		return sc.nextLine();
	}

	public static List<String> stationNames() {
		List<String> stationNames = new ArrayList<>();

		System.out.println();
		System.out.println(DEPARTURE_INPUT_MESSAGE);
		stationNames.add(sc.nextLine());
		System.out.println();
		System.out.println(ARRIVAL_INPUT_MESSAGE);
		stationNames.add(sc.nextLine());

		return stationNames;
	}
}
