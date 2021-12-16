package subway;

import java.util.Scanner;

import subway.util.InputValidator;
import subway.view.InputView;
import subway.view.OutputView;

public class SubwayPathController {
	private final Scanner sc;

	public SubwayPathController(Scanner sc) {
		this.sc = sc;
	}

	public void run() {
		while (true) {
			OutputView.mainPage();
			String mainCommend = getMainCommend();
			if (mainCommend.equals("1")) {
				operatePathSearch();
			}
			if (mainCommend.equals("Q")) {
				return;
			}
		}
	}

	private void operatePathSearch() {
		OutputView.menuPage();
		String menuCommend = getMenuCommend();
		if (menuCommend.equals("1")) {
			searchShortestPath();
		}
		if (menuCommend.equals("2")) {
			searchFastestPath();
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

	private void searchShortestPath() {
		String departure = InputView.departure();
		String arrival = InputView.arrival();
		subwayMap.getShortestPath();
		OutputView.showPath();
	}

	private void searchFastestPath() {
		String departure = InputView.departure();
		String arrival = InputView.arrival();
		subwayMap.getFastestPath();
		OutputView.showPath();
	}
}
