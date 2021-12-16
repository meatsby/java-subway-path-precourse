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
}
