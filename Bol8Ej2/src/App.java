import java.io.*;
import java.util.Scanner;

public class App {
    static String compruebaRuta(boolean esUnDirectorio){
        Scanner sc = new Scanner(System.in);
        boolean flag=false;
        String ruta="";
            do {
                System.out.println("Introduce una ruta");
                ruta=sc.nextLine();  
                File ru=new File(ruta);
                if(ru.exists()){
                    flag=false;
                }else{
                    System.out.println("Introduce una ruta valida");
                    flag=true;
            }
            } while (flag);
            File f=new File(ruta);
            if(f.isDirectory()&&esUnDirectorio==false){
                System.out.println("Has seleccionado un direcctorio, ciertas funciones del programa no funcionaran correctamente");
            }
        return ruta;
    }

    static int compruebaNum(){
        Scanner sc = new Scanner(System.in);
        int key;
        try {
            key=Integer.parseInt(sc.nextLine());

        } catch (NumberFormatException e) {
            key=0;
        }
        return key;
    }


    static String cambiar(){
        Scanner sc = new Scanner(System.in);
        boolean flag=false;
        String ruta="";
        ruta=compruebaRuta(false);
        return ruta;
    }


    static void sobreescribir(String ruta){
        Scanner sc = new Scanner(System.in); 
        File ru = new File(ruta);
        boolean flag=false;
        int key=0;
        if(ru.exists()){
            System.out.println("El archivo ya existe ¿Quiere sobreescribirlo?");
            System.out.println("1.Si");
            System.out.println("2.No");
            do {
                key=compruebaNum();
                switch (key) {
                    case 1:
                        try(PrintWriter f = new PrintWriter(new FileWriter(ruta,false))){
                        }catch(IOException e){
                            System.err.println("Error al acceder al archivo");
                        }
                        break;
                    case 2:
                        
                        break;
                
                    default:
                    System.out.println("Opción no valida");
                    flag=true;
                        break;
                }
            } while (flag);
        }else{
            try(PrintWriter f = new PrintWriter(new FileWriter(ruta,true))){
            }catch(IOException e){
                System.err.println("Error al acceder al archivo");
            }
        }
    }


    static void mostrar(String ruta){
        String texto;
        try(Scanner f= new Scanner(new File(ruta))){
            while(f.hasNext()){
                texto= f.nextLine();
                System.out.println(texto);
            }
        }catch(IOException e){
            System.err.println("Error de acceso al archivo: "+ e.getMessage());
        }
    }


    static void añadir(String ruta){
        Scanner sc = new Scanner(System.in); 
        String texto;
        System.out.println("Introduce el texto");
        try(PrintWriter f = new PrintWriter(new FileWriter(ruta,true))){
            texto=sc.nextLine();
            f.println(texto);
        }catch(IOException e){
            System.err.println("Error al acceder al archivo");
        }
    }
    static void borrar(String ruta){
        int key;
        boolean flag=false;
        boolean borrado;
        System.out.println("Seguro que quieres borrar?");
        System.out.println("1.Si");
        System.out.println("2.No");
        do {
            key=compruebaNum();
            switch (key) {
                case 1:
                    File f=new File(ruta);
                    borrado=f.delete();
                    if(borrado){
                        System.out.println("Archivo borrado!");
                    }else{
                        System.out.println("Problema con el borrado");
                    }
                    break;
                case 2:
                    
                    break;
                default:
                System.out.println("opción no valida");
                    break;
            }
        } while (flag);


    }
    public static void main(String[] args) throws Exception {
        Scanner sc= new Scanner(System.in);
        boolean flag=true;
        int cont=0;
        String rutaParc="";
        String nombre="Default";
        int key=0;
        String ruta="";
        do {
            //ya se que esto no estaba en el enunciado, pero veo con más sentido que la primera vez que se ejecute el programa pida que selecciones un archivo
            if(cont==0){
                ruta=cambiar();
            }else{
            System.out.println("1.Cambiar Archivo");    
            System.out.println("2.Crear Archivo");
            System.out.println("3.Mostrar Archivo");
            System.out.println("4.Añade Contenido");
            System.out.println("5.Borra Archivo");
            System.out.println("6. Salir");
            key=compruebaNum();
            switch (key) {
                case 1:
                    ruta=cambiar();
                    break;
                case 2:
                rutaParc=compruebaRuta(true);
                System.out.println("Introduce el nombre del archivo");
                nombre=sc.nextLine();
                sobreescribir(rutaParc+"/"+nombre+".txt");
                ruta=rutaParc+"/"+nombre+".txt";
                    break;
                case 3:
                    mostrar(ruta);
                    break;
                case 4:
                    añadir(ruta);
                    break;
                case 5:
                    borrar(ruta);
                    break;
                case 6:
                    System.out.println("Bye bye");
                    flag=false;
                    break;
                default:
                System.out.println("Opcion no valida!");
                    break;
                }
            }
            cont++;
        } while (flag);
    }
}
