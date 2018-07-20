DROP TABLE IF EXISTS stock_data;

CREATE TABLE stock_data (
    id int not null auto_increment,
    company int not null,
    price float not null,
    volume int not null,
    date timestamp not null,
    primary key (id)
);