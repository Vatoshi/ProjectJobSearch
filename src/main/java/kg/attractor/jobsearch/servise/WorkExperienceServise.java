package kg.attractor.jobsearch.servise;

import kg.attractor.jobsearch.models.WorkExperienceInfo;
import kg.attractor.jobsearch.repositories.WorkExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkExperienceServise {
    private final WorkExperienceRepository workExperienceRepository;

    public List<WorkExperienceInfo> getWorkExperienceByResumeId(Long resumeId) {
        return workExperienceRepository.getWorkExperienceInfoByResumeId(resumeId);
    }

    public void saveAll (List<WorkExperienceInfo> workExperienceInfos) {
        workExperienceRepository.saveAll(workExperienceInfos);
    }
}
