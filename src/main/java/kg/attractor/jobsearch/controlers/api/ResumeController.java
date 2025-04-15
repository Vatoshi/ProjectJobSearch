package kg.attractor.jobsearch.controlers.api;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.servise.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("resume")
public class ResumeController {
    private final ResumeService resumeService;

    @DeleteMapping("delete/{resumeId}")
    public HttpStatus deleteResume(@PathVariable Long resumeId) {
        return resumeService.deleteResume(resumeId);
    }

    @PutMapping("edit/{resumeId}")
    public ResponseEntity<ResumeDto> editResume(@PathVariable Long resumeId, @RequestBody @Valid ResumeDto resumeDto) {
        return resumeService.updateResume(resumeId, resumeDto);
    }

    @GetMapping("category/{categoryName}")
    public List<ResumeDto> getResumesByCategory(@PathVariable String categoryName) {
        return resumeService.getResumesById(categoryName);
    }

//    @GetMapping("by-user/{userId}")
//    public List<ResumeDto> getResumesByUserId(@PathVariable Long userId) {
//        return resumeService.getResumesByAplicant(userId);
//    }

//    @GetMapping("id/{resumeId}")
//    public ResumeDto getResume(@PathVariable Long resumeId) {
//        return resumeService.getResumeById(resumeId);
//    }
}
