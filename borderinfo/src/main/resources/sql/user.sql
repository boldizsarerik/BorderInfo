create table User
(
    id       int not null primary key,
    date     datetime  null,
    password varchar(255) null,
    username varchar(255) null,
    roles varchar(255) null,
    active   bit not null
);

/* értékelés
Plusz tábla:
   - Border
   - Comments
 */