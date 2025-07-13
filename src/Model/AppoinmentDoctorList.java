package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;

public class AppoinmentDoctorList {
    private final StringProperty id;
    private final StringProperty name;
    private final IntegerProperty doctorId;

    public AppoinmentDoctorList(String id, String name, int doctorId) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.doctorId = new SimpleIntegerProperty(doctorId);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
    
    public int getDoctorId() {
        return doctorId.get();
    }

    public IntegerProperty doctorIdProperty() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId.set(doctorId);
    }
}
