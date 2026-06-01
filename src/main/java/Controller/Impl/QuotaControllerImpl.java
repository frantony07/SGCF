package Controller.Impl;

import Controller.QuotaController;
import org.ONE.model.entity.QuotaModel;
import org.ONE.model.services.QuotaService;
import org.ONE.model.services.impl.QuotaServiceImpl;

import java.util.List;

public class QuotaControllerImpl implements QuotaController {
    private QuotaService quotaService = new QuotaServiceImpl();

    @Override
    public void createQuota(QuotaModel quotaModel){
        quotaService.createQuota(quotaModel);
    }

    @Override
    public void updateQuota(QuotaModel quotaModel){
        quotaService.updateQuota(quotaModel);
    }

    @Override
    public List<QuotaModel> findAllQuotas(){
        return quotaService.findAllQuotas();
    }

    @Override
    public QuotaModel findActiveQuotaById(Long employeeId){
        return quotaService.findActiveQuotaById(employeeId);
    }


}
