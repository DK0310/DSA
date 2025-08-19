public interface DataManager {
    void addData(String code, String name, int value1, int value2);
    void displayAllData();
    boolean updateData(String code, String action);
    void searchData(String query);
    boolean hasData(String code);
    String getDataDetails(String code);
}

