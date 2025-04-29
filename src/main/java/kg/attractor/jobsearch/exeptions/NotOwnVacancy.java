package kg.attractor.jobsearch.exeptions;

public class NotOwnVacancy extends RuntimeException {
    public NotOwnVacancy(String message) {
        super(message);
    }
}
