package donnees;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

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
	
	
}
