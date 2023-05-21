package com.test.LikelionPost.controller;

import com.test.LikelionPost.Dto.PostRequestDto;
import com.test.LikelionPost.Dto.PostReturnDto;
import com.test.LikelionPost.domain.Post;
import com.test.LikelionPost.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostServiceImpl postService;

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostReturnDto> getPostById(@PathVariable("id") long id) {
        try{
            return ResponseEntity.ok(postService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    @GetMapping("/posts")
    public ResponseEntity<List<PostReturnDto>> getAllProduct(){
        List<PostReturnDto> posts = postService.findAll();
        return ResponseEntity.ok(posts);
    }

    @PostMapping ("/posts")
    public ResponseEntity<Post> createPost(@RequestBody PostRequestDto postRequestDto){
        try{
            ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(postService.save(postRequestDto));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<PostReturnDto> updatePost(@PathVariable("id") long id, @RequestBody PostRequestDto postRequestDto){
        try {
            ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(postService.update(id, postRequestDto));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id){
        try{
            postService.delete(id);
            ResponseEntity.noContent();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
