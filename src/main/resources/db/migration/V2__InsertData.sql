INSERT INTO brands (brand_name)
VALUES ('Gorenje'), ('Samsung'), ('Bosch');

INSERT INTO types (type_name)
VALUES ('Пральна машина'), ('Телевізор'), ('Холодильник');

INSERT INTO products (model, description_ua, description_pl, price, availability, image, brand_id, type_id)
VALUES
    ('Gorenje SensoCare 6Kg', 'Пральна машину Gorenje SensoCare в робочому стані. По корпусу є пару рижиків. В ремонті не була. Працює відмінно', 'Latest Apple smartphonePL', 4999.99, TRUE, 'https://main-cdn.sbermegamarket.ru/big1/hlr-system/-18/211/579/512/121/913/600009617844b0.jpeg', 1, 1),
    ('Samsung QLED 2K', 'Вживаний телевізор. Стан 10\10, уживався 2 місяці', 'Latest Samsung smartphonePL', 1299.99, TRUE, 'https://www.mediaexpert.pl/media/cache/resolve/gallery/images/54/5436316/Telewizor-SAMSUNG-CU7192-skos-1.jpg', 2, 1),
    ('Bosch KGN 33', 'Холодильник в робочому cтані', 'Latest Sony TelevisionPL', 3199.99, TRUE, 'https://a.allegroimg.com/original/1153bd/a306fa4940098dd2ac982b6cc01a/Lodowka-Bosch-KGN-33NLEB-176cm-282L-NO-FROST', 3, 3);

INSERT INTO admins (username ,password, role)
VALUES ('adminUs', 'adminPas', 'ADMIN');