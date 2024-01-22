package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;
    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /* Case 1: 4번 게시글의 모든 댓글 조회 */
        {
            // 1. 입력 데이터 준비
            Long articleId = 4L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = new Article(4L, "당신의 인생 영화는?", "댓글 고");
            Comment a = new Comment(1L, article, "PARK", "굿 윌 헌팅");
            Comment b = new Comment(2L, article, "KIM", "아이 엠 셈");
            Comment c = new Comment(3L, article, "CHOI", "쇼생크 탈출");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력!");
        }
        /* Case 2: 1번 게시글의 모든 댓글 조회 */
        {
            // 1. 입력 데이터 준비
            Long articleId = 1L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = new Article(1L, "가가가가", "1111");
            List<Comment> expected = Arrays.asList(); // 1번 게시글에는 댓글이 존재하지 않아서 공란

            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없음");
        }
        /* Case 3: 9번 게시글의 모든 댓글 조회 */
        {
            // 1. 입력 데이터 준비
            Long articleId = 9L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 3. 예상 데이터
            List<Comment> expected = new ArrayList<Comment>();

            // 4. 비교 및 검증
            assertEquals(comments, expected, "둘다 없ㅇ므");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        /* Case 1: "Park"의 모든 댓글 조회 */
        {
            // 1. 입력 데이터 준비
            String nickname = "PARK";
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. 예상 데이터
            Comment a = new Comment(1L, new Article(4L, "당신의 인생 영화는?", "댓글 고"), "PARK", "굿 윌 헌팅");
            Comment b = new Comment(4L, new Article(5L, "당신의 소울 푸드는?", "댓글 고고"), "PARK", "치킨");
            Comment c = new Comment(7L, new Article(6L, "당신의 취미는?", "댓글 고고고"), "PARK", "조깅");
            List<Comment> expected = Arrays.asList(a,b,c);
            // 4. 비교 및 검증
            assertEquals(comments.toString(), expected.toString(), "Park의 모든 댓글을 출력!");
        }

        /* Case 2: "KIM"의 모든 댓글 조회 */
        {
            // 1. 입력 데이터 준비
            String nickname = "KIM";
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. 예상 데이터
            Comment a = new Comment(2L, new Article(4L, "당신의 인생 영화는?", "댓글 고"), "KIM", "아이 엠 셈");
            Comment b = new Comment(5L, new Article(5L, "당신의 소울 푸드는?", "댓글 고고"), "KIM", "피자");
            Comment c = new Comment(8L, new Article(6L, "당신의 취미는?", "댓글 고고고"), "KIM", "유튜브 시청");
            List<Comment> expected = Arrays.asList(a,b,c);
            // 4. 비교 및 검증
            assertEquals(comments.toString(), expected.toString(), "김의 모든 댓글 출력");
        }

        /* Case 3: null의 모든 댓글 조회 */
//        {
//            // 1. 입력 데이터 준비
//            String nickname = ;
//            // 2. 실제 데이터
//            List<Comment> comments = commentRepository.findByNickname(nickname);
//            // 3. 예상 데이터
//            Comment a = new Comment(2L, new Article(4L, "당신의 인생 영화는?", "댓글 고"), "KIM", "아이 엠 셈");
//            Comment b = new Comment(5L, new Article(5L, "당신의 소울 푸드는?", "댓글 고고"), "KIM", "피자");
//            Comment c = new Comment(8L, new Article(6L, "당신의 취미는?", "댓글 고고고"), "KIM", "유튜브 시청");
//            List<Comment> expected = Arrays.asList(a,b,c);
//            // 4. 비교 및 검증
//            assertEquals(comments.toString(), expected.toString(), "김의 모든 댓글 출력");
//        }
    }
}