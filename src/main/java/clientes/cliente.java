package clientes;

//importamos las librerias que necesitamos
//agregamos este import para la conexion con la base de datos
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
//importamos la biblioteca p@ara poder ingrar datos por pantalla
import java.util.Scanner;


public class cliente {
    //armamos lka estrucutra pricipal para eliminar el error de que no hay clase principal
    //main principak
    public static void main(String []args){
        System.out.println("----------------------------------------");
        System.out.println("Trabajo practico Obligatorio Codo a Codo");
        System.out.println("----------------------------------------");
        
        //CREAMOS UN MENU para el ingreso de datos por pantalla
        int opc=0;
        
        //Creamos las variables para el inhreso de datos por pantalla
        String nombre;
        String apellido;
        int edad;
        String hobbie;
        String editor;
        String so;
        //utilizamos scanner crando una variable sc del tipo scanner para las opciones
        Scanner sc=new Scanner(System.in);
         
        //hacer mientras opc sea distinto de 5 y cuando sea 5 termina la ejecucion del progema.
        do{
            //con el do hacemos si o si que se ejecute minimo una vez el codigo minetras  opc sea menor que 0 "o" mayor a 6
            do{
                System.out.println("MENU PRINCIPAL");                    
                System.out.println("1 - Informe");
                System.out.println("2 - Ingresar");
                System.out.println("3 - Salir");
                System.out.println("");
                System.out.println("Ingrese un opcion: ");
                //opc toma el valor capturado
                opc=sc.nextInt();
                
            } while (opc<0 || opc>3);
            
            switch(opc){
                case 1:
                    consultar();
                    break;
                case 2:
                    System.out.println("COMPLETE LOS DATOS: ");
                    System.out.println("----------------------------------------");
                    
                    System.out.println("Nombre: ");
                    nombre=sc.next();
                    
                    System.out.println("Apellido: ");
                    apellido=sc.next();
                    
                    System.out.println("Edad: ");
                    edad=sc.nextInt();
                    
                    System.out.println("Hobbie: ");
                    hobbie=sc.next();

                    System.out.println("Editor: ");
                    editor=sc.next();

                    System.out.println("Sistema Operativo: ");
                    so=sc.next();
                    
                    //estos datos ingresado por main se los voy a mandar por argumento
                    //estoy enviando argumentos a la funcion insertar esta debe recibir los mismo
                    insertar(nombre,apellido,edad,hobbie,editor,so);
                    System.out.println("Registro Agregado");
                    System.out.println("----------------------------------------");
                    break;
                case 3:
                    System.out.println("GRACIAS POR UTILIZAR LA APP!");
                    break;
            }
        } while(opc!=3);
    }

    //este metodo funcion conecta la base de datos devolviendo un valor de conexion
    private static Connection conectarBaseDeDatos() throws SQLException {
        //sin definir conexion como variable null no funcionaria el return! Solucion!
        Connection conexion=null;

        //en el caso de error utilizaremos el try and cacht para capturar el error en el caso.
        try{
            //soliicitamos los drivers de conexion a la ide maria db de maeven!
            String driver="org.mariadb.jdbc.Driver";
            //definimos la ubicacion de donde esta la base de datos utilizando los drivers y el local host mas el puerto por default
            String url="jdbc:mariadb://localhost:3306/developers";
            String usuario="root";
            String clave="conejiz2012II2";
            //realizamos la conexion
            conexion=DriverManager.getConnection(url,usuario,clave);
        }
        catch (SQLException e) {
            //captura cualquier exception sql que ocurra
            e.printStackTrace();
        }
        return conexion;
    } 
    
    //METODO DE CONSULTA
    private static void consultar(){
        try{
            //conectamos a la bd por medio de la funcion creada
            Connection conexion=conectarBaseDeDatos();
            
            //utilizamos la sintaxis de SQL para seleccionar toda o parte de los campos que queremos mostrar
            String sql="select * from clientes";
        
            //sTATEMENT : para poder mostrar los datos solicitados con la consulta sql
            Statement querry=conexion.createStatement();
            //nos devuelve un resultado y donde le tengo que pasar a executequerry el string sql (variable que cree)
            //para una consulta
            ResultSet resultado=querry.executeQuery(sql); 
            
            
            //IMPRIMIMOS : mientras tengA resultados
            System.out.println("----------------------------------------");
            System.out.println("INICIO DE INFORME");
            System.out.println("----------------------------------------");

            while(resultado.next()){
                //se mostrara cada dato de la tabla
                System.out.println("Nombre: "+resultado.getString("nombre"));
                System.out.println("Apellido: "+resultado.getString("apellido"));
                System.out.println("Edad: "+resultado.getString("edad"));
                System.out.println("Hobbie:"+resultado.getString("hobbie"));
                System.out.println("Editor de Codigo preferido: "+resultado.getString("editor"));
                System.out.println("Sistema Operativo utilizado: "+resultado.getString("sisop"));
                System.out.println("----------------------------------------");

            }
            System.out.println("Informe terminado!");
            System.out.println("----------------------------------------");
        }
        
        catch(SQLException ex){
            //captura cualquier exception sql que ocurra
            ex.printStackTrace();        
        }
       
    }
    
    //METODO PARA INSETAR
    //recibo los datos del scanner y cro variables para poder trabajr con esos datos
    public static void insertar(String nom, String ap,int e, String hob, String ed, String so){
        try{
            //conectamos a la bd por medio de la funcion creada
            //me devuelve un string de conexion 
            Connection conexion=conectarBaseDeDatos();
            
            //utilizamos la sintaxis de SQL para seleccionar toda o parte de los campos que queremos mostrar
            //VALUE -> palabra reservada. Pasamos a Harcoder -> metemos datos desde el codigo! 
            String sql="INSERT INTO clientes(nombre,apellido,edad,hobbie,editor,sisop)"
                    +"VALUES('"+nom+"','"+ap+"',"+e+",'"+hob+"','"+ed+"','"+so+"')";
           
            //sTATEMENT : para poder mostrar los datos solicitados con la consulta sql
            Statement querry=conexion.createStatement();
           
            //para insertar. Vamos a tomar l qyerry y ejecutar el sql de la linea 88!
            querry.execute(sql);
            
        }
        
        catch(SQLException ex){
            //captura cualquier exception sql que ocurra
            ex.printStackTrace();        
        }
        
    }
}
