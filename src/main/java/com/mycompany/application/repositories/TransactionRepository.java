package com.mycompany.application.repositories;

import com.mycompany.application.entities.Transaction;
import com.mycompany.application.enums.TransactionStatusEnum;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class TransactionRepository {
    private Session session;

    public TransactionRepository(Session session) {
        this.session = session;
    }

    public List<Transaction> getTransactions() {
        Query query = session.createQuery("from Transaction");
        List<Transaction> transactions = query.getResultList();
        return transactions;
    }

    public List<Transaction> getTransactionsByStatusNot(Integer status) {
        Query query = session.createQuery("from Transaction T where T.status != :status");
        query.setParameter("status", status);
        List<Transaction> transactions = query.getResultList();
        return transactions;
    }

    public Transaction changeTransactionStatusToPaid(
            Transaction transaction,
            Double amountPaidByClient,
            Double amountChange
    ) {
        transaction.setAmountChange(amountChange);
        transaction.setAmountPaidByClient(amountPaidByClient);
        transaction.setStatus(TransactionStatusEnum.PAID.value);
        session.update(transaction);

        return transaction;
    }
}
