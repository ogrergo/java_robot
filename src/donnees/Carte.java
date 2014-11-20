package donnees;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import strategie.ActionMove;

public class Carte {
	
	private Case[][] cases;
	private int tailleCases;
	private HashSet<Case> plage = new HashSet<Case>();
	private HashSet<Case> water = new HashSet<Case>();
	
	public Carte(int ligne, int colonne, int taillecase) {
		cases = new Case[ligne][colonne];
		this.tailleCases = taillecase;
	}
	
	public void init() {
		initPlageEtWater();
	}
	
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
		return (r.getTempsDeplacementMilieu(arrive.getNature(), this) != Double.MAX_VALUE);
	}
	
	public Collection<Case> getVoisin(Case c) {
		ArrayList<Case> res = new ArrayList<Case>(4);
		for (Direction d : Direction.values()) {
			try {
				res.add(this.getCase(c, d));
			} catch (InvalidCaseException e) {}
		}
		return res;
	}
	
	public Collection<Case> caseVoisineAccessible(Robot r, Case c) {
		ArrayList<Case> res = new ArrayList<Case>(4);
		for (Case d : getVoisin(c)) {
			if (this.estCaseAccessible(r, d))
				res.add(d);
		}
		return res;
	}
	
	//Retourne vrai si une des cases voisine de c 
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
	
	public double tempsDeplacement(Robot r, Case depart, Case arrive) {
		return (r.getTempsDeplacementMilieu(depart.getNature(), this) +
				r.getTempsDeplacementMilieu(arrive.getNature(), this)) / 2;
	}
	
	public int distanceNbCaseVolOiseau(Case dep, Case arr) {
		return Math.abs(dep.getColonne() - arr.getColonne()) + Math.abs(dep.getLigne() - arr.getLigne()); 
	}

	public Case findNearestWater(Case last_case, Robot robot) {

		Collection<Case> target;
		if(robot.seRemplieSurEau())
			target = water;
		else
			target = plage;
		
		
		int min = Integer.MAX_VALUE;
		Case res = null;
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
		
		System.out.println("+++ ** +++ Nearest case to fill from " + last_case + " is " + res);
		return res;
	}
	
	
}
