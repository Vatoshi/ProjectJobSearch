package kg.attractor.jobsearch.servise.helpers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import kg.attractor.jobsearch.models.Resume;
import kg.attractor.jobsearch.repositories.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserResumeServise {
    private final ResumeRepository resumeRepository;

    public Page<Resume> getResumesByUserId(Long userId, Pageable pageable) {
        return resumeRepository.getResumesByUserId(userId, pageable);
    }

    public Integer getResumesCountNonActive(Long userId) {
        return resumeRepository.getResumesCountNonActive(userId);
    }
}
