
package AtmClone;

public class ResourceChecker {
    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String resourcePath = "atm.fxml";
        if (classLoader.getResource(resourcePath) != null) {
            System.out.println("Resource found: " + resourcePath);
        } else {
            System.out.println("Resource not found: " + resourcePath);
        }
    }
}
