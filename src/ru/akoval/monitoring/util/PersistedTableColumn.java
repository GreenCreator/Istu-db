package ru.akoval.monitoring.util;


import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class PersistedTableColumn<S,T> extends TableColumn<S,T> {

    public PersistedTableColumn(){
        super();
    }

    public void setParams(
            String colName,
            Callback<TableColumn<S,T>, TableCell<S,T>> cellFactory,
            BiConsumer<S,T> committer,
            BiConsumer<S, String> persister)
    {
        this.setCellValueFactory(new PropertyValueFactory<>(colName));
        this.setCellFactory(cellFactory);
        this.setOnEditCommit(event -> {
            committer.accept(event.getRowValue(), event.getNewValue());
            persister.accept(event.getRowValue(), event.getTableColumn().getId());
        });
    }

    @Override
    public String toString() {
        return this.getText();
    }
}
