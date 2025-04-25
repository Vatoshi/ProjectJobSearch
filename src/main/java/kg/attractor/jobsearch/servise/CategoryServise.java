package kg.attractor.jobsearch.servise;

import kg.attractor.jobsearch.models.Category;
import kg.attractor.jobsearch.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServise {
    private final CategoryRepository categoryRepository;

    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
