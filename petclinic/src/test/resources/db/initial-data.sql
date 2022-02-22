insert into owner(owner_id, owner_address, owner_first_name, owner_last_name, owner_telephone)
    values (1, 'Budapest', 'Joseph', 'Barbera', '12345');
insert into owner(owner_id, owner_address, owner_first_name, owner_last_name, owner_telephone)
    values (2, 'Budapest', 'William', 'Hanna', '67890');

insert into type(type_id, type_name)
    values (111, 'Cat');
insert into type(type_id, type_name)
    values (222, 'Mouse');

insert into pet(pet_id, pet_birth_date, pet_name, owner_id, type_id)
    values (11, current_timestamp, 'Tom', 1, 111);
insert into pet(pet_id, pet_birth_date, pet_name, owner_id, type_id)
    values (22, current_timestamp, 'Jerry', 2, 222);

