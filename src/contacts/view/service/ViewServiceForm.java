package contacts.view.service;

import contacts.data.Categorie;
import contacts.data.Personne;
import contacts.view.ManagerGui;
import contacts.view.memo.ModelMemo;
import contacts.view.memo.ViewMemoList;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jfox.javafx.util.UtilFX;
import jfox.javafx.util.converter.ConverterInteger;
import jfox.javafx.view.ControllerAbstract;

public class ViewServiceForm extends ControllerAbstract {

	
	//-------
	// Composants de la vue
	//-------
	
	@FXML
	private Label			labId;
	@FXML
	private TextField		txfNom;
	@FXML
	private TextField		txfAnneeCreation;
	@FXML
	private CheckBox		ckbSiege;
	@FXML
	private Button			btnValider;
	@FXML
	private ComboBox<Personne> cmbPersonne;


	//-------
	// Autres champs
	//-------
	
	@Inject
	private ManagerGui		managerGui;
	@Inject
	private ModelService	modelService;


	//-------
	// Initialisation du Controller
	//-------

	@FXML
	private void initialize() {
		
		var draft = modelService.getDraft();

		// Id
		bind( labId, draft.idProperty(), new ConverterInteger() );
		
		// Nom
		bindBidirectional( txfNom, draft.nomProperty()  );
		validator.addRuleNotBlank(txfNom);
		validator.addRuleMaxLength(txfNom, 50 );
		validator.addRuleMinLength(txfNom, 3 );
		
		// Annee de creation
		bindBidirectional( txfAnneeCreation, draft.anneeCreationProperty(), new ConverterInteger() );
		validator.addRuleNotBlank(txfAnneeCreation);
		validator.addRuleMaxLength(txfAnneeCreation, 5 );
		validator.addRuleMinLength(txfAnneeCreation, 4 );
		
		// Flagsiege
		bindBidirectional( ckbSiege, draft.flagSiegeProperty()  );
		validator.addRuleNotBlank(ckbSiege);
		
		// Bouton VAlider
		btnValider.disableProperty().bind( validator.invalidProperty() );
		
		//Combobox
		cmbPersonne.setItems( modelService.getPersonne() );
		bindBidirectional( cmbPersonne, draft.personneProperty() );
		UtilFX.setCellFactory( cmbPersonne, p -> p.getNom() + " " + p.getPrenom() );
	}
	
	
	@Override
	public void refresh() {
		txfNom.requestFocus();
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
		modelService.saveDraft();
		managerGui.showView( ViewMemoList.class );
	}
	
	@FXML
	private void doPersonneSupprimer() {
	cmbPersonne.setValue( null );
	}
}
