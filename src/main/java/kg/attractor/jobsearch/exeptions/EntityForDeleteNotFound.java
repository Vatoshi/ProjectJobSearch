package kg.attractor.jobsearch.exeptions;

public class EntityForDeleteNotFound extends RuntimeException {
    public EntityForDeleteNotFound(String message) {
        super(message);
    }
}
