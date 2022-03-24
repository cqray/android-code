package cn.cqray.android.util;

public class ResUtils {

    public static int getIdByName(String name) {
        return getIdByName(name, "id");
    }

    public static int getStringIdByName(String name) {
        return getIdByName(name, "string");
    }

    public static int getColorIdByName(String name) {
        return getIdByName(name, "color");
    }

    public static int getDimenIdByName(String name) {
        return getIdByName(name, "dimen");
    }

    public static int getIdByName(String name, String defType) {
        String packageName = Utils.getContext().getPackageName();
        return Utils.getContext().getResources().getIdentifier(name, defType, packageName);
    }
}
