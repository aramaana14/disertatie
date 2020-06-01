package ro.metro.qe.clients;

public class Result {

	private static final String OKAY_STATUS = "OK";

	private String status;

	public static String getOkayStatus() {
		return OKAY_STATUS;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus( ) {
		return this.status;
	}

	public boolean isOkay( ) {
		return OKAY_STATUS.equals( this.getStatus( ) );
	}

}
