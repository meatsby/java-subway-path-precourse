package subway;

import java.util.List;

public class OutputView {
	public static void showPath(List<String> path, List<Integer> info) {
		System.out.println("## 조회 결과");
		System.out.println("[INFO] ---");
		System.out.printf("[INFO] 총 거리: %dkm%n", info.get(0));
		System.out.printf("[INFO] 총 소요 시간: %d분%n", info.get(1));
		System.out.println("[INFO] ---");
		for (String station : path) {
			System.out.println("[INFO] " + station);
		}
		System.out.println();
	}

	public static void showError(String message) {
		System.out.println(message);
		System.out.println();
	}

	public static void printBlankLine() {
		System.out.println();
	}
}
