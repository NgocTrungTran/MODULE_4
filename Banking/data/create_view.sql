-- Tạo VIEW hiển thị lịch sử chuyển khoản, trong đó có ID và tên người gửi, ID và tên người nhận, và các thông tin giao dịch.

drop view showHistoryTransfers;
create view showHistoryTransfers as
select tran.recipient_id,
       rec.full_name as recipientName,
       tran.sender_id,
       sen.full_name as senderName,
       tran.created_at,
       tran.created_by,
       tran.updated_at,
       tran.updated_by,
       tran.fees,
       tran.fees_amount,
       tran.transaction_amount,
       tran.transfer_amount
from transfers as tran
         join customers as rec on rec.id = tran.recipient_id
         join customers as sen on sen.id = tran.sender_id;

select *
from showHistoryTransfers;