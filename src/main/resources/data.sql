ALTER TABLE regions
    ADD CONSTRAINT uq_regions UNIQUE (name, short_name);

insert into regions (name, short_name)
values ('Saint Petersburg', 'SPb'),
       ('Moscow', 'Msc');
