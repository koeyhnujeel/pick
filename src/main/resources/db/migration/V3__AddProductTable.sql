CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    price INT NOT NULL,
    description VARCHAR(50) NOT NULL,
    stock INT NOT NULL,
    mainImage VARCHAR(100) NOT NULL,
    mainThumbnail VARCHAR(100) NOT NULL,
    detailImage VARCHAR(100) NOT NULL,
    category VARCHAR(10) NOT NULL,
    status VARCHAR(10) NOT NULL,
    created_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT category CHECK (category IN ('TOP', 'PANTS', 'SHOES')),
    CONSTRAINT status CHECK (status IN ('IN_STOCK', 'SOLD_OUT'))
);
