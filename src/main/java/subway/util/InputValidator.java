package subway.util;

import java.util.List;

import subway.domain.SubwayMap;

public class InputValidator {
	private static final String PATH_SEARCH = "1";
	private static final String TERMINATION = "Q";
	private static final String SEARCH_BY_DISTANCE = "1";
	private static final String SEARCH_BY_TIME = "2";
	private static final String GO_BACK = "B";
	private static final String ERROR = "[ERROR] ";
	private static final String INPUT_ERROR = "유효하지 않은 입력입니다.";
	private static final String STATION_NAME_ERROR = "출발역과 도착역이 동일합니다.";
	private static final int DEPARTURE = 0;
	private static final int ARRIVAL = 1;

	public static void isValidMainCommend(String mainCommend) {
		if (!(mainCommend.equals(PATH_SEARCH) || mainCommend.equals(TERMINATION))) {
			throw new IllegalArgumentException(ERROR + INPUT_ERROR);
		}
	}

	public static void isValidMenuCommend(String menuCommend) {
		if (!(menuCommend.equals(SEARCH_BY_DISTANCE) || menuCommend.equals(SEARCH_BY_TIME) || menuCommend.equals(GO_BACK))) {
			throw new IllegalArgumentException(ERROR + INPUT_ERROR);
		}
	}

	public static void isValidStationNames(SubwayMap subwayMap, List<String> stationNames) {
		if (stationNames.get(DEPARTURE).equals(stationNames.get(ARRIVAL))) {
			throw new IllegalArgumentException(ERROR + STATION_NAME_ERROR);
		}
		for (String stationName : stationNames) {
			if (!subwayMap.getStationNames().contains(stationName)) {
				throw new IllegalArgumentException(ERROR + STATION_NAME_ERROR);
			}
		}
	}
}
