package rdc.ericwangi.banking.models;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public enum TransactionType {
    TRANSFERT,
    DEPOT,
    RETRAIT
}
