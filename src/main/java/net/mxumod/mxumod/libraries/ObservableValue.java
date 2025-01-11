package net.mxumod.mxumod.libraries;

import java.util.ArrayList;
import java.util.List;

public class ObservableValue<T> {
    private T value; // 儲存當前值
    private final List<ChangeListener<T>> listeners = new ArrayList<>(); // 儲存監聽器

    public interface ChangeListener<T> {
        void onChanged(T oldValue, T newValue);
    }

    public ObservableValue(T initialValue) {
        this.value = initialValue;
    }

    public void setValue(T newValue) {
        if ((this.value == null && newValue != null) || (this.value != null && !this.value.equals(newValue))) {
            T oldValue = this.value;
            this.value = newValue;

            for (ChangeListener<T> listener : listeners) {
                listener.onChanged(oldValue, newValue);
            }
        }
    }

    public T getValue() {
        return this.value;
    }

    public void addChangeListener(ChangeListener<T> listener) {
        listeners.add(listener);
    }

    public void removeChangeListener(ChangeListener<T> listener) {
        listeners.remove(listener);
    }
}

