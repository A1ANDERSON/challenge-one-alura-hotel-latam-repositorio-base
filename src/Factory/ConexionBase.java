package Factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionBase {

    public DataSource dataSou;

    public ConexionBase() {
        ComboPooledDataSource comboPool = new ComboPooledDataSource();
        comboPool.setJdbcUrl("jdbc:mysql://localhost:3306/hotel_alura_pe");
        comboPool.setUser("root");
        comboPool.setPassword("password");

        this.dataSou = comboPool;
    }

    public Connection conectarBase(){
        try{
            return this.dataSou.getConnection();
        }catch (SQLException e){
            System.out.println("Ocurrio un error al conectarse a la base de datos, posiblemente el servicio no este habilitado");
            throw new RuntimeException(e);
        }
    }
}
