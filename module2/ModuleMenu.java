package module2;
public interface ModuleMenu {
    String getModuleName();
    void initialize();
    void displayMenu();
    void processChoice(String choice);
    void run();
}
