create table industry_classification
(
	ic_id int not null,
	source varchar(10),
	category_0 varchar(10),
	category_1 varchar(10),
	category_2 varchar(10),
	category_3 varchar(10),
	category_4 varchar(10),
	description varchar(1000),
	other_description varchar(1000)
);

alter table industry_classification modify ic_id int auto_increment primary key;
