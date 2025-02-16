package net.mxumod.mxumod.libraries;

public class ObservableNumber<T extends Number> extends ObservableValue<T> {

    public ObservableNumber(T initialValue) {
        super(initialValue);
    }

    // 🔥 增加數值
    public void addValue(T amount) {
        setValue(add(this.value, amount));
    }

    // 🔥 減少數值
    public void reduceValue(T amount) {
        setValue(subtract(this.value, amount));
    }

    // 🔥 可選：允許控制是否觸發事件
    public void addValue(T amount, boolean isEvent) {
        setValue(add(this.value, amount), isEvent);
    }

    public void reduceValue(T amount, boolean isEvent) {
        setValue(subtract(this.value, amount), isEvent);
    }

    // 🚀 泛型計算方法：加法
    private T add(T a, T b) {
        if (a instanceof Integer) return (T) Integer.valueOf(a.intValue() + b.intValue());
        if (a instanceof Float) return (T) Float.valueOf(a.floatValue() + b.floatValue());
        if (a instanceof Double) return (T) Double.valueOf(a.doubleValue() + b.doubleValue());
        if (a instanceof Long) return (T) Long.valueOf(a.longValue() + b.longValue());
        return a;
    }

    // 🚀 泛型計算方法：減法
    private T subtract(T a, T b) {
        if (a instanceof Integer) return (T) Integer.valueOf(a.intValue() - b.intValue());
        if (a instanceof Float) return (T) Float.valueOf(a.floatValue() - b.floatValue());
        if (a instanceof Double) return (T) Double.valueOf(a.doubleValue() - b.doubleValue());
        if (a instanceof Long) return (T) Long.valueOf(a.longValue() - b.longValue());
        return a;
    }
}
