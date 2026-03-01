INSERT INTO usuarios (name, email, password, role, create_date)
VALUES (
           'admin',
           'admin@gmail.com',
           '$2a$07$NQLkSMkW8Jij.SHCLUD/DuhfinNlBZoNOhDWIegM59dCfOGq5tg2u',
           'ADMIN',
            CURRENT_TIMESTAMP
       );


-- ======================
-- LANCHES
-- ======================

INSERT INTO product (name, description, price, category, available, featured, create_date)
VALUES ('X-Burger', 'Hamburguer artesanal com queijo', 25.90, 'LANCHES', true, true, CURRENT_TIMESTAMP);

INSERT INTO product (name, description, price, category, available, featured, create_date)
VALUES ( 'X-Salada', 'Hamburguer com queijo e salada', 27.90, 'LANCHES', true, false, CURRENT_TIMESTAMP);

INSERT INTO product (name, description, price, category, available, featured, create_date)
VALUES ('X-Bacon', 'Hamburguer com bacon crocante', 29.90, 'LANCHES', true, true, CURRENT_TIMESTAMP);

INSERT INTO product (name, description, price, category, available, featured, create_date)
VALUES ('Hot Dog Especial', 'Cachorro-quente completo', 18.90, 'LANCHES', true, false, CURRENT_TIMESTAMP);

INSERT INTO product (name, description, price, category, available, featured, create_date)
VALUES ('Sanduiche Natural', 'Frango com salada natural', 22.50, 'LANCHES', true, false, CURRENT_TIMESTAMP);


-- ======================
-- PIZZAS
-- ======================

INSERT INTO product (name, description, price, category, available, featured, create_date)
VALUES ('Pizza Calabresa', 'Calabresa com cebola', 49.90, 'PIZZAS', true, true, CURRENT_TIMESTAMP);

INSERT INTO product (name, description, price, category, available, featured, create_date)
VALUES ('Pizza Mussarela', 'Mussarela tradicional', 45.90, 'PIZZAS', true, false, CURRENT_TIMESTAMP);

INSERT INTO product (name, description, price, category, available, featured, create_date)
VALUES ('Pizza Portuguesa', 'Presunto, ovo e ervilha', 52.90, 'PIZZAS', true, true, CURRENT_TIMESTAMP);

INSERT INTO product (name, description, price, category, available, featured, create_date)
VALUES ('Pizza Frango com Catupiry', 'Frango desfiado com catupiry', 54.90, 'PIZZAS', true, false, CURRENT_TIMESTAMP);

INSERT INTO product (name, description, price, category, available, featured, create_date)
VALUES ( 'Pizza Quatro Queijos', 'Mix de quatro queijos', 56.90, 'PIZZAS', true, true, CURRENT_TIMESTAMP);


-- ======================
-- BEBIDAS
-- ======================

INSERT INTO product (name, description, price, category, available, featured, create_date)
VALUES ( 'Coca-Cola 2L', 'Refrigerante Coca-Cola 2 litros', 12.00, 'BEBIDAS', true, false, CURRENT_TIMESTAMP);

INSERT INTO product (name, description, price, category, available, featured, create_date)
VALUES ('Guarana Lata', 'Guarana 350ml', 5.50, 'BEBIDAS', true, false, CURRENT_TIMESTAMP);

INSERT INTO product (name, description, price, category, available, featured, create_date)
VALUES ( 'Suco Natural Laranja', 'Suco natural 500ml', 8.00, 'BEBIDAS', true, true, CURRENT_TIMESTAMP);

INSERT INTO product (name, description, price, category, available, featured, create_date)
VALUES ( 'Agua Mineral', 'Agua sem gas 500ml', 4.00, 'BEBIDAS', true, false, CURRENT_TIMESTAMP);

INSERT INTO product (name, description, price, category, available, featured, create_date)
VALUES ('Refrigerante Zero', 'Refrigerante sem acucar 2L', 13.00, 'BEBIDAS', true, false, CURRENT_TIMESTAMP);


-- ======================
-- SOBREMESAS
-- ======================

INSERT INTO product (name, description, price, category, available, featured, create_date)
VALUES ('Brownie', 'Brownie de chocolate', 12.90, 'SOBREMESAS', true, true, CURRENT_TIMESTAMP);

INSERT INTO product (name, description, price, category, available, featured, create_date)
VALUES ('Mousse de Maracuja', 'Mousse cremoso', 10.90, 'SOBREMESAS', true, false, CURRENT_TIMESTAMP);

INSERT INTO product (name, description, price, category, available, featured, create_date)
VALUES ('Pudim', 'Pudim tradicional', 9.90, 'SOBREMESAS', true, false, CURRENT_TIMESTAMP);

INSERT INTO product (name, description, price, category, available, featured, create_date)
VALUES ( 'Sorvete 2 bolas', 'Sorvete sabores variados', 14.00, 'SOBREMESAS', true, true, CURRENT_TIMESTAMP);

INSERT INTO product (name, description, price, category, available, featured, create_date)
VALUES ( 'Petit Gateau', 'Bolo quente com sorvete', 18.90, 'SOBREMESAS', true, true, CURRENT_TIMESTAMP);