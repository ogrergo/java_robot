package donnees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;


public class Astar {

	
	public static List<Direction> getShortestPath(Case start, Case goal, Carte carte, Robot r) {
			Set<Node> closed_set = new HashSet<Node>();
			Queue<Node> open_set = new PriorityQueue<Node>();
			Map<Case, Node> map = new HashMap<Case, Node>();
			Node current = new Node(start);
			Node first = current;

			map.put(start, current);
			
			open_set.add(current);
			current.g_score = 0;
			current.f_score = carte.DistanceVolOiseau(start, goal);
			

			
			while(!open_set.isEmpty()) {
				current = open_set.remove();
				if(current.cell == goal) {
					ArrayList<Direction> list = new ArrayList<Direction>();
					while(current != first) {
						list.add(0,carte.getDirection(current.previous.cell, current.cell));
						current = current.previous;
					}
					return list;
				}
				closed_set.add(current);
				for(Case v : carte.caseVoisine(r, current.cell)) {
					Node voisin = map.get(v);
					if(voisin == null) {
						voisin = new Node(v);
						map.put(v, voisin);
					} else {
						if(closed_set.contains(voisin))
							continue;
					}
					double new_g_score = current.g_score + carte.tempsDeplacement(r, current.cell, v);
					
					if(!open_set.contains(voisin) || new_g_score < voisin.g_score) {
						voisin.previous = voisin;
						voisin.g_score = new_g_score;
						voisin.f_score = voisin.g_score + carte.DistanceVolOiseau(v, goal);
						if(!open_set.contains(voisin)) {
							open_set.add(voisin);
						}
					}
				}
			}
			
			return null;
		}
	
	
	private static class Node implements Comparable<Node>{
		public Node(Case start) {
			cell = start;
		}
		
		private Case cell;
		private double g_score;
		private double f_score;
		private Node previous = null;

		@Override
		public int compareTo(Node o) {
			return (int) (o.f_score - f_score);
		}
	}

	
}
