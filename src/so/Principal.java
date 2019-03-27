package so;

public class Principal {
    public static void main(String[] args) {
        FAT fat = new FAT(10);
        fat.mostrarMD();
//        fat.agregar(new Archivo("gataca.avi"));
//
//        fat.mostrarMD();
//        fat.agregar(new Archivo("jui.avi"));
//        fat.agregar(new Archivo("asdf.avi"));
//        fat.mostrarMD();
//        fat.borrarArchivo(new Archivo("gataca.avi"));
//        fat.mostrarMD();

        for (int i = 0; i < 5; i++) {
        	fat.agregar(new Archivo("gat.ava"));
        }
        fat.mostrarMD();
        
        fat.borrarArchivo(new Archivo("gat.ava"));
        fat.mostrarMD();
        fat.borrarArchivo(new Archivo("asf"));
        fat.mostrarMD();
        fat.borrarArchivo(new Archivo("gat.ava"));
        fat.mostrarMD();
        
        // fat.borrarArchivo(new Archivo("gataca.avi"));
        // fat.borrarArchivo(new Archivo("gataca.avi"));
        // fat.mostrarMD();
    }
}
