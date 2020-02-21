
DROP SCHEMA IF EXISTS bookstore CASCADE;
CREATE SCHEMA if NOT EXISTS bookstore;

CREATE TABLE bookstore.author
(
  author_id UUID PRIMARY KEY NOT NULL,
  first_name VARCHAR(255),
  last_name VARCHAR(255) NOT NULL
);

CREATE TABLE bookstore.publisher
(
  publisher_id UUID PRIMARY KEY NOT NULL,
  country VARCHAR(255) NOT NULL
);

CREATE TABLE bookstore.customer
(
  customer_id UUID PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  street_name VARCHAR(255) NOT NULL,
  street_number VARCHAR(255) NOT NULL,
  postal_code VARCHAR(50) NOT NULL ,
  province VARCHAR(50),
  country VARCHAR(100),
  phone INT NOT NULL
);

CREATE TABLE bookstore.book
(
  book_id UUID PRIMARY KEY,
  author_id UUID NOT NULL,
  publisher_id UUID NOT NULL,
  publish_date bigint,
  title VARCHAR(255),
  ISBN VARCHAR(20),
  genre VARCHAR(100),
  type VARCHAR(100),
  publication_year INT,
  price NUMERIC DEFAULT 0.0,
  FOREIGN KEY (author_id) REFERENCES bookstore.author(author_id),
  FOREIGN KEY (publisher_id) REFERENCES bookstore.publisher(publisher_id)
);

CREATE TABLE bookstore.order
(
  order_id UUID PRIMARY KEY,
  book_id UUID NOT NULL,
  quantity INT NOT NULL,
  price NUMERIC NOT NULL,
  FOREIGN KEY (book_id) REFERENCES bookstore.book(book_id)
);

CREATE TABLE bookstore.transit
(
  order_id UUID NOT NULL,
  customer_id UUID NOT NULL,
  order_date TIMESTAMP NOT NULL,
  subtotal NUMERIC,
  shipping VARCHAR(255),
  PRIMARY KEY (order_id, customer_id),
  FOREIGN KEY (order_id) REFERENCES bookstore.order(order_id),
  FOREIGN KEY (customer_id) REFERENCES bookstore.customer(customer_id)
);
CREATE UNIQUE INDEX orders_order_id_uindex ON bookstore.transit (order_id);
CREATE UNIQUE INDEX orders_customer_id_uindex ON bookstore.transit (customer_id);
CREATE UNIQUE INDEX book_id_uindex ON bookstore.book USING BTREE(book_id);