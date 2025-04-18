package kg.attractor.jobsearch.repositories;

import kg.attractor.jobsearch.models.WorkExperienceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkExperienceRepository extends JpaRepository<WorkExperienceInfo,Long> {
    List<WorkExperienceInfo> getWorkExperienceInfoByResumeId(Long resumeId);
}
