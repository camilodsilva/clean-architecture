create table discount (
    discount_idt integer not null,
    amount decimal(10,2) not null,
    points integer not null,
    status varchar(12) not null,
    created_at timestamp with time zone not null,
    updated_at timestamp with time zone not null
);

create sequence sq_discount start with 1 increment by 1 minvalue 1 maxvalue 99999999;

alter table discount add constraint discount_idt_pk primary key (discount_idt);
alter table discount alter column discount_idt set default nextval('sq_discount');
