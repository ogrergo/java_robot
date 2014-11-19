package donnees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import strategie.ActionMove;


public class Astar {

	
	public static List<ActionMove> getShortestPath(Case start, Case goal, Carte carte, Robot r) {
			Set<Node> closed_set = new HashSet<Node>();
			Queue<Node> open_set = new PriorityQueue<Node>();
			Map<Case, Node> map = new HashMap<Case, Node>();
			Node current = new Node(start);
			Node first = current;
			double vitRobot = r.getVitesse() / 3600;
			map.put(start, current);
			
			open_set.add(current);
			current.g_score = 0;
			current.f_score = carte.DistanceVolOiseau(start, goal) * vitRobot;
			
			while(!open_set.isEmpty()) {
				current = open_set.remove();
				assert(current != null);
				if(current.cell == goal) {
					ArrayList<ActionMove> list = new ArrayList<ActionMove>();
					System.out.println("fin algo");

					while(current != first) {
						list.add(0,
								new ActionMove(
										carte.tempsDeplacement(r, current.previous.cell, current.cell), 
										carte.getDirection(current.previous.cell, current.cell))
						);
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
						voisin.previous = current;
						voisin.g_score = new_g_score;
						voisin.f_score = voisin.g_score + carte.DistanceVolOiseau(v, goal) * vitRobot;
						
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
			return (int) (f_score - o.f_score);
		}
	}

}
