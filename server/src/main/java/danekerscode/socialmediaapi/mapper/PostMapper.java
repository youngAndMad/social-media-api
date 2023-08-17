package danekerscode.socialmediaapi.mapper;

import danekerscode.socialmediaapi.model.Channel;
import danekerscode.socialmediaapi.model.Post;
import danekerscode.socialmediaapi.payload.request.PostDTO;
import org.mapstruct.*;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface PostMapper {


    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "channel" , expression = "java(channel)")
    Post toPost(PostDTO dto, Channel channel);


    void update(PostDTO dto,@MappingTarget Post post);
}
