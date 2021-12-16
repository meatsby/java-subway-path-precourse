package subway.view;

import java.util.List;

public class OutputView {
	public static void mainPage() {
		System.out.println("## 메인 화면");
		System.out.println("1. 경로 조회");
		System.out.println("Q. 종료");
	}

	public static void menuPage() {
		System.out.println();
		System.out.println("## 경로 기준");
		System.out.println("1. 최단 거리");
		System.out.println("2. 최소 시간");
		System.out.println("B. 돌아가기");
	}

	public static void showPath(List<String> path, List<Integer> pathInfo) {
		System.out.println();
		System.out.println("## 조회 결과");
		System.out.println("[INFO] ---");
		System.out.printf("[INFO] 총 거리: %dkm%n", pathInfo.get(0));
		System.out.printf("[INFO] 총 소요 시간: %d분%n", pathInfo.get(1));
		System.out.println("[INFO] ---");
		for (String stationName : path) {
			System.out.printf("[INFO] %s%n", stationName);
		}
		System.out.println();
	}

	public static void showError(String message) {
		System.out.println();
		System.out.println(message);
	}
}
