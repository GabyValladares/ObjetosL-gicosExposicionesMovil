package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import android.content.Context;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLConnection {
    private static final String URL = "jdbc:mysql://localhost/auto";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver no encontrado", e);
        }
    }

    public static void insertData(Context context, String nombre, String cedula, String pagarMatricula, String pagarMultas, String matriculacion, String pagoContaminacion, String total) {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                String query = "INSERT INTO datos (nombre, cedula, pagarMatricula, PagoMultas, matriculacion, pagoCont, total) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, nombre);
                    preparedStatement.setString(2, cedula);
                    preparedStatement.setString(3, pagarMatricula);
                    preparedStatement.setString(4, pagarMultas);
                    preparedStatement.setString(5, matriculacion);
                    preparedStatement.setString(6, pagoContaminacion);
                    preparedStatement.setString(7, total);
                    preparedStatement.executeUpdate();
                    Toast.makeText(context, "Datos guardados exitosamente", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Error al conectar a la base de datos", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
        }
    }
}
