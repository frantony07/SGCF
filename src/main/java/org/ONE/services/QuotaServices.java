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

    public void createQuota(QuotaModel quotaModel) {
        try {
            if(quotaModel == null) {
                throw new RuntimeException("A meta não pode ser nula");
            }
            quotaRepository.create(quotaModel);
        } catch (Exception err) {
            PrintError.printErro(err);
        }
    }

    public void updateQuota(QuotaModel quotaModel) {
        try {
            if (quotaModel == null) {
                throw new RuntimeException("A quota não pode ser nula");
            }
            quotaRepository.update(quotaModel);
        } catch (Exception err) {
            PrintError.printErro(err);
        }
    }

    public void deleteQuota(QuotaModel quotaModel) {
        try {
            if (quotaModel == null) {
                throw new RuntimeException("A quota não pode ser nula");
            }
            quotaRepository.delete(quotaModel);
        } catch (Exception err) {
            PrintError.printErro(err);
        }
    }

    public void quickGetEmployeeQuota() {
        EntityManager em = CustomizerFactory.getEntityManager();

        try {
            quotaRepository
        } catch (Exception err) {
            PrintError.printErro(err);
        }
    }

    public List<QuotaModel> findAllQuotas() {
        try {
            return quotaRepository.findAll();
        } catch (Exception err) {
            PrintError.printErro(err);
        }
        return List.of();
    }

    public QuotaModel findQuotaById(Long id) {
        try {
            return quotaRepository.findById(id);
        } catch (Exception err) {
            PrintError.printErro(err);
        }
        return null;
    }
}
