package Controller;

import Controller.Record.PayModelDTO;
import org.ONE.model.entity.ENUM.Status;
import org.ONE.model.entity.PayModel;

import java.time.LocalDate;
import java.util.List;

public interface PaymentController {

    void createNewRecorde(PayModelDTO payModel);

    void updateRecords(PayModel payModel);

    void delete(PayModelDTO payModel);

    List<PayModel> findByName(String name);

    List<PayModel> findAll ();

    PayModel findById(long id);

    Long getCount();

    Long getSize();


}
