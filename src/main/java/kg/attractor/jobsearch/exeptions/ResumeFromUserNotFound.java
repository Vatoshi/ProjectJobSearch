package kg.attractor.jobsearch.exeptions;

public class ResumeFromUserNotFound extends IllegalArgumentException {
    public ResumeFromUserNotFound() {
        super("Resume from user not found");
    }
}
