INSERT INTO brands (brand_name)
VALUES ('Apple'), ('Samsung'), ('Sony');

INSERT INTO types (type_name)
VALUES ('Smartphone'), ('Laptop'), ('Television');

INSERT INTO products (model, description_ua, description_pl, price, availability, image, brand_id, type_id)
VALUES
    ('iPhone 13', 'Latest Apple smartphoneUA', 'Latest Apple smartphonePL', 999.99, TRUE, 'image_url', 1, 1),
    ('Galaxy S21', 'Latest Samsung smartphoneUA', 'Latest Samsung smartphonePL', 799.99, TRUE, 'image_url', 2, 1),
    ('Bravia XR', 'Latest Sony TelevisionUA', 'Latest Sony TelevisionPL', 1199.99, TRUE, 'image_url', 3, 3);

INSERT INTO admins (username ,password, role)
VALUES ('adminUs', 'adminPas', 'ADMIN');