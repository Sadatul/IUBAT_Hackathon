CREATE TABLE activity
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    user_id  BIGINT       NOT NULL,
    title    VARCHAR(255) NOT NULL,
    deadline datetime     NOT NULL,
    CONSTRAINT pk_activity PRIMARY KEY (id)
);

CREATE TABLE sub_activities
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    title        VARCHAR(255) NOT NULL,
    activity_id  BIGINT       NOT NULL,
    is_completed BIT(1)       NOT NULL,
    CONSTRAINT pk_sub_activities PRIMARY KEY (id)
);

ALTER TABLE activity
    ADD CONSTRAINT FK_ACTIVITY_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE sub_activities
    ADD CONSTRAINT FK_SUB_ACTIVITIES_ON_ACTIVITY FOREIGN KEY (activity_id) REFERENCES activity (id);