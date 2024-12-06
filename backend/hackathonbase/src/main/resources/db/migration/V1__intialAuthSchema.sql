CREATE TABLE users
(
    id                       BIGINT AUTO_INCREMENT NOT NULL,
    username                 VARCHAR(255)          NOT NULL,
    password                 VARCHAR(255)          NOT NULL,
    full_name                VARCHAR(255)          NOT NULL,
    dob                      DATE                  NOT NULL,
    gender                   VARCHAR(10)           NOT NULL,
    occupation               VARCHAR(255)          NULL,
    hobbies                  VARCHAR(512)          NULL,
    profile_pic              VARCHAR(512)          NULL,
    score                    BIGINT                NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);