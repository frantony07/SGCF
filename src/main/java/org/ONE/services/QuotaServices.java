package org.ONE.services;

import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.models.QuotaModel;
import org.ONE.repositories.CustomizerFactory;
import org.ONE.repositories.QuotaRepository;

import java.util.List;

public class QuotaServices {
    private EntityManager entityManager = CustomizerFactory.getEntityManager();
    private QuotaRepository quotaRepository = new QuotaRepository(entityManager);

    public QuotaServices() {};

    public void createQuota (QuotaModel quotaModel) {
        try {
            if (quotaModel == null) {
                throw new RuntimeException("A meta não pode ser nula");
            }
            quotaRepository.createQuota(quotaModel);
        } catch (Exception err) {
            PrintError.printErro(err);
        }
    }

    public void updateQuota (QuotaModel quotaModel) {
        try {
            if (quotaModel == null) {
                throw new RuntimeException("A meta não pode ser nula");
            }
            quotaRepository.updateQuota(quotaModel);
        } catch (Exception err) {
            PrintError.printErro(err);
        }
    }

    public void deleteQuota (QuotaModel quotaModel) {
        try {
            if (quotaModel == null) {
                throw new RuntimeException("A meta não pode ser nula");
            }
            quotaRepository.deleteQuota(quotaModel);
        } catch (Exception err) {
            PrintError.printErro(err);
        }
    }

    public void quickGetCompanyQuota (QuotaModel quotaModel) {
        try {
            if (quotaModel == null) {
                System.out.println("Erro, não há uma meta ativa");
                return;
            }
            // Gotta work on this one
        } catch (Exception err) {
            PrintError.printErro(err);
        }
    }

    public void quickGetEmployeeQuota() {
        EntityManager em = CustomizerFactory.getEntityManager();

        try {
            // Working on it
        } catch (Exception err) {
            PrintError.printErro(err);
        }
    }

    public List<QuotaModel> findAllQuotas() {
        try {
            return quotaRepository.findAllQuotas();
        } catch (Exception err) {
            PrintError.printErro(err);
        }
        return List.of();
    }

    public QuotaModel findQuotaById (Long id) {
        try {
            return quotaRepository.findById(id);
        } catch (Exception err) {
            PrintError.printErro(err);
        }
        return null;
    }

    public Long getQuotaSize() {
        try {
            return quotaRepository.getSize();
        } catch (Exception err) {
            PrintError.printErro(err);
        }
        return null;
    }
}
