-- Payments table
CREATE TABLE payments (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    user_id BIGINT NOT NULL,
    workshop_id BIGINT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL
        CONSTRAINT chk_status CHECK (status IN ('PENDING', 'SUCCESS', 'FAILED')),
    payment_reference VARCHAR(100) NULL, -- mock payment id
    created_at DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
    updated_at DATETIME2 NOT NULL DEFAULT SYSDATETIME()
);


CREATE TRIGGER trg_UpdatePayments
ON payments
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE payments
    SET updated_at = SYSDATETIME()
    WHERE id IN (SELECT DISTINCT id FROM inserted);
END;
