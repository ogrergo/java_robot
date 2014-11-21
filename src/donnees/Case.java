package donnees;

/**
 * <b><code>Case</code> est la classe représentant une case</b>
 * <p>
 * Une case est caractérisée par :
 * <ul>
 * <li>sa ligne</li>
 * <li>sa colonne</li>
 * </ul>
 * </p>
 * 
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public class Case{

	/**
	 * La ligne de la case
	 */
	private int ligne;
	
	/**
	 * La colonne de la case
	 */
	private int colonne;
	
	/**
	 * La nature du terrain sur lequel se trouve la classe
	 */
	private NatureTerrain nature;
	
	/**
	 * Constructeur de la case
	 * 
	 * @param ligne
	 *            ligne de la case
	 * @param colonne
	 *            colonne de la case
	 * @param nature
	 *            nature de la case
	 */
	public Case(int ligne, int colonne, NatureTerrain nature) {
		this.ligne   = ligne;
		this.colonne = colonne;
		this.nature  = nature;
	}
	
	/**
	 * Retourne la ligne de la case
	 * 
	 * @return la ligne de la case   	
	 */
	public int getLigne() {
		return ligne;
	}
	
	/**
	 * Retourne la colonne de la case
	 * 
	 * @return la colonne de la case   	
	 */
	public int getColonne() {
		return colonne;
	}
	
	/**
	 * Retourne la nature du terrain de la case
	 * 
	 * @return la nature du terrain de la case   	
	 */
	public NatureTerrain getNature() {
		return nature;
	}
	
	@Override
	public String toString() {
		return "(" + ligne + ", " + colonne + ")";
	}
}
