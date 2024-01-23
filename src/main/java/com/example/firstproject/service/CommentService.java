package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        /*// 1. 댓글 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        // 2. 엔티티 -> dto 변환
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for (int i = 0; i < comments.size(); i++){
            Comment c = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);
            dtos.add(dto);
        }*/
        // 3. 결과 반환
        return commentRepository.findByArticleId(articleId)
                .stream() // 댓글 엔티티 목록을 스트림으로 변환
                .map(comment -> CommentDto.createCommentDto(comment)) // map( a -> b ): 스트림의 각 요소(a)를 꺼내 b를 수행한 결과로 매핑, 즉 엔티티를 dto로 매핑
                .collect(Collectors.toList()); // 스트림을 리스트로 변환
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // 1. 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId) //부모 게시글 가져오기
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패!" +
                        "대상 게시글이 없습니다.")); // 없으면 에러 메세지 출력
        // 2. 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);
        // 3. 댓글 엔티티를 db에 저장
        Comment created = commentRepository.save(comment);
        // 4. dto를 변환해 반환
        return CommentDto.createCommentDto(created);
    }
}