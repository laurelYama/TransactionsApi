package com.esiitech.secure_payment.service;

import com.esiitech.secure_payment.chiffrement.CryptoUtils;
import com.esiitech.secure_payment.entity.Transaction;
import com.esiitech.secure_payment.repository.TransactionRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction saveTransaction(Transaction transaction) {
        try {
            transaction.setNumPanEncrypted(CryptoUtils.encrypt(transaction.getNumPan()));
            transaction.setDateFinEncrypted(CryptoUtils.encrypt(transaction.getDateFin()));
            transaction.setCvxHashed(CryptoUtils.encrypt(transaction.getCvx()));
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du chiffrement des données", e);
        }

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll().stream().map(transaction -> {
            try {
                if (transaction.getNumPanEncrypted() != null) {
                    transaction.setNumPan(CryptoUtils.decrypt(transaction.getNumPanEncrypted()));
                }
                if (transaction.getDateFinEncrypted() != null) {
                    transaction.setDateFin(CryptoUtils.decrypt(transaction.getDateFinEncrypted()));
                }
                if (transaction.getCvxHashed() != null) {
                    transaction.setCvx(CryptoUtils.decrypt(transaction.getCvxHashed()));
                }
            } catch (Exception e) {
                throw new RuntimeException("Erreur lors du déchiffrement des données", e);
            }
            return transaction;
        }).collect(Collectors.toList());
    }

}
