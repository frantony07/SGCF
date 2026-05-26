package org.ONE.model.services;

import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.model.entity.QuotaModel;
import org.ONE.model.repositories.CustomizerFactory;
import org.ONE.model.repositories.QuotaRepository;

import java.util.List;

public class QuotaService {
    private EntityManager entityManager = CustomizerFactory.getEntityManager();
    private QuotaRepository quotaRepository = new QuotaRepository(entityManager);

    public QuotaService() {};

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

    public QuotaModel findActiveQuotaById(Long employeeId) {
        try {
            return quotaRepository.findActiveQuotaById(employeeId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
