/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.*;

/**
 *
 * @author Skay
 */
public class ModuloCoexao {

    //método responsavel por estabelecer a conexão com o banco
    public static Connection conector() {
        // variavel se chama conexao, mas o método se chama conector
        java.sql.Connection conexao = null;
        // "chama" o drive que importei para bibliotecas
        //variavel do tipo string com nome de driver
        String driver = "com.mysql.cj.jdbc.Driver";
        // Armazenando informações referente ao banco
        // variavel do tipo sting nome url caminho do banco de dados
        String url = "jdbc:mysql://localhost:3306/dbmecanicagustavo?useTimezone=true&serverTimezone=UTC";
        String user = "root";
        String password = "";
        // Estabelecendo a conexão com o banco
        try {
            Class.forName(driver); // Executar o arquivo do driver
            // Vai obter conexão utilizando os parametros
            conexao = DriverManager.getConnection(url, user, password); 
            return conexao;
        } catch (Exception e) {
           //System.out.println(e);
            return null;
        }
    }

}
