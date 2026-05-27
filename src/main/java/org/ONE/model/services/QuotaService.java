package org.ONE.model.services;

import org.ONE.model.entity.QuotaModel;

import java.util.List;

public interface QuotaService {
    void createQuota (QuotaModel quotaModel);
    void updateQuota (QuotaModel quotaModel);
    List<QuotaModel> findAllQuotas();
    QuotaModel findActiveQuotaById(Long employeeId);
}
