package contacts.view.memo;

import java.time.LocalDate;

import contacts.data.Categorie;
import contacts.view.ManagerGui;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jfox.javafx.util.UtilFX;
import jfox.javafx.util.converter.ConverterInteger;
import jfox.javafx.view.ControllerAbstract;


public class ViewMemoForm extends ControllerAbstract {

	
	//-------
	// Composants de la vue
	//-------
	
	@FXML
	private Label			labId;
	@FXML
	private TextField		txfTitre;
	@FXML
	private TextArea		txaDescription;
	@FXML
	private CheckBox		ckbUrgent;
	@FXML
	private Button			btnValider;
	@FXML
	private ComboBox<Categorie> cmbCategorie;


	//-------
	// Autres champs
	//-------
	
	@Inject
	private ManagerGui		managerGui;
	@Inject
	private ModelMemo	modelMemo;


	//-------
	// Initialisation du Controller
	//-------

	@FXML
	private void initialize() {
		
		var draft = modelMemo.getDraft();

		// Id
		bind( labId, draft.idProperty(), new ConverterInteger() );
		
		// Libellé
		bindBidirectional( txfTitre, draft.titreProperty()  );
		validator.addRuleNotBlank(txfTitre);
		validator.addRuleMaxLength(txfTitre, 50 );
		validator.addRuleMinLength(txfTitre, 10 );
		
		// Description
		bindBidirectional( txaDescription, draft.descriptionProperty()  );
		validator.addRuleNotBlank(txaDescription);
		validator.addRuleMaxLength(txaDescription, 200 );
		
		// Flagurgent
		bindBidirectional( ckbUrgent, draft.flagUrgentProperty()  );
		validator.addRuleNotBlank(ckbUrgent);
		
		// Bouton VAlider
		btnValider.disableProperty().bind( validator.invalidProperty() );
		
		//Combobox
		cmbCategorie.setItems( modelMemo.getCategories() );
		bindBidirectional( cmbCategorie, draft.categorieProperty() );
		UtilFX.setCellFactory( cmbCategorie, "libelle" );
	}
	
	
	@Override
	public void refresh() {
		txfTitre.requestFocus();
	}
	
	
	//-------
	// Actions
	//-------
	
	@FXML
	private void doAnnuler() {
		managerGui.showView( ViewMemoList.class );
	}
	
	@FXML
	private void doValider() {
		modelMemo.saveDraft();
		managerGui.showView( ViewMemoList.class );
	}
	
	@FXML
	private void doCategorieSupprimer() {
	cmbCategorie.setValue( null );
	}


}
