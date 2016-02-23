package tembo.terrariums.components;

public class OrganismDeadException extends Exception {

	/**
	 * The message constructor
	 * @param string
	 */
	public OrganismDeadException(String msg) {
		super(msg);
	}

	/**
	 * Exception constructor
	 * @param e the exception
	 */
	public OrganismDeadException(Exception e) {
		super(e);
	}

	/**
	 * String and exception constructor
	 * @param msg the message
	 * @param e the exception
	 */
	public OrganismDeadException(String msg, Exception e) {
		super(msg,e);
	}
}
