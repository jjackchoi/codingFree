package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    // 1. 댓글 조회
    /**
     * 반환형이 List<Comment>가 아닌 ResponseEntity<List<CommentDto>>인 이유는
     * db에서 조회한 댓글 엔티티 목록은 List<Comment>이지만 엔티티를 dto로 변환하면
     * List<CommentDto>가 되기 때문
     **/

    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>>  comments(@PathVariable Long articleId){
        // 서비스에 위임
        List<CommentDto> dtos = commentService.comments(articleId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 2. 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId,
                                             @RequestBody CommentDto dto){
        // 서비스에 위임
        CommentDto createDto = commentService.create(articleId, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }
    // 3. 댓글 수정
    // 4. 댓글 삭제


}
