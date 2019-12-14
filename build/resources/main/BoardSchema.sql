CREATE TABLE BOARD (
    seq         INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title       VARCHAR(255) NOT NULL ,
    context     VARCHAR(1000) NOT NULL ,
    writer      VARCHAR(10) NOT NULL ,
    password    INT NOT NULL ,
    regDate     TIMESTAMP NOT NULL ,
    cnt         INT NOT NULL
);