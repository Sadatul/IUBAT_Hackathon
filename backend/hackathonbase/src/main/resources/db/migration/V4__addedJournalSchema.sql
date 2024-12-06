CREATE TABLE journals
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    user_id    BIGINT                NOT NULL,
    title      VARCHAR(255)          NOT NULL,
    content    TEXT                  NOT NULL,
    created_at datetime              NOT NULL,
    CONSTRAINT pk_journals PRIMARY KEY (id)
);

ALTER TABLE journals
    ADD CONSTRAINT FK_JOURNALS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);