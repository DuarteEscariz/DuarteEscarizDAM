
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.*;

public class FrameEj3 extends JFrame implements ActionListener  {
    JTextArea texto;
    JButton boton;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    String contenido;
        public String selecciónDeExtension(File file){
            String extension="";
            try {
                if(file.exists()){
                    String nombre = file.getName();
                    extension=nombre.substring(nombre.lastIndexOf("."));
                }
            } catch (Exception e) {
                extension="";
            }
            return extension;
        }

        public FrameEj3(){
            super("Ej3");
            this.setLayout(new FlowLayout());
            boton=new JButton("Selecciona Fichero");
            boton.addActionListener(this);
            add(boton);
            texto=new JTextArea();
            texto.setSize(texto.getPreferredSize());
            add(texto);
            label1=new JLabel();
            add(label1);
            label2=new JLabel();
            add(label2);
            label3=new JLabel();
            add(label3);
            label4=new JLabel();
            add(label4);

        }


@Override
public void actionPerformed(ActionEvent evento) {
    int respuesta;
    FileNameExtensionFilter filtroText=new FileNameExtensionFilter("Texto","txt");
    FileNameExtensionFilter filtroImg=new FileNameExtensionFilter("Imagenes","jpg","jpeg","png","gif");
    JFileChooser fc=new JFileChooser();
    fc.addChoosableFileFilter(filtroText);
    fc.addChoosableFileFilter(filtroImg);
    respuesta=fc.showOpenDialog(this);
    if(respuesta==JFileChooser.APPROVE_OPTION){
       String extension=selecciónDeExtension(fc.getSelectedFile());
       if(extension.equals(".txt")){
           try(Scanner f = new Scanner(fc.getSelectedFile())) {
               while(f.hasNext()){
                   contenido=f.nextLine();
                   texto.append(contenido+"\n");
               }

           } catch (Exception e) {
            System.err.println("Error de acceso al archivo");
           }
       }else{
           if(extension.equals(".jpg")||extension.equals(".jpeg")||extension.equals(".png")||extension.equals(".gif")){
            label1.setIcon(new ImageIcon(fc.getSelectedFile().getPath()));
            label1.setSize(label1.getPreferredSize());
           }else{
               label1.setText("Nombre: "+fc.getSelectedFile().getName());
               label2.setText("Path: "+fc.getSelectedFile().getPath());
               label3.setText("Tamaño: "+(fc.getSelectedFile().length()/1024)+"Kb");
               label4.setText(String.format
               ("%s permisos de escritura y %s permisos de lectura", fc.getSelectedFile().canWrite() ? "Tienes" : "No tienes", fc.getSelectedFile().canRead() ? "tienes" : "mo tienes"));
           }
       }
    }
}
}
