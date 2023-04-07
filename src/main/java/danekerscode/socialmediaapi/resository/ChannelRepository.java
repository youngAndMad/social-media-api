package danekerscode.socialmediaapi.resository;

import danekerscode.socialmediaapi.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelRepository extends JpaRepository<Channel , Integer> {
    List<Channel> getChannelsByOwner_Id(Integer id);
}
