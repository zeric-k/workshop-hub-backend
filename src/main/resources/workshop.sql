
-- Workshop Table
CREATE TABLE workshop (
    id INT IDENTITY(1,1) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    level VARCHAR(50),
    category VARCHAR(100),
    instructor VARCHAR(100),
    spaceId INT NOT NULL,
    link VARCHAR(255),
    createdAt DATETIME DEFAULT GETDATE(),
    updatedAt DATETIME DEFAULT GETDATE(),
    updatedBy VARCHAR(50) DEFAULT 'SYSTEM',
    CONSTRAINT fk_space FOREIGN KEY (spaceId) REFERENCES space(id)
);
