
create table if not exists UTILISATEUR(
    id serial PRIMARY KEY,
    email varchar(50) not null,
    role varchar(50) not null constraint c_role check (role IN ('ADMIN','RECEPTION','MECANICIEN'))
);
commit;

create table if not exists CLIENT(
    id serial PRIMARY KEY,
    name varchar(100) not null,
    phone varchar(20) null,
    email varchar(50) null
);
commit;

create table if not exists MOTO(
    id serial PRIMARY KEY,
    marque varchar(50) not null,
    modele varchar(50) not null,
    annee varchar(4) not null,
    immatriculation varchar(15) not null,
    client_id integer references CLIENT(id)
);
commit;

create table if not exists BON_REPARATION(
    id serial PRIMARY KEY,
    reference varchar(15) not null,
    description_probleme varchar(150) not null,
    statut varchar constraint c_statut check (statut IN ('CREÉ', 'EN_COURS', 'EN_ATTENTE_PIECES', 'TERMINÉ', 'LIVRÉ')),
    priorite varchar constraint c_priorite check (priorite IN ('BASSE', 'NORMALE', 'URGENTE')),
    date_creation timestamp default now(),
    date_mise_a_jour timestamp default null,
    client_id integer references CLIENT(id),
    moto_id integer references MOTO(id),
    mecanicien_id integer references UTILISATEUR(id)

);