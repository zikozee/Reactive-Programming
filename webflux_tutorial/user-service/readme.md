## POSTGRES SCRIPT
```postgresql
CREATE TABLE USERS (
                       id serial primary key,
                       name varchar(50),
                       balance int
);


CREATE TABLE USER_TNX (
                          id serial primary key,
                          user_id bigint,
                          amount int,
                          transaction_date timestamp,
                          CONSTRAINT fk_user_id
                              FOREIGN KEY (user_id)
                                  REFERENCES users(id)
                                  on delete cascade
);
```