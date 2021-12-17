package subway.view;

import java.util.List;

public class OutputView {
	private static final String MAIN_MENU = "## 메인 화면";
	private static final String PATH_SEARCH = "1. 경로 조회";
	private static final String TERMINATION = "Q. 종료";
	private static final String SEARCH_MENU = "## 경로 기준";
	private static final String SEARCH_BY_DISTANCE = "1. 최단 거리";
	private static final String SEARCH_BY_TIME = "2. 최소 시간";
	private static final String GO_BACK = "B. 돌아가기";
	private static final String SEARCH_RESULT = "## 조회 결과";
	private static final String OUTPUT_TEMPLATE = "[INFO] ---";
	private static final String DISTANCE_TRAVELLED = "[INFO] 총 거리: %dkm%n";
	private static final String TIME_TAKEN = "[INFO] 총 소요 시간: %d분%n";
	private static final String STATION_NAMES = "[INFO] %s%n";

	public static void mainPage() {
		System.out.println(MAIN_MENU);
		System.out.println(PATH_SEARCH);
		System.out.println(TERMINATION);
	}

	public static void menuPage() {
		System.out.println();
		System.out.println(SEARCH_MENU);
		System.out.println(SEARCH_BY_DISTANCE);
		System.out.println(SEARCH_BY_TIME);
		System.out.println(GO_BACK);
	}

	public static void showPath(List<String> path, List<Integer> pathInfo) {
		System.out.println();
		System.out.println(SEARCH_RESULT);
		System.out.println(OUTPUT_TEMPLATE);
		System.out.printf(DISTANCE_TRAVELLED, pathInfo.get(0));
		System.out.printf(TIME_TAKEN, pathInfo.get(1));
		System.out.println(OUTPUT_TEMPLATE);
		for (String stationName : path) {
			System.out.printf(STATION_NAMES, stationName);
		}
		System.out.println();
	}

	public static void showError(String message) {
		System.out.println();
		System.out.println(message);
	}
}
