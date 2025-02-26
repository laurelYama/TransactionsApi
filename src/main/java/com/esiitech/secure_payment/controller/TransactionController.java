package com.esiitech.secure_payment.controller;

import com.esiitech.secure_payment.entity.Transaction;
import com.esiitech.secure_payment.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transactions", description = "API pour la gestion des transactions")  // 🔹 Ajoute un tag Swagger
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/save")
    @Operation(summary = "Enregistre une transaction", description = "Stocke une nouvelle transaction avec chiffrement des données sensibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction enregistrée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur serveur")
    })
    public Transaction saveTransaction(@RequestBody Transaction transaction) {
        return transactionService.saveTransaction(transaction);
    }

    @GetMapping("/all")
    @Operation(summary = "Récupère toutes les transactions", description = "Retourne la liste des transactions avec les données déchiffrées")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des transactions récupérée avec succès"),
            @ApiResponse(responseCode = "500", description = "Erreur serveur")
    })
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}
