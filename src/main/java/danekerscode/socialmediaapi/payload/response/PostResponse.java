package danekerscode.socialmediaapi.payload.response;

import danekerscode.socialmediaapi.model.Comment;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostResponse {
    private Integer id;
    private String title;
    private String body;
    private List<Comment> comments;
    private List<String> imagesURLs;
}
