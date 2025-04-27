package kg.attractor.jobsearch.servise;

import kg.attractor.jobsearch.models.ResetPassword;
import kg.attractor.jobsearch.repositories.ResetPasswordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;


@Service
@RequiredArgsConstructor
public class ResetPasswordServise {
    private final ResetPasswordRepository resetPasswordRepository;

    public String getTokenByEmail(String email) {
        ResetPassword resetPassword = resetPasswordRepository.findByEmail(email);
        return resetPassword.getToken();
    }

    public boolean checkExistToken(String email) {
        return resetPasswordRepository.existsByEmail(email);
    }

    public void deleteToken(String email) {
        resetPasswordRepository.deleteByEmail(email);
    }

    public boolean checkToken(String email, String token) {
        return resetPasswordRepository.existsByEmailAndToken(email, token);
    }

    public void createToken(String email) {
        ResetPassword reset = resetPasswordRepository.findByEmail(email);
        if (reset != null) {
            reset.setToken(generateSixDigitCode());
            resetPasswordRepository.save(reset);
        } else {
            ResetPassword resetPassword = new ResetPassword();
            resetPassword.setEmail(email);
            resetPassword.setToken(generateSixDigitCode());
            resetPasswordRepository.save(resetPassword);
        }
    }

    private String generateSixDigitCode() {
        SecureRandom random = new SecureRandom();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}
