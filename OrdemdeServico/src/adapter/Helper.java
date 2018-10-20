package adapter;

public class Helper {

    private static Helper instance;

    private Helper() {
    }

    public static Helper getInstance() {
        if (instance == null) {
            instance = new Helper();
        }
        return instance;
    }

    public int randomiza(int n) {
        int ranNum = (int) (Math.random() * n);
        return ranNum;
    }

    public int mod(int dividendo, int divisor) {
        return (int) Math.round(dividendo - (Math.floor(dividendo / divisor) * divisor));
    }

    public String removeCaracteresEspeciais(String doc) {
        if (doc.contains(".")) {
            doc = doc.replace(".", "");
        }
        if (doc.contains("-")) {
            doc = doc.replace("-", "");
        }
        if (doc.contains("/")) {
            doc = doc.replace("/", "");
        }
        return doc;
    }
}
