create table company_category
(
	cc_id int not null,
	stock_id varchar(25) not null,
	source varchar(10),
	category varchar(10),
	other_category varchar(10)
);

alter table company_category modify cc_id int auto_increment primary key;