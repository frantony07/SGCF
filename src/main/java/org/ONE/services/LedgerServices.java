package org.ONE.services;

import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.repositories.CustomizerFactory;
import org.ONE.repositories.LedgerRepository;

public class LedgerServices {
    public void quickGetLedger() {
        EntityManager em = CustomizerFactory.getEntityManager();
        int count = 0;
        try {
            if (new LedgerRepository(em).getCount() != null) {
                for (long i = new LedgerRepository(em).getCount() - 1;
                     i >= 0 && count < 5;
                     i--) {
                    new LedgerRepository(em).findAll().get((int) i);
                    count++;
                }
            }
        } catch (Exception err) {
            PrintError.printErro(err);
        }
    }
}
