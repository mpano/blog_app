package net.myblog.blog.service;

import net.myblog.blog.payload.PostDto;
import net.myblog.blog.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createpost(PostDto postDto);
    PostResponse getallpost(int pageNo, int pageSize);
    PostDto findbyid(String id);
    PostDto updatepost(PostDto postDto,String id);
    String delete(String id);
}
