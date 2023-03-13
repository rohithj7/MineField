package mvc;

public class Model extends Bean {

    private boolean unsavedChanges;
    String fileName;

    public Model() {}

    public void changed(){
        unsavedChanges = true;
        firePropertyChange(null,null,null);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fName) {
        fileName = fName;
    }

    public boolean getUnsavedChanges() {
        return unsavedChanges;
    }

    public void setUnsavedChanges(boolean b) {
        unsavedChanges = b;
    }

}
