package customtable;

import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableValue;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class DirectColorPickerTableCell<S> extends TableCell<S, Color> {
    private ColorPicker colorPicker;

    public DirectColorPickerTableCell() {
        colorPicker = new ColorPicker();
        colorPicker.setOnAction(event -> {
            ObservableValue<Color> ov = getTableColumn().getCellObservableValue(getIndex());
            if (ov instanceof WritableValue) {
                @SuppressWarnings("unchecked")
                WritableValue<Color> wv = (WritableValue<Color>)ov;
                wv.setValue(colorPicker.getValue());
            }
        });
    }

    @Override
    protected void updateItem(Color item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            colorPicker.setValue(item);
            setGraphic(colorPicker);
        }
    }

    static <S> Callback<TableColumn<S, Color>, TableCell<S, Color>> forTableColumn() {
        return column -> new DirectColorPickerTableCell<>();
    }
}
