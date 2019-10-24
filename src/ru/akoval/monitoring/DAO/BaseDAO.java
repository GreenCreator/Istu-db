package ru.akoval.monitoring.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

import ru.akoval.monitoring.entities.BaseEntity;
import ru.akoval.monitoring.util.SqliteConnection;

public class BaseDAO {


    public static <T extends BaseEntity> T delete(T entity) {
        /***
         * Requires that class of object passed to param was named equally to name of table in database
         */
        String sql = "DELETE FROM " + entity.getClass().getSimpleName().toLowerCase() + " WHERE id = ?";
        try (
                Connection conn = SqliteConnection.Connector();
                PreparedStatement statement = Objects.requireNonNull(conn).prepareStatement(sql)
        ) {
            statement.setInt(1, entity.getId());
            statement.executeUpdate();

            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
