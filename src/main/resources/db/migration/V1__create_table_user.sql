CREATE TABLE IF NOT EXISTS users(
    uuid CHAR(36) PRIMARY KEY UNIQUE NOT NULL,
    name VARCHAR(200) NOT NULL,
    email VARCHAR(250) UNIQUE NOT NULL,
    birth_date DATE NOT NULL,
    password VARCHAR(60) NOT NULL,
    update_at TIMESTAMP NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE
);

DELIMITER //

DROP TRIGGER IF EXISTS update_at_insert_trigger//

CREATE TRIGGER update_at_insert_trigger BEFORE INSERT ON users
FOR EACH ROW
BEGIN
    SET NEW.update_at = NOW();
END;
//

DROP TRIGGER IF EXISTS update_at_update_trigger//

CREATE TRIGGER update_at_update_trigger BEFORE UPDATE ON users
FOR EACH ROW
BEGIN
    SET NEW.update_at = NOW();
END;
//

DELIMITER ;
