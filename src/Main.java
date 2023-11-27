public class Main {
    public static void main(String[] args) {
        SQLMethods.init();
        SQLMethods.ListFollowings(SQLMethods.GetCon(), "testID_01");
    }
}