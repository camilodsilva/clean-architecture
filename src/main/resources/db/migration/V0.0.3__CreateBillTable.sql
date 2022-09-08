create table bill (
    bill_idt integer not null,
    customer_fky integer not null,
    discount_fky integer default null,
    amount decimal(10,2) not null,
    amount_with_discount decimal(10,2) not null,
    generated_points integer,
    location varchar(100) not null,
    created_at timestamp with time zone not null,
    updated_at timestamp with time zone not null
);

create sequence sq_bill start with 1 increment by 1 minvalue 1 maxvalue 99999999;

alter table bill add constraint bill_idt_pk primary key (bill_idt);
alter table bill add constraint bill_customer_fk foreign key (customer_fky) references customer(customer_idt) on delete restrict;
alter table bill add constraint bill_discount_fk foreign key (discount_fky) references discount(discount_idt) on delete restrict;
alter table bill alter column bill_idt set default nextval('sq_bill');
