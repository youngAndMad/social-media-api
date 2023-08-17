package danekerscode.socialmediaapi.payload.request;

import javax.validation.constraints.NotNull;

public record ArticleDTO(
        @NotNull
        String author,
        @NotNull
        String title,
        @NotNull
        String description,
        @NotNull
        String url,
        @NotNull
        String content,
        String imageUrl
) {
}
