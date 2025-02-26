package com.esiitech.secure_payment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "MONETICO_TRANSACTIONS")
public class Transaction {

    @Id
    private String idTrx;
    private String dateTrx;
    private String heureTrx;
    private String origTrx;
    private String moyen;

    @JsonIgnore  // Ne pas envoyer ces champs dans la réponse JSON
    private String numPanEncrypted;

    @JsonIgnore
    private String dateFinEncrypted;

    @JsonIgnore
    private String cvxHashed;

    private Double montant;

    @Transient
    @JsonProperty("numPan")  // Masquer le PAN dans la réponse JSON
    public String getMaskedNumPan() {
        if (numPan == null || numPan.length() < 6) return "XXXX XXXX XXXX XXXX";
        return numPan.substring(0, 4) + "XX XXXX XXXX " + numPan.substring(numPan.length() - 4);
    }

    @Transient
    @JsonProperty("dateFin")  // Masquer la date de fin de validité
    public String getMaskedDateFin() {
        if (dateFin == null) return "XX/XX";
        return "**/**";
    }

    @Transient
    @JsonProperty("cvx")  // Ne jamais envoyer le CVX en clair
    public String getMaskedCvx() {
        return "***";
    }

    @Transient
    private String numPan; // Déchiffré pour affichage interne uniquement

    @Transient
    private String dateFin;

    @Transient
    private String cvx;

}
