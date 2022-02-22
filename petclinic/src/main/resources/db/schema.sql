create schema if not exists clinic;

create table if not exists clinic.owner
(
    owner_id          serial primary key,
    owner_address     text not null,
    owner_first_name  text not null,
    owner_last_name   text not null,
    owner_telephone   text not null
);

create table if not exists clinic.type
(
    type_id      serial primary key ,
    type_name    text not null,
        constraint  uk_type_name
            unique (type_name)
);

create table if not exists clinic.pet
(
    pet_id          serial primary key ,
    pet_birth_date  timestamp not null,
    pet_name     text not null,
    owner_id  int not null,
    type_id   int not null,
        constraint uk_pet
            unique (pet_name, pet_birth_date, owner_id, type_id),
        constraint fk7qfti9yba86tgfe9oobeqxfxg
            foreign key (owner_id) references clinic.owner,
        constraint fke575pwyhy4o7k8wci2hmoj1a2
            foreign key (type_id) references clinic.type
);
