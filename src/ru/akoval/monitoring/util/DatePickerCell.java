package ru.akoval.monitoring.util;

import javafx.beans.binding.Bindings;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import ru.akoval.monitoring.entities.Semester;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DatePickerCell extends TableCell<Semester, LocalDate> {

    private final DateTimeFormatter formatter ;
    private final DatePicker datePicker ;

    public DatePickerCell() {

        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy") ;
        datePicker = new DatePicker() ;

        datePicker.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB) {
                datePicker.setValue(datePicker.getConverter().fromString(datePicker.getEditor().getText()));
                commitEdit(datePicker.getValue());
            }
            if (event.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        });

        datePicker.setDayCellFactory(picker -> {
            DateCell cell = new DateCell();
            cell.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                datePicker.setValue(cell.getItem());
                if (event.getClickCount() == 2) {
                    datePicker.hide();
                    commitEdit(cell.getItem());
                }
                event.consume();
            });
            cell.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    commitEdit(datePicker.getValue());
                }
            });
            return cell ;
        });

        contentDisplayProperty().bind(Bindings.when(editingProperty())
                .then(ContentDisplay.GRAPHIC_ONLY)
                .otherwise(ContentDisplay.TEXT_ONLY));
    }

    @Override
    public void updateItem(LocalDate birthday, boolean empty) {
        super.updateItem(birthday, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(formatter.format(birthday));
            setGraphic(datePicker);
        }
    }

    @Override
    public void startEdit() {
        super.startEdit();
        if (!isEmpty()) {
            datePicker.setValue(getItem());
        }
    }

}
