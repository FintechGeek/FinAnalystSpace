create table company_profile
(
 cp_id int not null;
 stock_id varchar(25) not null,
 effective datetime not null,
 company_name varchar(200),
 other_namevarchar(200),
 industry_code varchar(30),
 industry_full_code varchar(30) 
)
alter table company_profile modify cp_id int auto_increment primary key;
alter table company_profile add index(stock_id); 
