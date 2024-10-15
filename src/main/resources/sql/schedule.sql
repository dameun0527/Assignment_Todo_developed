# 일정 관리
CREATE TABLE schedule (
                          id BIGINT Auto_Increment Primary Key,
                          title VARCHAR(255),
                          username VARCHAR(255),
                          password VARCHAR(255),
                          created_at VARCHAR(10),
                          updated_at VARCHAR(10)
);

# 외래키 추가 (댓글 기능 추가)
CREATE TABLE schedule (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          title VARCHAR(255),
                          username VARCHAR(255),
                          password
)