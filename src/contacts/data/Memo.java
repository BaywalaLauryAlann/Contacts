package contacts.data;

import java.util.Objects;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Memo {
	private final ObjectProperty<Integer> id = new SimpleObjectProperty<>();
	private final StringProperty titre = new SimpleStringProperty();
	private final StringProperty description = new SimpleStringProperty();
	private final ObjectProperty<Boolean> flagUrgent = new SimpleObjectProperty<>();
	private final ObjectProperty<Categorie> categorie = new SimpleObjectProperty<>();
	//Getters & Setters
	public ObjectProperty<Integer> idProperty(){
		return id;
	}
	public Integer getId() {
		return id.get();
	}
	public void setId(Integer id) {
		this.id.set(id);
	}
	public StringProperty descriptionProperty() {
		return description;
	}
	public String getDescription() {
		return description.get();
	}
	public void setDescription( String description ) {
		this.description.set( description );
	}
	public StringProperty titreProperty() {
		return titre;
	}
	public String getTitre() {
		return titre.get();
	}
	public void setTitre( String titre ) {
		this.titre.set( titre );
	}
	public ObjectProperty<Boolean> flagUrgentProperty(){
		return flagUrgent;
	}
	public Boolean getFlagUrgent() {
		return flagUrgent.get();
	}
	public void setFlagUrgent(Boolean flagUrgent) {
		this.flagUrgent.set(flagUrgent);
	}
	public ObjectProperty<Categorie> categorieProperty(){
		return categorie;
	}
	public Categorie getCategorie() {
		return categorie.get();
	}
	public void setCategorie(Categorie categorie) {
		this.categorie.set(categorie);
	}
	@Override
	public int hashCode() {
		return Objects.hash(id.get());
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Memo other = (Memo) obj;
		return Objects.equals(id.get(), other.id.get());
	}
	
	
}
