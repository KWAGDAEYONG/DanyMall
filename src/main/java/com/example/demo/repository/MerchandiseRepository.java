package com.example.demo.repository;

        import com.example.demo.model.Item;
        import com.example.demo.model.Merchandise;
        import org.springframework.data.jpa.repository.JpaRepository;

        import java.util.List;

public interface MerchandiseRepository extends JpaRepository<Merchandise, Long> {
        List<Merchandise> findByItem(Item item);
}