package statistics.matcher;

import java.lang.reflect.Method;

import statistics.Player;

public class HasFewerThan implements Matcher {

    private int value;
    private String methodName;

    public HasFewerThan(int value, String category) {
        this.value = value;
        methodName = "get" + Character.toUpperCase(category.charAt(0)) + category.substring(1, category.length());
    }

    @Override
    public boolean matches(Player p) {
        try {
            Method method = p.getClass().getMethod(methodName);
            int playersValue = (Integer) method.invoke(p);
            return playersValue < value;
        } catch (Exception e) {
            System.out.println(e);
            throw new IllegalStateException(
                "Player does not have field " + methodName.substring(3, methodName.length()).toLowerCase());
        }
    }
}