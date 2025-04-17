package kg.attractor.jobsearch.repositories;

import kg.attractor.jobsearch.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
