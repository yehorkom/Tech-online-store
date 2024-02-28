CREATE TABLE Brands (
                        BrandID SERIAL PRIMARY KEY,
                        Name TEXT NOT NULL
);

CREATE TABLE Types (
                       TypeID SERIAL PRIMARY KEY,
                       Name TEXT NOT NULL
);

CREATE TABLE Products (
                          ProductID SERIAL PRIMARY KEY,
                          Model TEXT NOT NULL,
                          Description TEXT,
                          Price NUMERIC CHECK (Price > 0),
                          Availability BOOLEAN,
                          Image TEXT,
                          BrandID INTEGER,
                          TypeID INTEGER,
                          FOREIGN KEY (BrandID) REFERENCES Brands(BrandID),
                          FOREIGN KEY (TypeID) REFERENCES Types(TypeID)
);