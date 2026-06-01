package Controller;

import org.ONE.model.entity.QuotaModel;

import java.util.List;

public interface QuotaController {
    void createQuota (QuotaModel quotaModel);

    void updateQuota(QuotaModel quotaModel);

    List<QuotaModel> findAllQuotas();

    QuotaModel findActiveQuotaById(Long employeeId);
}
