package kg.attractor.jobsearch.servise.helpers;
import kg.attractor.jobsearch.dto.mutal.ResumeForProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import kg.attractor.jobsearch.models.Resume;
import kg.attractor.jobsearch.repositories.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserResumeServise {
    private final ResumeRepository resumeRepository;

    public Page<ResumeForProfile> getResumesByUserId(Long userId, Pageable pageable) {
        return resumeRepository.getResumesByUserId(userId, pageable)
                .map(resume -> ResumeForProfile.builder()
                        .updateTime(Date.from(resume.getUpdateTime().atZone(ZoneId.systemDefault()).toInstant()))
                        .name(resume.getName())
                        .id(resume.getId())
                        .build());
    }

}
