package evenement;

public class ExecutionException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ExecutionException(Evenement e, String msg) {
		super("Erreur evenement \""+ e.getClass().getName() + "\" : " + msg);
	}

}