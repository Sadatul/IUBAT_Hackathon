CREATE TABLE chat_messages
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    session_id BIGINT                NOT NULL,
    message    TEXT                  NOT NULL,
    is_user    BIT(1)                NOT NULL,
    sent_at    datetime              NOT NULL,
    CONSTRAINT pk_chat_messages PRIMARY KEY (id)
);

CREATE TABLE chat_sessions
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    user_id BIGINT                NOT NULL,
    name    VARCHAR(255)          NULL,
    CONSTRAINT pk_chat_sessions PRIMARY KEY (id)
);

ALTER TABLE chat_messages
    ADD CONSTRAINT FK_CHAT_MESSAGES_ON_SESSION FOREIGN KEY (session_id) REFERENCES chat_sessions (id);

ALTER TABLE chat_sessions
    ADD CONSTRAINT FK_CHAT_SESSIONS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);