package subway;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;

public class SubwayMap {
	private final List<String> lines = new ArrayList<>(Arrays.asList("2호선", "3호선", "신분당선"));
	private final List<String> stations = new ArrayList<>(
		Arrays.asList("교대역", "강남역", "역삼역", "남부터미널역", "양재역", "매봉역", "양재시민의숲역", "선릉역", "한티역"));
	private final WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph = new WeightedMultigraph<>(
		DefaultWeightedEdge.class);
	private final WeightedMultigraph<String, DefaultWeightedEdge> timeGraph = new WeightedMultigraph<>(
		DefaultWeightedEdge.class);
	private final DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath = new DijkstraShortestPath<>(
		distanceGraph);
	private final DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestTime = new DijkstraShortestPath<>(
		timeGraph);

	public SubwayMap() {
		initializeStations();
		initializeLines();
		initializeDistanceGraph();
		initializeTimeGraph();
	}

	private void initializeStations() {
		for (String stationName : stations) {
			StationRepository.addStation(new Station(stationName));
		}
	}

	private void initializeLines() {
		for (String lineName : lines) {
			LineRepository.addLine(new Line(lineName));
		}
	}

	private void initializeDistanceGraph() {
		for (Station station : StationRepository.stations()) {
			distanceGraph.addVertex(station.getName());
		}
		distanceGraph.addVertex("선릉역");
		distanceGraph.addVertex("한티역");
		distanceGraph.setEdgeWeight(distanceGraph.addEdge("교대역", "강남역"), 2);
		distanceGraph.setEdgeWeight(distanceGraph.addEdge("강남역", "역삼역"), 2);
		distanceGraph.setEdgeWeight(distanceGraph.addEdge("교대역", "남부터미널역"), 3);
		distanceGraph.setEdgeWeight(distanceGraph.addEdge("남부터미널역", "양재역"), 6);
		distanceGraph.setEdgeWeight(distanceGraph.addEdge("양재역", "매봉역"), 1);
		distanceGraph.setEdgeWeight(distanceGraph.addEdge("강남역", "양재역"), 2);
		distanceGraph.setEdgeWeight(distanceGraph.addEdge("선릉역", "한티역"), 10);
	}

	private void initializeTimeGraph() {
		for (Station station : StationRepository.stations()) {
			timeGraph.addVertex(station.getName());
		}
		timeGraph.addVertex("선릉역");
		timeGraph.addVertex("한티역");
		timeGraph.setEdgeWeight(timeGraph.addEdge("교대역", "강남역"), 3);
		timeGraph.setEdgeWeight(timeGraph.addEdge("강남역", "역삼역"), 3);
		timeGraph.setEdgeWeight(timeGraph.addEdge("교대역", "남부터미널역"), 2);
		timeGraph.setEdgeWeight(timeGraph.addEdge("남부터미널역", "양재역"), 5);
		timeGraph.setEdgeWeight(timeGraph.addEdge("양재역", "매봉역"), 1);
		timeGraph.setEdgeWeight(timeGraph.addEdge("강남역", "양재역"), 8);
		timeGraph.setEdgeWeight(timeGraph.addEdge("양재역", "양재시민의숲역"), 3);
		timeGraph.setEdgeWeight(timeGraph.addEdge("선릉역", "한티역"), 10);
	}

	public List<String> findShortestDistance(List<String> stationNames) {
		if (!distanceGraph.containsVertex(stationNames.get((0))) || !distanceGraph.containsVertex(
			stationNames.get((1)))) {
			throw new IllegalArgumentException("[ERROR] 해당역이 존재하지 않습니다.");
		}
		try {
			return dijkstraShortestPath.getPath(stationNames.get(0), stationNames.get(1)).getVertexList();
		} catch (NullPointerException e) {
			throw new IllegalArgumentException("[ERROR] 연결되어 있지 않은 역입니다.");
		}
	}

	public List<String> findShortestTime(List<String> stationNames) {
		if (!timeGraph.containsVertex(stationNames.get((0))) || !timeGraph.containsVertex(stationNames.get((1)))) {
			throw new IllegalArgumentException("[ERROR] 해당역이 존재하지 않습니다.");
		}
		try {
			return dijkstraShortestTime.getPath(stationNames.get(0), stationNames.get(1)).getVertexList();
		} catch (NullPointerException e) {
			throw new IllegalArgumentException("[ERROR] 연결되어 있지 않은 역입니다.");
		}
	}

	public List<Integer> getShortestDistanceInfo(List<String> shortestDistance, List<String> stationNames) {
		List<Integer> shortestDistanceInfo = new ArrayList<>();
		shortestDistanceInfo.add(findDistance(stationNames));
		shortestDistanceInfo.add(findTimeValue(shortestDistance));
		return shortestDistanceInfo;
	}

	public List<Integer> getShortestTimeInfo(List<String> shortestTime, List<String> stationNames) {
		List<Integer> shortestTimeInfo = new ArrayList<>();
		shortestTimeInfo.add(findDistanceValue(shortestTime));
		shortestTimeInfo.add(findTime(stationNames));
		return shortestTimeInfo;
	}

	public int findDistanceValue(List<String> path) {
		int distance = 0;
		for (int i = 0; i < path.size() - 1; i++) {
			distance += (int)distanceGraph.getEdgeWeight(distanceGraph.getEdge(path.get(i), path.get(i + 1)));
		}
		return distance;
	}

	public int findTimeValue(List<String> path) {
		int time = 0;
		for (int i = 0; i < path.size() - 1; i++) {
			time += (int)timeGraph.getEdgeWeight(timeGraph.getEdge(path.get(i), path.get(i + 1)));
		}
		return time;
	}

	public int findDistance(List<String> stationNames) {
		return (int)dijkstraShortestPath.getPathWeight(stationNames.get(0), stationNames.get(1));
	}

	public int findTime(List<String> stationNames) {
		return (int)dijkstraShortestTime.getPathWeight(stationNames.get(0), stationNames.get(1));
	}
}
