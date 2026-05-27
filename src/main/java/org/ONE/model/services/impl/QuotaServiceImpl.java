package org.ONE.model.services.impl;

import Functions.PrintError;
import jakarta.persistence.EntityManager;
import org.ONE.model.entity.QuotaModel;
import org.ONE.model.repositories.CustomizerFactory;
import org.ONE.model.repositories.QuotaRepository;
import org.ONE.model.services.QuotaService;

import java.util.List;

public class QuotaServiceImpl implements QuotaService {
    private EntityManager entityManager = CustomizerFactory.getEntityManager();
    private QuotaRepository quotaRepository = new QuotaRepository(entityManager);

    public QuotaServiceImpl() {}

    @Override
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

    @Override
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

    @Override
    public List<QuotaModel> findAllQuotas() {
        try {
            return quotaRepository.findAllQuotas();
        } catch (Exception err) {
            PrintError.printErro(err);
        }
        return List.of();
    }

    @Override
    public QuotaModel findActiveQuotaById(Long employeeId) {
        try {
            return quotaRepository.findActiveQuotaById(employeeId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
