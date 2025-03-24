package kg.attractor.jobsearch.controlers;

import kg.attractor.jobsearch.dto.CategoriesDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.servise.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("resume")
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping("category/{categoryName}")
    public List<ResumeDto> getResumesByCategory(@PathVariable String categoryName) {
        return resumeService.getResumesById(categoryName);
    }

    @GetMapping("by-user/{userId}")
    public List<ResumeDto> getResumesByUserId(@PathVariable Long userId) {
        return resumeService.getResumesByAplicant(userId);
    }
}
