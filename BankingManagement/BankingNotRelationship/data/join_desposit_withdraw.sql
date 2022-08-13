-- CREATE VIEW desposit_withdraw AS
SELECT * FROM (
SELECT
	d.id,
    cus.id  AS customer_id,
    d.created_at,
    cus.full_name,
    cus.email,
    cus.balance,
    d.transaction_amount,
    'DEPOSITS' AS `transaction`
FROM deposits AS d
LEFT JOIN customers AS cus 
ON cus.id = d.customer_id 
UNION ALL SELECT
	wd.id,
    cus.id AS customer_id,
    wd.created_at,
    cus.full_name,
    cus.email,
    cus.balance,
    wd.transaction_amount,
    'WITHDRAWS' AS `transaction`
FROM withdraws AS wd
LEFT JOIN customers AS cus 
ON cus.id = wd.customer_id
) result
ORDER BY result.created_at ASC;

-- SELECT * FROM desposit_withdraw
-- ORDER BY created_at ASC;