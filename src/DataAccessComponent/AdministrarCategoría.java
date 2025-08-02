package DataAccessComponent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AdministrarCategoría {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        int opcion;
        do{
            System.out.println("\n =====Administrar la tabla categoría=====");
            System.out.println("\n 1. Insertar");
            System.out.println("\n 2. Consultar");
            System.out.println("\n 3. Actualizar");
            System.out.println("\n 4. Eliminar");
            System.out.println("\n 0. Salir");
            System.out.println("\n Ingresar opción: ");
            opcion = sc.nextInt();;

            switch(opcion){
                case 1:
                    insertar();
                    break;
                case 2:
                    consultar();
                    break;
                case 3:
                    actualizar();
                    break;
                case 4:
                    eliminar();
                    break;
                case 0:
                    System.out.printf("Saliendo");
                    break;
                default:
                    System.out.printf("Invalido");
            }
        }while (opcion != 0);
    }

    public static void insertar(){
        try(Connection conn = ConexionOracleMaster.getConnection()){
            System.out.println("ID Categoria: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.println("ID ingresado: " + id);
            System.out.println("Nombre: ");
            String nombre = sc.nextLine();
            System.out.println("Nombre Ingresado: " + nombre);
            System.out.println("Descripcion: ");
            String descripcion = sc.nextLine();
            System.out.println("Descripcion Ingresado: " + descripcion);

            String sql = "INSERT INTO Categoria (CATEGORIA_ID, NOMBRE_CATEGORIA, DESCRIPCION) VALUES (?,?,?)";
            try(PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setInt(1, id);
                stmt.setString(2, nombre);
                stmt.setString(3, descripcion);

                int rows = stmt.executeUpdate();
                System.out.println(rows>0?  "✅ Categoría insertada." : "⚠️ No se insertó ninguna fila.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void consultar(){
        try(Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "SELECT * FROM CATEGORIA";
            try(PreparedStatement stmt = conn.prepareStatement(sql)){
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("CATEGORIA_ID");
                    String nombre = rs.getString("NOMBRE_CATEGORIA");
                    String descripcion = rs.getString("DESCRIPCION");
                    System.out.println(id + " " + nombre + " " + descripcion + "\n");
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void actualizar(){
        try(Connection conn = ConexionOracleMaster.getConnection()){
            System.out.println("ID Categoria a actualizar: ");
            int id = sc.nextInt();
            System.out.println("Nuevo nombre: ");
            String nombre = sc.nextLine();
            System.out.println("Nueva descripcion: ");
            String descripcion = sc.nextLine();

            String sql = "UPDATE CATEGORIA SET NOMBRE_CATEGORIA = ?, DESCRIPCION = ? WHERE CATEGORIA_ID = ?";
            try(PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1, nombre);
                stmt.setString(2, descripcion);
                stmt.setInt(3, id);
                int rows = stmt.executeUpdate();
                System.out.println(rows > 0 ? "✅ Categoría actualizada." : "⚠️ No se encontró la categoría.");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void eliminar(){
        try(Connection conn = ConexionOracleMaster.getConnection()){
            System.out.println("ID Categoria a eliminar: ");
            int id = sc.nextInt();

            String sql = "DELETE FROM Categoria WHERE CATEGORIA_ID = ?";
            try(PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setInt(1, id);
                int rows = stmt.executeUpdate();

                System.out.println(rows > 0 ? "✅ Categoría eliminada. " : "⚠️ No se encontró la categoria.")    ;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
