CREATE TABLE brands (
                        brand_id SERIAL PRIMARY KEY,
                        brand_name TEXT NOT NULL
);

CREATE TABLE types (
                       type_id SERIAL PRIMARY KEY,
                       type_name TEXT NOT NULL
);

CREATE TABLE products (
                          product_id SERIAL PRIMARY KEY,
                          model TEXT NOT NULL,
                          description_ua TEXT,
                          description_pl TEXT,
                          price NUMERIC CHECK (Price > 0),
                          availability BOOLEAN,
                          image TEXT,
                          brand_id INTEGER,
                          type_id INTEGER,
                          FOREIGN KEY (brand_id) REFERENCES brands(brand_id),
                          FOREIGN KEY (type_id) REFERENCES types(type_id)
);

CREATE TABLE admins (
                        admin_id SERIAL PRIMARY KEY,
                        username TEXT NOT NULL,
                        password TEXT NOT NULL,
                        role TEXT
);