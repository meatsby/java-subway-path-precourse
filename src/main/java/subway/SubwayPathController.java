package subway;

import java.util.List;
import java.util.Scanner;

public class SubwayPathController {
	private final Scanner sc;

	public SubwayPathController(Scanner sc) {
		this.sc = sc;
	}

	public void run() {
		SubwayMap subwayMap = new SubwayMap();

		while (true) {
			String mainCommend = operateMainCommend(sc);
			OutputView.printBlankLine();
			if (mainCommend.equals("1")) {
				String menuCommend = operateMenuCommend(sc);
				OutputView.printBlankLine();
				searchPath(subwayMap, menuCommend);
			}
			if (mainCommend.equals("Q")) {
				return;
			}
		}
	}

	private void searchPath(SubwayMap subwayMap, String menuCommend) {
		if (menuCommend.equals("1")) {
			List<String> stationNames = getStationNames(sc);
			List<String> shortestPath = operateDistanceSearch(subwayMap, stationNames);
			List<Integer> shortestDistanceInfo = subwayMap.getShortestDistanceInfo(shortestPath, stationNames);
			OutputView.showPath(shortestPath, shortestDistanceInfo);
		}
		if (menuCommend.equals("2")) {
			List<String> stationNames = getStationNames(sc);
			List<String> shortestTime = operateTimeSearch(subwayMap, stationNames);
			List<Integer> shortestTimeInfo = subwayMap.getShortestTimeInfo(shortestTime, stationNames);
			OutputView.showPath(shortestTime, shortestTimeInfo);
		}
	}

	private String operateMainCommend(Scanner sc) {
		String mainCommend = InputView.getMainCommend(sc);
		try {
			InputValidator.isValidMainCommend(mainCommend);
			return mainCommend;
		} catch (IllegalArgumentException e) {
			OutputView.showError(e.getMessage());
			return operateMainCommend(sc);
		}
	}

	private String operateMenuCommend(Scanner sc) {
		String menuCommend = InputView.getMenuCommend(sc);
		try {
			InputValidator.isValidMenuCommend(menuCommend);
			return menuCommend;
		} catch (IllegalArgumentException e) {
			OutputView.showError(e.getMessage());
			return operateMenuCommend(sc);
		}
	}

	private List<String> getStationNames(Scanner sc) {
		List<String> stationNames = InputView.getStationNames(sc);
		try {
			InputValidator.isValidStationNames(stationNames);
			return stationNames;
		} catch (IllegalArgumentException e) {
			OutputView.showError(e.getMessage());
			return getStationNames(sc);
		}
	}

	private List<String> operateDistanceSearch(SubwayMap subwayMap, List<String> stationNames) {
		try {
			return subwayMap.findShortestDistance(stationNames);
		} catch (IllegalArgumentException e) {
			OutputView.showError(e.getMessage());
			return getStationNames(sc);
		}
	}

	private List<String> operateTimeSearch(SubwayMap subwayMap, List<String> stationNames) {
		try {
			return subwayMap.findShortestTime(stationNames);
		} catch (IllegalArgumentException e) {
			OutputView.showError(e.getMessage());
			return getStationNames(sc);
		}
	}
}
