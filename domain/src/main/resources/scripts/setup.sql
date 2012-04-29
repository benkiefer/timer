CREATE TABLE tbtMovie (
MovieId int NOT NULL AUTO_INCREMENT,
Title varchar(255) NOT NULL,
Watched bit default 0
);

Alter Table tbtMovie ADD PRIMARY KEY (MovieId);