package module2;

public interface ModuleMenu {
    // DataManager and ModuleMenu methods merged
    void addData(String code, String name, int value1, int value2);
    void displayAllData();
    boolean updateData(String code, String action);
    void searchData(String query);
    boolean hasData(String code);
    String getDataDetails(String code);
    String getModuleName();
    void initialize();
    void displayMenu();
    void processChoice(String choice);
    void run();
}
// DataManager interface removed, all methods are now in ModuleMenu
