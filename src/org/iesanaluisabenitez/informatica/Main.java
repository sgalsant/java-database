package org.iesanaluisabenitez.informatica;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // instalar la libreria para conectarnos a mysql-conector-java
        try (Connection conexion = DriverManager.getConnection(
                "jdbc:mysql://localhost:8306/sakila",
                "root",
                "root")) {

            Scanner scanner = new Scanner(System.in);
            System.out.print("buscar por título: ");
            String title = scanner.next();
            String sql = "select * from film where title like '%" + title + "%'";

            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(sql);

            //MetaData para obtener información acerca de las columnas
            //el indice de las columnas empieza en 1
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.println(rsmd.getColumnName(i) + " " + rsmd.getColumnTypeName(i));
            }

            //recorremos los registros recuperados
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getDate(13));
            }

        } catch (SQLException e) {
            System.out.println("Error SQL " + e.getMessage());
        }


    }
}
