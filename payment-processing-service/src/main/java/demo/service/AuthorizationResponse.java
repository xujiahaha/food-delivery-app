package demo.service;


public class AuthorizationResponse {
    private boolean approved;
    private String errorMessage;

    public AuthorizationResponse() {
    }

    public AuthorizationResponse(boolean approved, String errorMessage) {
        this.approved = approved;
        this.errorMessage = errorMessage;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
