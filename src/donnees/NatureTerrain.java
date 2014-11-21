package donnees;

/**
 * <b><code>NatureTerrain</code> est l'�numaration permettant de representer les diff�rents 
 * type de terrain des cases</b>
 * <p>
 * Les diff�rents types de terrain sont :
 * <ul>
 * <li>EAU</li>
 * <li>FORET</li>
 * <li>ROCHE</li>
 * <li>TERRAIN_LIBRE</li>
 * <li>HABITAT</li>
 * </ul>
 * </p>
 * 
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */

public enum NatureTerrain {
	EAU(),
	FORET(),
	ROCHE(),
	TERRAIN_LIBRE(),
	HABITAT();
}
