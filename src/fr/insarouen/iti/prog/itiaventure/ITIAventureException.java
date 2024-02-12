package fr.insarouen.iti.prog.itiaventure;

public class ITIAventureException extends Exception {

    public ITIAventureException(String message) {
        super(message);
    }

    public ITIAventureException() { 
        super();
    }

    public String toString() {
        return String.format("ITIAventureException: %s : %s", this.getClass().getName(), this.getMessage());
    }
}
