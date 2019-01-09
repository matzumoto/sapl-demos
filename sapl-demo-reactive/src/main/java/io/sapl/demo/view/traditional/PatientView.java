package io.sapl.demo.view.traditional;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import io.sapl.demo.domain.PatientRepo;
import io.sapl.demo.view.AbstractPatientForm;
import io.sapl.demo.view.AbstractPatientView;
import io.sapl.pep.BlockingSAPLAuthorizer;
import io.sapl.pep.SAPLAuthorizer;


@SpringComponent("traditionalPatientView")
@SpringView(name = "traditional")
public class PatientView extends AbstractPatientView {

    @Autowired
    public PatientView(SAPLAuthorizer authorizer, PatientRepo patientRepo) {
        super(authorizer, patientRepo);
    }

    @Override
    protected AbstractPatientForm createForm(AbstractPatientForm.RefreshCallback refreshCallback, PatientRepo patientRepo, BlockingSAPLAuthorizer authorizer) {
        return new PatientForm(refreshCallback, patientRepo, authorizer);
    }
}
