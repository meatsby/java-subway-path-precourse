package subway.domain;

import java.util.Arrays;
import java.util.List;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public class SubwayMap {
	private WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph = new WeightedMultigraph(
		DefaultWeightedEdge.class);
	private WeightedMultigraph<String, DefaultWeightedEdge> timeGraph = new WeightedMultigraph(
		DefaultWeightedEdge.class);
	private List<String> stationNames = Arrays.asList("교대역", "강남역", "역삼역", "남부터미널역", "양재역", "매봉역", "양재시민의숲역");
	private List<List<String>> lineStationList = Arrays.asList(
		Arrays.asList("교대역", "강남역", "역삼역"),
		Arrays.asList("교대역", "남부터미널역", "양재역", "매봉역"),
		Arrays.asList("강남역", "양재역", "양재시민의숲역"));
	private List<List<Integer>> distanceList = Arrays.asList(
		Arrays.asList(2, 2),
		Arrays.asList(3, 6, 1),
		Arrays.asList(2, 10));
	private List<List<Integer>> timeList = Arrays.asList(
		Arrays.asList(3, 3),
		Arrays.asList(2, 5, 1),
		Arrays.asList(8, 3));

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
}
