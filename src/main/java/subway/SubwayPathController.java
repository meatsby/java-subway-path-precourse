package subway;

import java.util.List;

import subway.domain.SubwayMap;
import subway.util.InputValidator;
import subway.view.InputView;
import subway.view.OutputView;

public class SubwayPathController {
	public void run() {
		SubwayMap subwayMap = new SubwayMap();

		while (true) {
			OutputView.mainPage();
			String mainCommend = getMainCommend();
			if (mainCommend.equals("1")) {
				operatePathSearch(subwayMap);
			}
			if (mainCommend.equals("Q")) {
				return;
			}
		}
	}

	private void operatePathSearch(SubwayMap subwayMap) {
		OutputView.menuPage();
		String menuCommend = getMenuCommend();
		if (menuCommend.equals("1")) {
			searchShortestPath(subwayMap);
		}
		if (menuCommend.equals("2")) {
			searchFastestPath(subwayMap);
		}
	}

	private void searchShortestPath(SubwayMap subwayMap) {
		try {
			List<String> stationNames = InputView.stationNames();
			InputValidator.isValidStationNames(subwayMap, stationNames);
			List<String> shortestPath = subwayMap.searchShortestPath(stationNames);
			List<Integer> pathInfo = subwayMap.getPathInfo(shortestPath);
			OutputView.showPath(shortestPath, pathInfo);
		} catch (IllegalArgumentException e) {
			OutputView.showError(e.getMessage());
			operatePathSearch(subwayMap);
		}
	}

	private void searchFastestPath(SubwayMap subwayMap) {
		try {
			List<String> stationNames = InputView.stationNames();
			InputValidator.isValidStationNames(subwayMap, stationNames);
			List<String> fastestPath = subwayMap.searchFastestPath(stationNames);
			List<Integer> pathInfo = subwayMap.getPathInfo(fastestPath);
			OutputView.showPath(fastestPath, pathInfo);
		} catch (IllegalArgumentException e) {
			OutputView.showError(e.getMessage());
			operatePathSearch(subwayMap);
		}
	}

	private String getMainCommend() {
		String mainCommend = InputView.commend();
		try {
			InputValidator.isValidMainCommend(mainCommend);
			return mainCommend;
		} catch (IllegalArgumentException e) {
			OutputView.showError(e.getMessage());
			return getMainCommend();
		}
	}

	private String getMenuCommend() {
		String menuCommend = InputView.commend();
		try {
			InputValidator.isValidMenuCommend(menuCommend);
			return menuCommend;
		} catch (IllegalArgumentException e) {
			OutputView.showError(e.getMessage());
			return getMenuCommend();
		}
	}
}
