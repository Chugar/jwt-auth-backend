CREATE TABLE simple_user (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username varchar(250) not null UNIQUE,
    password varchar(250) not null
);

CREATE TABLE user_roles (
	user_id int NOT NULL,
    role_id int NOT NULL,
    
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES simple_user(id),
    FOREIGN KEY (role_id) REFERENCES simple_authoritie(id)
);


INSERT INTO `simple_user`(`username`, `password`) 

VALUES ('chill', '$2a$10$TrwizbPmLSiRdATfQ4gLruMdmhCXCdyeUQ1As.I0Q88nm/Nxrg0o.')