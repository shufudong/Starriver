create table task (
    id bigint auto_increment,
    title varchar(128) not null,
    description varchar(255),
    user_id bigint not null,
    primary key (id)
) engine=InnoDB;

create table user (
    id bigint auto_increment,
    login_name varchar(64) not null unique,
    name varchar(64) not null,
    password varchar(255) not null,
    salt varchar(64) not null,
    roles varchar(255) not null,
    register_date timestamp not null default 0,
    primary key (id)
) engine=InnoDB;

insert into task (id, title, description, user_id) values(1, 'Study SpringFramework','学习框架', 2);
insert into task (id, title, description, user_id) values(2, 'Study Grails 2.0','学习语言', 2);
insert into task (id, title, description, user_id) values(3, 'Try SpringFuse','尝试SpringFuse', 2);
insert into task (id, title, description, user_id) values(4, 'Try Spring root','尝试Spring root', 2);
insert into task (id, title, description, user_id) values(5, '更新Starriver','As soon as posibble.', 2);
insert into task (id, title, description, user_id) values(6, '打篮球','打篮球', 2);
insert into task (id, title, description, user_id) values(7, '踢足球','踢足球', 2);
insert into task (id, title, description, user_id) values(8, '弹钢琴','弹钢琴', 2);
insert into task (id, title, description, user_id) values(9, '看电影','看电影', 2);
insert into task (id, title, description, user_id) values(10, '喝咖啡','喝咖啡', 2);


insert into user (id, login_name, name, password, salt, roles, register_date) values(1,'admin','Admin','691b14d79bf0fa2215f155235df5e670b64394cc','7efbd59d9741d34f','admin','2016-03-04 01:00:00');
insert into user (id, login_name, name, password, salt, roles, register_date) values(2,'user','Starriver','2488aa0c31c624687bd9928e0a5d29e7d1ed520b','6d65d24122c30500','user','2016-03-04 02:00:00');

