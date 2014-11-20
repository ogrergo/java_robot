package donnees;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import strategie.ActionMove;

/**
 * <b><code>Carte</code> est la classe représentant la carte</b>
 * <p>
 * La carte est caractérisée par :
 * <ul>
 * <li>le tableau des cases qu'elle contient</li>
 * <li>la taille des cases qu'elle contient</li>
 * <li>l'ensemble des cases voisines à une case d'eau </li>
 * <li>l'ensemble des cases d'eau </li>
 * </ul>
 * </p>
 * 
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */

public class Carte {
	
	/**
	 * Le tableau des cases de la carte
	 */
	private Case[][] cases;
	
	/**
	 * La taille des cases de la carte 
	 */
	private int tailleCases;
	
	/**
	 * l'ensemble des cases voisines à une case d'eau 
	 */
	private HashSet<Case> plage = new HashSet<Case>();
	/**
	* l'ensemble des cases d'eau
	*/
	private HashSet<Case> water = new HashSet<Case>();
	
	/**
	 * Constructeur de la carte.
	 * 
	 * @param ligne
	 *            Nombre de lignes de la carte.
	 * @param colonne
	 *            Nombre de colonnes de la carte.
	 * @param _tailleCases
	 *            Taille des cases de la carte.
	 */
	public Carte(int ligne, int colonne, int taillecase) {
		cases = new Case[ligne][colonne];
		this.tailleCases = taillecase;
	}
	
	/**
	 * @see Carte#initPlageEtWater()
	 */
	public void init() {
		initPlageEtWater();
	}
	
	/**
	 * Fonctoin permettant de placer :
	 * <ul>
	 * <li>dans water toutes les cases contenant de l'eau</li>
	 * <li>dans plage toutes les cases voisines d'une cases contenant de l'eau</li>
	 * </ul>
	 */
	private void initPlageEtWater() {
		for(Case[] ct : cases) {
			for(Case c : ct) {
				if(c.getNature() == NatureTerrain.EAU) {
					water.add(c);
					for(Case p : getVoisin(c))
						if(p.getNature() != NatureTerrain.EAU)
							plage.add(p);
				}
			}
		}
	}
	
	
	public void setCase(int ligne, int colonne, Case c) {
		cases[ligne][colonne] = c;
	}
	
	public int getNbLignes() {
		return cases.length;
	}
	
	public int getNbColonnes() {
		return cases[0].length;
	}
	
	public int getTailleCases() {
		return tailleCases;
	}
	
	/**
	 * Permet de connaitre la direction pour aller de la case c1 à c2
	 * (c1 et c2 étant voisines)
	 * 
	 * @param c1
	 *            une case de la carte
	 * @param c2
	 *            une case de la carte voisine de c1
	 *
	 *@return la direction pour aller de c1 à c2
	 */
	public Direction getDirection(Case c1, Case c2) {
		if(c1.getColonne() == c2.getColonne()) {
			if(c1.getLigne() > c2.getLigne())
				return Direction.NORD;
			else
				return Direction.SUD;
		} else {
			if(c1.getColonne() > c2.getColonne())
				return Direction.OUEST;
			else
				return Direction.EST;
		}
	}
	
	/**
	 * Retourne la case de coordonnees (ligne, colonne)
	 * 
	 * @param ligne
	 *            la ligne de la case desiree
	 * @param colonne
	 *            la colonne de la case desiree
	 *
	 *@throws InvalidCaseException
	 *@return la case de coordonnees (ligne, colonne)
	 */
	public Case getCase(int ligne, int colonne) throws InvalidCaseException {
		if(ligne >= 0 && ligne < getNbLignes() && colonne >= 0 && colonne < getNbColonnes())
			return cases[ligne][colonne];
		else
			throw new InvalidCaseException("La coordonnee (" + ligne + ", " + colonne + ") n'est pas une coordonnee de case valide");
	}

	/**
	 * Retourne la case voisine de c dans la direction d
	 * 
	 * @param c
	 *            une case
	 * @param d
	 *            une direction
	 *
	 *@throws InvalidCaseException
	 *@return la case voisine de c dans la direction d
	 */
	public Case getCase(Case c, Direction d) throws InvalidCaseException {
		switch(d) {
		case EST:
			return getCase(c.getLigne(), c.getColonne() + 1);
		case NORD:
			return getCase(c.getLigne() - 1, c.getColonne());
		case OUEST:
			return getCase(c.getLigne(), c.getColonne() - 1);
		case SUD:
			return getCase(c.getLigne() + 1, c.getColonne());
		}
		return null;
	}
	
	/**
	 * Retourne la distance de la diagonale entre les cases départ et arrive
	 * (fonction utile pour le calcul du plus court chemin)
	 * 
	 * @param depart
	 *            la case de depart
	 * @param arrive
	 *            la case d'arrive
	 *
	 *@return la distance de la diagonale entre les cases départ et arrive
	 */
	public double DistanceVolOiseau(Case depart, Case arrive) {
		return (this.tailleCases * Math.sqrt(((depart.getColonne() - arrive.getColonne()) * (depart.getColonne() - arrive.getColonne())) + 
				((depart.getLigne() - arrive.getLigne()) * (depart.getLigne() - arrive.getLigne()))));
	
	}
	
	/**
	 * Test si une case est accessible par un robot
	 * 
	 * @param r
	 *            un robot
	 * @param arrive
	 *            la case d'arrive
	 *
	 *@return vrai si le robot r peut se déplacer sur la case c
	 */
	public boolean estCaseAccessible(Robot r, Case arrive) {
		return (r.getTempsDeplacementMilieu(arrive.getNature(), this) != Double.MAX_VALUE);
	}
	
	/**
	 * Retourne une collection contenant tous les voisins de la case c
	 * 
	 * @param c
	 *            une case
	 *
	 *@return la collection contenant tous les voisins de la case c
	 */
	public Collection<Case> getVoisin(Case c) {
		ArrayList<Case> res = new ArrayList<Case>(4);
		for (Direction d : Direction.values()) {
			try {
				res.add(this.getCase(c, d));
			} catch (InvalidCaseException e) {}
		}
		return res;
	}
	
	/**
	 * Retourne une collection contenant toutes les cases accessibles depuis le case c par le robot r
	 *
	 * @param r
	 *            un robot
	 * @param c
	 *            une case
	 *
	 *@return la collection contenant toutes les cases accessibles depuis c par le robot r
	 */
	public Collection<Case> caseVoisineAccessible(Robot r, Case c) {
		ArrayList<Case> res = new ArrayList<Case>(4);
		for (Case d : getVoisin(c)) {
			if (this.estCaseAccessible(r, d))
				res.add(d);
		}
		return res;
	}
	
	/**
	 * Test si la case c est voisine d'une case d'eau
	 *
	 * @param c
	 *            une case
	 *
	 *@return vrai si la case c est voisine avec une case d'eau, faux sinon
	 */
	public boolean isPlage(Case c) {
		for (Direction d : Direction.values()) {
			try {
				if (this.getCase(c, d).getNature() == NatureTerrain.EAU)
					return true;
				//Si le voisin ne fait pas partie de la carte, on ignore l'erreur
			} catch (InvalidCaseException e) {} 
		}
		return false;
	}
	
	/**
	 * Calcule le temps de deplacement mis par un robot pour se deplacer entre deux cases voisines
	 * @param r
	 *            un robot
	 * @param depart
	 *            la case de départ
	 * @param arrive
	 *            la case d'arrive
	 *
	 *@return vrai si la case c est voisine avec une case d'eau, faux sinon
	 */
	public double tempsDeplacement(Robot r, Case depart, Case arrive) {
		return (r.getTempsDeplacementMilieu(depart.getNature(), this) +
				r.getTempsDeplacementMilieu(arrive.getNature(), this)) / 2;
	}

	/**
	 * Retourne le nombre de cases appartenant à la diagonale entre les cases départ et arrive
	 * (fonction utile pour le calcul du plus court chemin)
	 * 
	 * @param depart
	 *            la case de depart
	 * @param arrive
	 *            la case d'arrive
	 *
	 *@return le nombre de cases appartenant à la diagonale entre les cases départ et arrive
	 */
	public int distanceNbCaseVolOiseau(Case dep, Case arr) {
		return Math.abs(dep.getColonne() - arr.getColonne()) + Math.abs(dep.getLigne() - arr.getLigne()); 
	}

	
	/**
	 * Trouve la case d'eau la plus proche à partir d'une certaine case pour un certain robot
	 * @param last_case
	 *            la case à partir de laquelle on part
	 * @param robot
	 *            le robot qui cherche la case d'eau
	 *
	 *@return la case d'eau la plus proche de last_case pour le robot robot
	 */
	public Case findNearestWater(Case last_case, Robot robot) {
		Collection<Case> target;
		//Selon le type de robot, le remplissage ne se fait pas de la meme façon
		if(robot.seRemplieSurEau())
			target = water;
		else
			target = plage;
		int min = Integer.MAX_VALUE;
		Case res = null;
		//Boucle permettant de trouver la case d'eau (ou voisine d'une case d'eau) la plus proche de last_case
		for(Case c : target) {
			if(distanceNbCaseVolOiseau(last_case, c) < min) {
				List<ActionMove> l = Astar.getShortestPath(last_case, c, this, robot);
				if(l == null)
					continue;
				if(l.size() < min) {
					res = c;
					min = l.size();
				}
			}
		}
		return res;
	}
	
	
}
