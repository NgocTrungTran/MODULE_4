-- procedure deposits
delimiter //
 DROP PROCEDURE IF EXISTS deposits //
 CREATE PROCEDURE deposits(
	IN customer_id BIGINT,
    IN transaction_amount DECIMAL(12,0),
    OUT message varchar(255)
    )
 	BEGIN
 		DECLARE count_id INT;
		SET count_id = (SELECT COUNT(*) FROM customers c WHERE c.id = customer_id);
		IF(count_id = 0)
		THEN
			SET message = 'Unknown customer id';
		ELSE IF (transaction_amount < 1)
        THEN
			SET message = 'Transaction amount not enough';
			ELSE
				UPDATE customers c
				SET c.balance = c.balance + transaction_amount
				WHERE c.id = customer_id;
			
				INSERT INTO deposits (customer_id, created_at, transaction_amount)
				VALUES (customer_id, NOW(), transaction_amount);
				
						SET message = 'Deposist Completed';
			END IF;
		END IF;
 	END;//
 delimiter ;
call deposits(8, 40000000000, @mess);
SELECT @mess;


-- procedure Withdraws
delimiter //
DROP PROCEDURE IF EXISTS withdraw //
 CREATE PROCEDURE withdraw(
	IN customer_id BIGINT,
    IN transaction_amount DECIMAL(12,0),
    OUT message varchar(255)
    )
 	BEGIN
 		DECLARE current_balance DECIMAL(12,0);
		DECLARE count_id INT;
		SELECT c.balance INTO current_balance FROM customers AS c
		WHERE c.id = customer_id;
		SET count_id = (SELECT COUNT(*) FROM customers c WHERE c.id = customer_id);
		IF(count_id = 0)
		THEN
			SET message = 'Unknown customer id';
			ELSE IF (transaction_amount < 1)
			   THEN
				  SET message = 'Transaction amount not enough';
			ELSE IF (transaction_amount > current_balance)
				THEN
				  SET message = 'Transaction amount lager than balance';
			ELSE
				UPDATE customers c
				  SET c.balance = c.balance - transaction_amount
					 WHERE c.id = customer_id ;
					
					INSERT INTO withdraws(created_at, customer_id, transaction_amount)
					VALUES (NOW(), customer_id, transaction_amount);
					
					SET message = 'Withdraw completed';
				END IF;
			END IF;
		END IF;
 	END;//
 delimiter ;
 CALL withdraw(-1, 20000000, @mess);
 SELECT @mess;
 
 -- procedure transfer
 delimiter //
 DROP PROCEDURE IF EXISTS transfer //
 CREATE PROCEDURE transfer(
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
						UPDATE customers c
						SET c.balance = c.balance - transaction_amount
						WHERE c.id = sender_id;
						
						UPDATE customers c
						SET c.balance = c.balance + transfer_amount
						WHERE c.id = recipient_id;
						
						INSERT INTO transfers (created_at, fees_amount, transaction_amount, transfer_amount, recipient_id, sender_id)
						VALUES (NOW(), fees_amount, transaction_amount, transfer_amount, recipient_id, sender_id);
						
						SET message = 'Transfer Completed';	
					END IF;
				END IF;
			END IF;
		END IF;
 	END;//
 delimiter ;
 CALL transfer(4, 5, 50000000, @mess);
 SELECT @mess;