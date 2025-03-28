package kg.attractor.jobsearch.exeptions;

public class NotFound extends RuntimeException {
    public NotFound(String message) {
        super(message);
    }
}
