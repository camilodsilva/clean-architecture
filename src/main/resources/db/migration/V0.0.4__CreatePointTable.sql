create table point (
    point_idt integer not null,
    customer_fky integer not null,
    total_points integer not null,
    total_amount decimal(10,2) not null,
    created_at timestamp with time zone not null,
    updated_at timestamp with time zone not null
);

create sequence sq_point start with 1 increment by 1 minvalue 1 maxvalue 99999999;

alter table point add constraint point_idt_pk primary key (point_idt);
alter table point add constraint point_customer_fk foreign key (customer_fky) references customer(customer_idt) on delete restrict;
alter table point alter column point_idt set default nextval('sq_point');

