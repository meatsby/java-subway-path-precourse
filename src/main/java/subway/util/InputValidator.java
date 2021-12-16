package subway.util;

import java.util.List;

import subway.domain.SubwayMap;

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

	public static void isValidStationNames(SubwayMap subwayMap, List<String> stationNames) {
		if (stationNames.get(0).equals(stationNames.get(1))) {
			throw new IllegalArgumentException("[ERROR] 출발역과 도착역이 동일합니다.");
		}
		for (String stationName : stationNames) {
			if (!subwayMap.getStationNames().contains(stationName)) {
				throw new IllegalArgumentException("[ERROR] 해당역이 존재하지 않습니다.");
			}
		}
	}
}
