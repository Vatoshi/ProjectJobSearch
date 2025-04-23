package kg.attractor.jobsearch.servise;

import kg.attractor.jobsearch.models.EducationInfo;
import kg.attractor.jobsearch.repositories.EducationInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationInfoServise {
    private final EducationInfoRepository educationInfoRepository;

    public void saveEducationInfo(List<EducationInfo> educationInfo) {
        educationInfoRepository.saveAll(educationInfo);
    }

    public List<EducationInfo> getEducationInfoByResumeId(Long resumeId) {
        return educationInfoRepository.getEducationInfoByResumeId(resumeId);
    }
}
