insert into users(id,username,password) value(1,"master",MD5('master'));
insert into users(id,username,password) value(2,"customer",MD5('customer'));
insert into users(id,username,password) value(3,"Director",MD5('director'));
insert into users(id,username,password) value(4,"Deputy_director",MD5('deputy_director'));
insert into users(id,username,password) value(5,"Manager",MD5('manager'));

insert into user_role(user_id,roles) value(1,'Master');
insert into user_role(user_id,roles) value(2,'Customer');
insert into user_role(user_id,roles) value(3,'Director');
insert into user_role(user_id,roles) value(4,'Deputy_director');
insert into user_role(user_id,roles) value(5,'Manager');




