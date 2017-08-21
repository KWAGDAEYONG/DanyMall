package com.example.demo.repository;

        import com.example.demo.model.Item;
        import com.example.demo.model.Merchandise;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchandiseRepository extends JpaRepository<Merchandise, Long> {
        Merchandise findByItemAndColorAndSize(Item item, String color, String size);

}