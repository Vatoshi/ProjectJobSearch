package kg.attractor.jobsearch.repositories;

import kg.attractor.jobsearch.models.EducationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationInfoRepository extends JpaRepository<EducationInfo,Long> {

    List<EducationInfo> getEducationInfoByResumeId(Long resumeId);
}
