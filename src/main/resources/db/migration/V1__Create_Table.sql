create table equipment_accounting (mark varchar(255) not null, begin_date date, deterioration integer, end_date date, equipment_type varchar(255), primary key (mark)) engine=InnoDB;
create table equipments (mark varchar(255) not null, description varchar(255), equipment_type varchar(255), primary key (mark)) engine=InnoDB;
create table furnitures (article varchar(255) not null, amount integer not null, furniture_type varchar(255), image_name varchar(255), purchase_price decimal(19,2) not null, unit varchar(255) not null, weight integer not null, main_supplier_id bigint, primary key (article)) engine=InnoDB;
create table materials (article varchar(255) not null, amount integer not null, description varchar(255), gost varchar(255), image_name varchar(255), length integer, material_type varchar(255), purchase_price decimal(19,2) not null, unit varchar(255) not null, main_supplier_id bigint, primary key (article)) engine=InnoDB;
create table orders (id bigint not null, order_date date not null, order_name varchar(255) not null, order_scheme_name varchar(255), planed_date_end date, price decimal(19,2), product_type varchar(255), customer_id bigint not null, manager_id bigint, primary key (id)) engine=InnoDB;
create table orders_sequence (next_val bigint) engine=InnoDB;
insert into orders_sequence values ( 25 );
create table product_type_dimension (product_type varchar(255) not null, dimensions integer not null, primary key (product_type)) engine=InnoDB;
create table specification_furniture (id bigint not null, amount integer not null, furniture_type varchar(255), product_type varchar(255), primary key (id)) engine=InnoDB;
create table specification_furniture_sequence (next_val bigint) engine=InnoDB;
insert into specification_furniture_sequence values ( 25 );
create table specification_material (id bigint not null, amount integer not null, product_type varchar(255), material_article varchar(255), primary key (id)) engine=InnoDB;
create table specification_materials_sequence (next_val bigint) engine=InnoDB;
insert into specification_materials_sequence values ( 25 );
create table specification_operation (id bigint not null, equipment_type varchar(255), operation varchar(255), operation_time datetime(6) not null, product_type varchar(255), primary key (id)) engine=InnoDB;
create table specification_operation_sequence (next_val bigint) engine=InnoDB;
insert into specification_operation_sequence values ( 25 );
create table specification_unit (id bigint not null, amount integer not null, product_type varchar(255), product_unit varchar(255), primary key (id)) engine=InnoDB;
create table specification_unit_sequence (next_val bigint) engine=InnoDB;
insert into specification_unit_sequence values ( 25 );
create table suppliers (id bigint not null, address varchar(255), delivery_period integer not null, supplier_name varchar(255) not null, primary key (id)) engine=InnoDB;
create table suppliers_sequence (next_val bigint) engine=InnoDB;
insert into suppliers_sequence values ( 25 );
create table user_role (user_id bigint not null, roles varchar(255)) engine=InnoDB;
create table user_sequence (next_val bigint) engine=InnoDB;
insert into user_sequence values ( 25 );
create table users (id bigint not null, first_name varchar(255), password varchar(255), patronymic varchar(255), photo_name varchar(255), surname varchar(255), username varchar(255), primary key (id)) engine=InnoDB;
alter table orders add constraint UK_hqtisynoemb5r5sht22uucj37 unique (order_name);
alter table specification_furniture add constraint UK_rx1hf1a9ye6m3dck4khi9lxe3 unique (furniture_type, product_type);
alter table specification_material add constraint UK_sh3acgam0lsq44wgs3tvjhxoj unique (material_article, product_type);
alter table specification_operation add constraint UK_ncj8o7sh7gsi3xlyq4jp32ko1 unique (product_type);
alter table specification_unit add constraint UK_68lxopd4sq8s6udaqq2g93edw unique (product_type, product_unit);
alter table suppliers add constraint UK_9g4pe49qq28884yknw05f33yy unique (supplier_name);
alter table users add constraint UK_sx468g52bpetvlad2j9y0lptc unique (password, username);
alter table users add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username);
alter table furnitures add constraint FKglitoxpwv5quqv6k5y73ihgsw foreign key (main_supplier_id) references suppliers (id) ON UPDATE CASCADE ON DELETE CASCADE ;
alter table materials add constraint FKfimrbaitqh8hvjjo95bqbjxwo foreign key (main_supplier_id) references suppliers (id) ON UPDATE CASCADE ON DELETE CASCADE ;
alter table orders add constraint FKsjfs85qf6vmcurlx43cnc16gy foreign key (customer_id) references users (id) ON UPDATE CASCADE ON DELETE CASCADE ;
alter table orders add constraint FK9qn4jar6kvccow7iyuo2mfuef foreign key (manager_id) references users (id) ON UPDATE CASCADE ON DELETE CASCADE ;
alter table specification_material add constraint FKed9d6uyvb51fdtvti5tj4i4d9 foreign key (material_article) references materials (article) ON UPDATE CASCADE ON DELETE CASCADE ;
alter table user_role add constraint FKj345gk1bovqvfame88rcx7yyx foreign key (user_id) references users (id) ON UPDATE CASCADE ON DELETE CASCADE ;