alter table regions
    add constraint uq_regions unique (name, short_name);

insert into regions (name, short_name)
values ('Saint Petersburg', 'SPb'),
       ('Moscow', 'Msc'),
       ('Yekaterinburg', 'Yekb');
