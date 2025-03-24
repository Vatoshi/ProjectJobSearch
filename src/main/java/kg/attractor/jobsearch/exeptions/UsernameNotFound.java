package kg.attractor.jobsearch.exeptions;

public class UsernameNotFound extends RuntimeException {
    public UsernameNotFound() {
        super("Username not found");
    }
}
