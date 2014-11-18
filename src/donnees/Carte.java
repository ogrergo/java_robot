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
		return Math.sqrt((Math.round((depart.getColonne() - arrive.getColonne())) 
				+ Math.round((depart.getLigne() - arrive.getLigne())))) * this.tailleCases;
	}
	
	public boolean estCaseAccessible(Robot r, Case arrive) {
		//Une case est accessible par un robot si sa vitesse n'est pas = 0
		return (r.getVitesseMilieu(arrive.getNature()) != 0);
	}
	
	public Collection<Case> caseVoisine(Robot r, Case c) {
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
	
	public double tempsDeplacement(Robot r, Case depart, Case arrive) {
		return (r.getVitesseMilieu(depart.getNature()) +
				r.getVitesseMilieu(arrive.getNature())) / 2;
	}
	
}
