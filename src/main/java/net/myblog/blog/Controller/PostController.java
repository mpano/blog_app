package net.myblog.blog.Controller;

import net.myblog.blog.payload.PostDto;
import net.myblog.blog.payload.PostResponse;
import net.myblog.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/post")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public ResponseEntity<PostDto> create(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createpost(postDto), HttpStatus.CREATED);
    }
    @GetMapping
    public PostResponse getall(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                               @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return postService.getallpost(pageNo,pageSize);
    }

    @GetMapping("/{id}")
    public PostDto getPostById(@PathVariable(name = "id") String id){
        return postService.findbyid(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatepost(@RequestBody PostDto postDto, @PathVariable(name = "id") String id){
        PostDto postResponce= postService.updatepost(postDto,id);
        return new ResponseEntity<>(postResponce,HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") String id){
        postService.delete(id);
        return new ResponseEntity<>("the post is deleted successfully", HttpStatus.OK);
    }
}
