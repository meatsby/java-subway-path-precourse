package subway.domain;

import java.util.Arrays;
import java.util.List;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public class SubwayMap {
	private final WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph = new WeightedMultigraph(
		DefaultWeightedEdge.class);
	private final WeightedMultigraph<String, DefaultWeightedEdge> timeGraph = new WeightedMultigraph(
		DefaultWeightedEdge.class);
	private final List<String> stationNames = Arrays.asList("교대역", "강남역", "역삼역", "남부터미널역", "양재역", "매봉역", "양재시민의숲역",
		"수서역", "오금역");
	private final List<List<String>> lineStationList = Arrays.asList(
		Arrays.asList("교대역", "강남역", "역삼역"),
		Arrays.asList("교대역", "남부터미널역", "양재역", "매봉역"),
		Arrays.asList("강남역", "양재역", "양재시민의숲역"),
		Arrays.asList("수서역", "오금역"));
	private final List<List<Integer>> distanceList = Arrays.asList(
		Arrays.asList(2, 2),
		Arrays.asList(3, 6, 1),
		Arrays.asList(2, 10),
		Arrays.asList(2));
	private final List<List<Integer>> timeList = Arrays.asList(
		Arrays.asList(3, 3),
		Arrays.asList(2, 5, 1),
		Arrays.asList(8, 3),
		Arrays.asList(3));

	public SubwayMap() {
		initializeSubwayGraph();
		initializePathGraph();
	}

	private void initializeSubwayGraph() {
		for (String stationName : stationNames) {
			distanceGraph.addVertex(stationName);
			timeGraph.addVertex(stationName);
		}
	}

	private void initializePathGraph() {
		for (int i = 0; i < distanceList.size(); i++) {
			for (int j = 0; j < distanceList.get(i).size(); j++) {
				distanceGraph.setEdgeWeight(
					distanceGraph.addEdge(lineStationList.get(i).get(j), lineStationList.get(i).get(j + 1)),
					distanceList.get(i).get(j));
				timeGraph.setEdgeWeight(
					timeGraph.addEdge(lineStationList.get(i).get(j), lineStationList.get(i).get(j + 1)),
					timeList.get(i).get(j));
			}
		}
	}

	public List<String> searchShortestPath(List<String> stationNames) {
		DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(distanceGraph);
		try {
			return dijkstraShortestPath.getPath(stationNames.get(0), stationNames.get(1)).getVertexList();
		} catch (Exception e) {
			throw new IllegalArgumentException("[ERROR] 출발역과 도착역이 연결되어 있지 않습니다.");
		}
	}

	public List<String> searchFastestPath(List<String> stationNames) {
		DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(timeGraph);
		try {
			return dijkstraShortestPath.getPath(stationNames.get(0), stationNames.get(1)).getVertexList();
		} catch (Exception e) {
			throw new IllegalArgumentException("[ERROR] 출발역과 도착역이 연결되어 있지 않습니다.");
		}
	}

	public List<Integer> getPathInfo(List<String> shortestPath) {
		int distance = 0;
		int time = 0;
		for (int i = 0; i < shortestPath.size() - 1; i++) {
			distance += distanceGraph.getEdgeWeight(
				distanceGraph.getEdge(shortestPath.get(i), shortestPath.get(i + 1)));
			time += timeGraph.getEdgeWeight(timeGraph.getEdge(shortestPath.get(i), shortestPath.get(i + 1)));
		}
		return Arrays.asList(distance, time);
	}

	public List<String> getStationNames() {
		return stationNames;
	}
}
