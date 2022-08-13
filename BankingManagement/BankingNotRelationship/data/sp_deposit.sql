DELIMITER //
DROP PROCEDURE IF EXISTS `sp_deposits` //
CREATE PROCEDURE `sp_deposits`(
	IN customer_id BIGINT,
    IN transaction_amount DECIMAL(12,0),
    OUT message varchar(255)
    )
BEGIN 
 		DECLARE count_id INT;
        
		DECLARE check_rollback BOOLEAN DEFAULT FALSE;
        DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET check_rollback = TRUE;
        
		SET count_id = (SELECT COUNT(*) FROM customers c WHERE c.id = customer_id);
		IF count_id = 0
		THEN
			SET message = 'Unknown customer';
		ELSE IF transaction_amount < 1 OR transaction_amount > 100000000000
        THEN
			SET message = 'Transaction amount greater than 1.000 and not exceeding 100.000.000.000';
			ELSE
				START TRANSACTION;
					UPDATE customers c
					SET c.balance = c.balance + transaction_amount
					WHERE c.id = customer_id;
		
					INSERT INTO deposits (customer_id, created_at, transaction_amount)
					VALUES (customer_id, NOW(), transaction_amount);
                    IF (check_rollback) 
                    THEN
						SET message = 'System error! Please contact the administrator';	
						ROLLBACK;
					ELSE
						SET message = 'Deposist Completed';	
						COMMIT;
					END IF;
			END IF;
		END IF;
        COMMIT;
 	END //
    DELIMITER ;
    
    CALL sp_deposits(3, 12345600, @mess);
    SELECT @mess;