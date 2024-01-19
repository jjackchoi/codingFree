-- 기존 Article 데이터
INSERT INTO article(title, content) VALUES('가가가가', '1111');
INSERT INTO article(title, content) VALUES('나나나나', '2222');
INSERT INTO article(title, content) VALUES('다다다다', '3333');
INSERT INTO article(title, content) VALUES('당신의 인생 영화는?', '댓글 고');
INSERT INTO article(title, content) VALUES('당신의 소울 푸드는?', '댓글 고고');
INSERT INTO article(title, content) VALUES('당신의 취미는?', '댓글 고고고');
-- 4번 게시글의 댓글 추가
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'PARK', '굿 윌 헌팅');
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'KIM', '아이 엠 셈');
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'CHOI', '쇼생크 탈출');
-- 5번 게시글의 댓글 추가
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'PARK', '치킨');
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'KIM', '피자');
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'CHOI', '초밥');
-- 6번 게시글의 댓글 추가
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'PARK', '조깅');
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'KIM', '유튜브 시청');
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'CHOI', '축구');

INSERT INTO member(email, password) VALUES('a@naver.com', '1111');
INSERT INTO member(email, password) VALUES('b@naver.com', '2222');
INSERT INTO member(email, password) VALUES('c@naver.com', '3333');

INSERT INTO coffee(name, price) VALUES('아메리카노', 2500);
INSERT INTO coffee(name, price) VALUES('라떼', 3000);
INSERT INTO coffee(name, price) VALUES('초코바나나', 4000);



