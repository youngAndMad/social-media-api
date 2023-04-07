package danekerscode.socialmediaapi.resository;

import danekerscode.socialmediaapi.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends JpaRepository<Image,Integer> {
}
