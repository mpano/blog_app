package net.myblog.blog.payload;

import lombok.Data;

@Data
public class PostDto {
    private String id;
    private String title;
    private String description;
    private String content;
}
