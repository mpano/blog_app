package net.myblog.blog.service.impl;

import net.myblog.blog.Model.Post;
import net.myblog.blog.exception.ResourceNotFoundException;
import net.myblog.blog.payload.PostDto;
import net.myblog.blog.payload.PostResponse;
import net.myblog.blog.repository.PostRepository;
import net.myblog.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createpost(PostDto postDto) {

        // convert dto to entity
        Post post = mapToEntity(postDto);
        Post newpost= postRepository.save(post);
        // convert entity to dto
        PostDto postResponce = mapToDto(newpost);

        return postResponce;
    }

    @Override
    public PostResponse getallpost(int pageNo, int pageSize) {
        Pageable pageable=  PageRequest.of(pageNo,pageSize);

       Page<Post> posts= postRepository.findAll(pageable);

       List<Post> listOfPost= posts.getContent();
       List<PostDto> content= listOfPost.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse= new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setLast(posts.isLast());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPage(posts.getTotalPages());

        return postResponse;
    }

    @Override
    public PostDto findbyid(String id) {
        Post post= postRepository.findById(id).orElseThrow(() -> new  ResourceNotFoundException("Post","id",id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatepost(PostDto postDto,String id) {
        Post post= postRepository.findById(id).orElseThrow(() -> new  ResourceNotFoundException("Post","id",id));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        Post updatepost= postRepository.save(post);

        return  mapToDto(updatepost);
    }

    @Override
    public String delete(String id) {
        Post post= postRepository.findById(id).orElseThrow(() -> new  ResourceNotFoundException("Post","id",id));
        postRepository.delete(post);
        return "is deleted";
    }

    private PostDto mapToDto(Post post){
        PostDto postDto=new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());
        return postDto;
    }
    private Post mapToEntity(PostDto postDto){
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        return post;
    }
}
