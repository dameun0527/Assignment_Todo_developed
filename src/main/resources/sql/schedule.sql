-- Member 테이블 생성
CREATE TABLE member (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP,
                        updated_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        email VARCHAR(255) NOT NULL UNIQUE,
                        name VARCHAR(255) NOT NULL,
                        member_id BIGINT NOT NULL UNIQUE
);

-- Schedule 테이블 생성
CREATE TABLE schedule (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP,
                          updated_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          title VARCHAR(255) NOT NULL,
                          password VARCHAR(255) NOT NULL
);

-- Comments 테이블 생성
CREATE TABLE comments (
                          comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP,
                          updated_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          comment TEXT NOT NULL,
                          member_id BIGINT NOT NULL,
                          schedule_id BIGINT NOT NULL,
                          CONSTRAINT FK_member FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE,
                          CONSTRAINT FK_schedule FOREIGN KEY (schedule_id) REFERENCES schedule(id) ON DELETE CASCADE
);