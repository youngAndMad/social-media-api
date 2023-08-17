package danekerscode.socialmediaapi.mapper;

import danekerscode.socialmediaapi.model.Channel;
import danekerscode.socialmediaapi.model.User;
import danekerscode.socialmediaapi.payload.request.ChannelDTO;
import org.mapstruct.*;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface ChannelMapper {

    @Mapping(target = "owner" , expression = "java(owner)")
    @Mapping(target = "id" , ignore = true)
    Channel toChannel(ChannelDTO dto , User owner);

    @Mapping(target = "owner" ,ignore = true)
    void update(ChannelDTO dto, @MappingTarget Channel channel);
}
