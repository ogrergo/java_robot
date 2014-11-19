package donnees;

import java.util.ArrayList;
import java.util.Collection;

public class Carte {
	
	private Case[][] cases;
	private int tailleCases;
	
	public Carte(int ligne, int colonne, int taillecase) {
		cases = new Case[ligne][colonne];
		this.tailleCases = taillecase;
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
	
	
	public Case getCase(int ligne, int colonne) throws InvalidCaseException {
		if(ligne >= 0 && ligne < getNbLignes() && colonne >= 0 && colonne < getNbColonnes())
			return cases[ligne][colonne];
		else
			throw new InvalidCaseException("La coordonn�e (" + ligne + ", " + colonne + ") n'est pas une coordonn�e de case valide");
	}

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
	
	public double DistanceVolOiseau(Case depart, Case arrive) {
		assert(depart != null);
		assert(arrive != null);
		return (this.tailleCases * Math.sqrt(((depart.getColonne() - arrive.getColonne()) * (depart.getColonne() - arrive.getColonne())) + 
				((depart.getLigne() - arrive.getLigne()) * (depart.getLigne() - arrive.getLigne()))));
	
	}
	
	public boolean estCaseAccessible(Robot r, Case arrive) {
		//Une case est accessible par un robot si sa vitesse n'est pas = 0
		return (r.getVitesseMilieu(arrive.getNature(), this) != 0);
	}
	
	public Collection<Case> caseVoisineAccessible(Robot r, Case c) {
		ArrayList<Case> voisins = new ArrayList<Case>();
		for (Direction d : Direction.values()) {
			try {
				Case unVoisin = this.getCase(c, d);
				if (this.estCaseAccessible(r, unVoisin))
					voisins.add(unVoisin);
				//Si le voisin ne fait pas partie de la carte, on ignore l'erreur
			} catch (InvalidCaseException e) {} 
		}
		return voisins;
	}
	
	//Retourne vrai si une des cases voisine de c 
	public boolean caseVoisineEau(Case c) {
		for (Direction d : Direction.values()) {
			try {
				if (this.getCase(c, d).getNature() == NatureTerrain.EAU)
					return true;
				//Si le voisin ne fait pas partie de la carte, on ignore l'erreur
			} catch (InvalidCaseException e) {} 
		}
		return false;
	}
	
	public double tempsDeplacement(Robot r, Case depart, Case arrive) {
		return (r.getVitesseMilieu(depart.getNature(), this) +
				r.getVitesseMilieu(arrive.getNature(), this)) / 2;
	}
	
	public int distanceNbCaseVolOiseau(Case dep, Case arr) {
		return Math.abs(dep.getColonne() - arr.getColonne()) + Math.abs(dep.getLigne() - arr.getLigne()); 
	}

	/*public ArrayList<Case> getAllWaterInRadius(Case dep, int radius) {
		ArrayList<Case> l = new ArrayList<Case>();
		for(Case[] ct : cases) {
			for(Case c : ct) {
				if(distanceNbCaseVolOiseau(dep,c) <= radius) {
					l.add(c);
				}
			}
		}
		return l;
	}*/

	public Case findNearestWater(Case last_case, Robot robot) {
		int min = Integer.MAX_VALUE;
		Case res = null;
		for(Case[] ct : cases) {
			for(Case ca : ct) {
				if(ca.getNature() == NatureTerrain.EAU && distanceNbCaseVolOiseau(last_case, ca) < min) {
					int cal = Astar.getShortestPath(last_case, ca, this, robot).size();
					if(cal < min) {
						res = ca;
					}
				}
			}
		}
		return res;
	}
	
}
