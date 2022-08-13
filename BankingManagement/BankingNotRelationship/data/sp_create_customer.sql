DELIMITER //
DROP PROCEDURE IF EXISTS `banking_management_not_relationship`.`create_customer` //
CREATE PROCEDURE `banking_management_not_relationship`.`create_customer`(
	IN full_name varchar(255),
    IN email varchar(255),
    IN phone varchar(255),
    IN address varchar(255),
    OUT message varchar(255)
    )
BEGIN 
		DECLARE check_rollback BOOLEAN DEFAULT FALSE;
        DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET check_rollback = TRUE;
				START TRANSACTION;
					INSERT INTO customers(
						full_name,
						created_at,
						email,
						phone,
						address)
					VALUES(
						full_name,
						now(),
						email,
						phone,
						address);
				IF (check_rollback) 
                    THEN
						SET message = 'System error! Please contact the administrator';	
						ROLLBACK;
				ELSE
						SET message = 'New customer created successfully';	
						COMMIT;
				END IF;
        COMMIT;
 	END //
    DELIMITER ;
    
    CALL `banking_management_not_relationship`.`create_customer`("Milo","dsad@gmail.com","0132659874","Dong hoi", @mess);
    SELECT @mess;