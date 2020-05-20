import java.io.File;
import java.util.Scanner;
 
public class principal {
    static String seleccion(){
        Scanner sc = new Scanner(System.in);
        String ruta="";
        
        boolean flag=false;
        while (flag==false){
            System.out.println("Introduce la ruta");
            ruta=sc.nextLine();
            File f=new File(ruta);
            if(f.canRead() && f.exists()){
                flag=true;
                
            }else{
                System.out.println("Ruta no valida");
            }
        }
        return ruta;
    } 

    static void info(String ruta){
        if(ruta==""){
            System.out.println("Selecciona una ruta primero!");
        }else{
            File f = new File(ruta);
            if(f.isFile()){
                System.out.printf("El nombre del archivo es %s\nSe encuentra en %s\n", f.getName(),f.getParent());
            }
            if(f.isDirectory()){
               File[] archivosContenidos=f.listFiles();
               for (int i = 0; i < archivosContenidos.length; i++) {
                   if (archivosContenidos[i].isFile()){
                    System.out.printf("%s \nPeso: %d bytes\n", archivosContenidos[i].getName(), archivosContenidos[i].length()); 
                   }
               }
            }
        }
    }

    static void crear(){
        Scanner sc = new Scanner(System.in);
        String ruta;
        String nombre="";
        boolean flag;
        System.out.println("Introduce la ruta");
        ruta=sc.nextLine();
        File ru=new File(ruta);
        if(ru.isDirectory()){
            System.out.println("Introduce el nombre del directorio");
            nombre=sc.nextLine();
            File dir=new File(ruta+"/"+nombre);
            flag=dir.mkdir();
            if(flag){
                System.out.println("El archivo se ha creado");
            }else{
                System.out.println("El archivo no se ha podido crear");
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String ruta="";
        boolean flag=false;
        boolean flag2=false;
        do {
            System.out.println("1.Seleccionar archivo o directorio");
            System.out.println("2.Información");
            System.out.println("3.Crear directorio");
            System.out.println("4.Salir");
            int key=0;
            do {
                try {
                    key=Integer.parseInt(sc.nextLine());
                    flag2=false;
                } catch (NumberFormatException e) {
                    System.out.println("Introduce un número del menu!");
                    flag2=true;
                }
            } while (flag2);
           
            switch (key) {
                case 1:
                    ruta=seleccion();
                    break;
                case 2:
                    info(ruta);
                    break;
                case 3:
                    crear();
                    break;
                case 4:
                    flag=true;
                    System.out.println("Bye bye");
                    break;
            
                default:
                System.out.println("Opción no valida");
                    break;
            }
        } while (flag==false);
    }
}
