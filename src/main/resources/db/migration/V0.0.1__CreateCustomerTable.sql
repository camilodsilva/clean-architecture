create table customer (
    customer_idt integer not null,
    name varchar(100) default null,
    last_name varchar(100) default null,
    email varchar(100) not null,
    pass varchar(255) not null,
    profile varchar(16) not null,
    created_at timestamp with time zone not null,
    updated_at timestamp with time zone not null
);

create sequence sq_customer start with 1 increment by 1 minvalue 1 maxvalue 99999999;

alter table customer add constraint customer_idt_pk primary key (customer_idt);
alter table customer alter column customer_idt set default nextval('sq_customer');
