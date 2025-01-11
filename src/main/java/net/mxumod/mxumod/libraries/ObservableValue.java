package net.mxumod.mxumod.libraries;

import java.util.ArrayList;
import java.util.List;

public class ObservableValue<T> {
    private T value; // 儲存當前值
    private final List<ChangeListener<T>> listeners = new ArrayList<>(); // 儲存監聽器

    // 接口：用於實現監聽行為
    public interface ChangeListener<T> {
        void onChanged(T oldValue, T newValue);
    }

    // 構造函數
    public ObservableValue(T initialValue) {
        this.value = initialValue;
    }

    // 設置值，並在值改變時觸發事件
    public void setValue(T newValue) {
        if ((this.value == null && newValue != null) || (this.value != null && !this.value.equals(newValue))) {
            T oldValue = this.value;
            this.value = newValue;

            // 通知所有監聽器
            for (ChangeListener<T> listener : listeners) {
                listener.onChanged(oldValue, newValue);
            }
        }
    }

    // 獲取當前值
    public T getValue() {
        return this.value;
    }

    // 添加監聽器
    public void addChangeListener(ChangeListener<T> listener) {
        listeners.add(listener);
    }

    // 移除監聽器
    public void removeChangeListener(ChangeListener<T> listener) {
        listeners.remove(listener);
    }
}

