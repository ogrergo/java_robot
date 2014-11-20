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
			
			/**
			 * On commence par cr�er toutes les structures dont on aura besoin :
			 * closed_set contiendra les valeurs associ�es � toutes les cases. Il permet de ne pas
			 * recalculer plusieurs fois la m�me chose.
			 * open_set est la queue de priorit� qui d�fini quelle noeud doit �tre trait� ensuite.
			 * Le noeud de poids le plus faible est trait� en premier.
			 * map contient les cases de la carte et permet de d�terminer les voisins d'une case.
			 * current : noeud courant
			 * first : permet de savoir quand s'arr�ter sans aller chercher dans la carte.
			 * vitRobot : vitesse du robot
			 * current.g_score : cout de d�placement depuis start selon le meilleur chemin connu
			 * current.f_score : cout de d�placement depuis start jusqu'� goal estim� en passant 
			 * par current 
			 */
		
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
			
			while(!open_set.isEmpty()) { // tant qu'il reste des noeuds dans la file de priorit�
				current = open_set.remove(); // on traite l'�l�ment de priorit� la plus faible
				assert(current != null);
				
				// Si on est arriv�, on enregistre le chemin dans un tableau de 'ActionMove'
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
				
				// sinon, on ajoute current aux noeuds parcourus et on le traite.
				closed_set.add(current);
				// on parcourt tous les voisins de la case correspondant aux noeuds courants.
				for(Case v : carte.caseVoisineAccessible(r, current.cell)) {
					Node voisin = map.get(v);

					if(voisin == null) {
						voisin = new Node(v);
						map.put(v, voisin);
					} else {
						// si voisin est d�j� contenu dans closed_set, on ne le traite pas
						if(closed_set.contains(voisin)) 
							continue;
					}
					// on calcule le temps qu'il faut pour atteindre la case voisine v
					double new_g_score = current.g_score + carte.tempsDeplacement(r, current.cell, v);
					
					// si g_score est meilleur, on ajoute voisin � la liste et on met � jour ses valeurs.
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
