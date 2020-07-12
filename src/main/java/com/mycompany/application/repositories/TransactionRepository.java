package com.mycompany.application.repositories;

import com.mycompany.application.entities.Transaction;
import com.mycompany.application.enums.TransactionStatusEnum;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TransactionRepository {
    private Session session;

    public TransactionRepository(Session session) {
        this.session = session;
    }

    public List<Transaction> getTransactions(
            List<Integer> statuses,
            Optional<String> optionalUsername,
            Optional<Date> optionalStartOnDate,
            Optional<Date> optionalEndOnDate
    ) throws Exception {
        CriteriaBuilder criteria = session.getCriteriaBuilder();
        CriteriaQuery<Transaction> transactionCriteriaQuery = criteria.createQuery(Transaction.class);
        Root<Transaction> root = transactionCriteriaQuery.from(Transaction.class);
        transactionCriteriaQuery
                .select(root);

        List<Predicate> predicates = new ArrayList<Predicate>();
        List<Predicate> transactionStatusPredicates = new ArrayList<Predicate>();

        for (Integer status: statuses) {
            transactionStatusPredicates.add(criteria.equal(root.get("status"), status));
        }

        predicates
                .add(criteria.or(transactionStatusPredicates
                        .toArray(new Predicate[transactionStatusPredicates.size()])));

        if (optionalUsername.isPresent()) {
            predicates.add(criteria.like(root.get("username"), "%" + optionalUsername.get() +"%"));
        }

        if (optionalStartOnDate.isPresent() && optionalEndOnDate.isPresent()) {
            predicates.add(criteria.between(
                    root.get("startOn"),
                    optionalStartOnDate.get(),
                    optionalEndOnDate.get()
            ));
        }

        transactionCriteriaQuery
                .where(criteria.and(predicates.toArray(new Predicate[predicates.size()])));

        Query<Transaction> transactionQuery = session.createQuery(transactionCriteriaQuery);
        return transactionQuery.getResultList();
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
