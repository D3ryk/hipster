package es.usc.citius.hipster.examples;

import es.usc.citius.hipster.algorithm.Hipster;
import es.usc.citius.hipster.graph.GraphBuilder;
import es.usc.citius.hipster.graph.GraphSearchProblem;
import es.usc.citius.hipster.graph.HipsterGraph;
import es.usc.citius.hipster.model.problem.SearchProblem;
import es.usc.citius.hipster.util.Predicate;

public class UndirectedGraphSearchExample {

	public static void main(String[] args) {
		//Create simple weighted undirected graph
		HipsterGraph<String, Double> graph =
				GraphBuilder.<String, Double>create()
				.connect("A").to("C").withEdge(1d)
				.connect("C").to("B").withEdge(6d)
				.connect("B").to("A").withEdge(3d)
				.connect("A").to("F").withEdge(9d)
				.connect("E").to("D").withEdge(5d)
				.connect("B").to("D").withEdge(5d)
				.connect("C").to("E").withEdge(2d)
				.connect("A").to("F").withEdge(8d)
				.connect("E").to("D").withEdge(8d)
				.connect("D").to("H").withEdge(4d)
				.connect("F").to("G").withEdge(3d)
				.connect("G").to("H").withEdge(1d)
				.connect("H").to("I").withEdge(6d)
				.connect("I").to("D").withEdge(5d)
				.connect("H").to("Z").withEdge(2d)
				.connect("Z").to("G").withEdge(7d)
				.connect("Z").to("J").withEdge(3d)
				.connect("J").to("I").withEdge(7d)
				.connect("Z").to("L").withEdge(3d)
				.connect("L").to("K").withEdge(1d)
				.connect("K").to("G").withEdge(8d)
				.connect("M").to("Z").withEdge(6d)
				.connect("J").to("M").withEdge(2d)
				.createDirectedGraph();
				//.createUndirectedGraph();
		
		SearchProblem searchProblem =
				GraphSearchProblem.startingFrom("A")
				.goalAt("M")
				.in(graph)
				.takeCostsFromEdges()
				.buildBidirectional();
		
		System.out.println(
				Hipster.createBSStar(searchProblem).search(new Predicate() {
					@Override
					public boolean apply(Object input) {
						if (input == null)
							return true;
						return false;
					}
				})
		);
	}
}
