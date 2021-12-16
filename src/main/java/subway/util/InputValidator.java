package subway.util;

public class InputValidator {
	public static void isValidMainCommend(String mainCommend) {
		if (!(mainCommend.equals("1") || mainCommend.equals("Q"))) {
			throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력입니다.");
		}
	}

	public static void isValidMenuCommend(String menuCommend) {
		if (!(menuCommend.equals("1") || menuCommend.equals("2") || menuCommend.equals("B"))) {
			throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력입니다.");
		}
	}
}
