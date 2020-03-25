INSERT INTO User (username,password) VALUES ('Erik','erik1997');
INSERT INTO User (username,password) VALUES ('Györgyi','12345678');
INSERT INTO BORDER(name,cars,posted,user_id) VALUES('Beregsurány -> Asztély',54, CURRENT_TIMESTAMP, (SELECT id FROM USER WHERE username='Erik') );
INSERT INTO BORDER(name,cars,posted,user_id) VALUES('Beregsurány -> Asztély',100, CURRENT_TIMESTAMP, (SELECT id FROM USER WHERE username='Erik') );
INSERT INTO BORDER(name,cars,posted,user_id) VALUES('Barabás -> Kaszony',40, CURRENT_TIMESTAMP, (SELECT id FROM USER WHERE username='Györgyi') );
INSERT INTO BORDER(name,cars,posted,user_id) VALUES('Barabás -> Kaszony',10, CURRENT_TIMESTAMP, (SELECT id FROM USER WHERE username='Györgyi') );
INSERT INTO BORDER(name,cars,posted,user_id) VALUES('valami',30, CURRENT_TIMESTAMP, (SELECT id FROM USER WHERE username='Erik') );
