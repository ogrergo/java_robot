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

/**
 * <b><code>Astar</code> est la classe permettant de trouver le plus court chemin entre deux cases </b>
 * <p>
 * 
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public class Astar {

	/**
	 * Fonction static qui retourne la liste contenant les ActionMove à effectuer pour aller de la case
	 * start à la case goal en prenant le plus court chemin (en terme de temps)
	 * 
	 * @param start
	 * 				Case de départ
	 * @param goal
	 * 				Case d'arrivée
	 * @param carte
	 * 				Carte utilisée
	 * @param r
	 * 				Robot pour lequel on veut calculer le plus court chemin
	 * 
	 * @return la liste contenant les ActionMove du plus court chemin entre start et goal
	 */
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
				for(Case v : carte.caseVoisineAccessible(r, current.cell)) {
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

	/**
	 * Classe static implémentant les node utiles dans la fonction getShortestPath
	 * Cette classe permet de comparer deux noeuds
	 */
	private static class Node implements Comparable<Node>{
		
		/**
		 * Case associé au noeud
		 */
		private Case cell;
		private double g_score;
		private double f_score;

		private Node previous = null;
		
		/**
		 * Constructeur d'un node.
		 * 
		 * @param start
		 *            Case associé au Node
		 * 
		 */
		public Node(Case start) {
			cell = start;
		}
		
		@Override
		public int compareTo(Node o) {
			return (int) (f_score - o.f_score);
		}
	}

}
