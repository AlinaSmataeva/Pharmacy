create table basket
(
    id              int auto_increment primary key,
    id_user         int null,
    id_medicine     int null,
    foreign key (id_user) references doctor (id),
    foreign key (id_medicine) references medicine (id)
);

create table medicine
(
    id         int auto_increment primary key,
    name       varchar(50) null,
    dose       int         null,
    cost       int         null,
    recipe     tinyint(1)  null,
    image      mediumblob  null,
    order_date date        null,
    image_name varchar(50) null
);

create table recipe
(
    id          int auto_increment primary key,
    issue_date  date        not null,
    recipe_time date        not null,
    id_user     int null,
    id_medicine int null,
    foreign key (id_user) references doctor (id),
    foreign key (id_medicine) references medicine (id)
);

create table roles
(
    id   int auto_increment primary key,
    role varchar(15) null
);

create table doctor
(
    id         int auto_increment primary key,
    username   varchar(15)  not null,
    first_name varchar(20)  null,
    last_name  varchar(20)  not null,
    email      varchar(254) not null,
    phone      varchar(13)  not null,
    password   varchar(128) not null,
    id_role    int          null,
    foreign key (id_role) references roles (id)
);

create table medicine_data
(
    id          int auto_increment
        primary key,
    amount      int not null,
    id_recipe   int not null,
    id_medicine int null,
    id_user     int null,
    foreign key (id_medicine) references medicine (id),
    foreign key (id_recipe) references recipe (id),
    foreign key (id_user) references doctor (id)
);

create table reservation
(
    id          int auto_increment primary key,
    id_user     int        not null,
    id_medicine int        not null,
    status      tinyint(1) null,
    address     varchar(30) null,
    id_recipe   int        null,
    foreign key (id_user) references doctor (id),
    foreign key (id_medicine) references medicine_data (id),
    foreign key (id_recipe) references recipe (id)
);