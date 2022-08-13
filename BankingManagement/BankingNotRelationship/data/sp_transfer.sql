DELIMITER //
DROP PROCEDURE IF EXISTS banking_management_not_relationship.sp_transfer //
CREATE PROCEDURE sp_transfer(
	IN sender_id BIGINT,
    IN recipient_id BIGINT,
    IN transfer_amount DECIMAL(12,0),
    OUT message varchar(255)
    )
BEGIN
 		DECLARE current_sender_balance DECIMAL(12,0);
        DECLARE transaction_amount DECIMAL(12,0);
        DECLARE fees_amount DECIMAL(12,0);
        DECLARE count_sender_id int;
        DECLARE count_recipient_id int;
        DECLARE check_rollback BOOLEAN DEFAULT FALSE;
        
        DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET check_rollback = TRUE;
        
        
        SET fees_amount = transfer_amount * 0.1;
        SET transaction_amount = transfer_amount + fees_amount;
        SET count_sender_id = (SELECT COUNT(*) FROM customers c WHERE c.id = sender_id);
        SET count_recipient_id = (SELECT COUNT(*) FROM customers c WHERE c.id = recipient_id);
        SELECT c.balance INTO current_sender_balance  FROM customers c
		WHERE c.id = sender_id;
        IF(count_sender_id = 0 OR count_recipient_id = 0)
        THEN
			SET message = 'Unknown id';
        ELSE
			IF(sender_id = recipient_id)
            THEN
				SET message = "Can't transfer to same account";
			ELSE
				IF(transaction_amount > current_sender_balance)
                THEN
					SET message = "Balance not enough";
				ELSE
					IF (transaction_amount < 1)
                    THEN
						SET message = "Transfer amount not enough ";
					ELSE
                    START TRANSACTION;
						UPDATE customers c
						SET c.balance = c.balance - transaction_amount
						WHERE c.id = sender_id;
						
						UPDATE customers c
						SET c.balance = c.balance + transfer_amount
						WHERE c.id = recipient_id;
						
						INSERT INTO transfers (created_at, fees_amount, transaction_amount, transfer_amount, recipient_id, sender_id)
						VALUES (NOW(), fees_amount, transaction_amount, transfer_amount, recipient_id, sender_id);
						IF (check_rollback) THEN
							SET message = 'System error!';	
							ROLLBACK;
                        ELSE
							SET message = 'Transfer Completed';	
							COMMIT;
                        END IF;
					END IF;
				END IF;
			END IF;
		END IF;
 	END //
DELIMITER ;

CALL banking_management_not_relationship.sp_transfer(1, 2, 10000, @mess);
SELECT @mess;