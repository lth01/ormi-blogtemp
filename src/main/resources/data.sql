INSERT INTO article (title, content, created_at, updated_at) VALUES ('제목1', '내용1', NOW(), NOW());
INSERT INTO article (title, content, created_at, updated_at) VALUES ('제목2', '내용2', NOW(), NOW());
INSERT INTO article (title, content, created_at, updated_at) VALUES ('제목3', '내용3', NOW(), NOW());


insert into COMMENT(ARTICLE_ID, CREATED_AT, BODY ) VALUES(1, NOW(), '하이1');
insert into COMMENT(ARTICLE_ID, CREATED_AT, BODY ) VALUES(2, NOW(), '하이2');
insert into COMMENT(ARTICLE_ID, CREATED_AT, BODY ) VALUES(3, NOW(), '하이3');
insert into COMMENT(ARTICLE_ID, CREATED_AT, BODY ) VALUES(2, NOW(), '하이4');
