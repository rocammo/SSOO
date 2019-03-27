package so;

/**
 * TODO
 *
 * 1. Cuando borramos varios archivos, falla
 * 2. Elementos null dentro del ArrayList clusters
 * 
 * 
 * 1. Siempre clusters.set
 * 2. Cuando borramos, no cambiar clusters
 * 3. Modificar mostrar MetaDatos de clusters
 */

public class Principal {
    public static void main(String[] args) {
        FAT fat = new FAT(10);
        fat.mostrarMD();
        fat.agregar(new Archivo("gataca.avi"));

        fat.mostrarMD();
        fat.agregar(new Archivo("jui.avi"));
        fat.agregar(new Archivo("asdf.avi"));
        fat.mostrarMD();
        fat.borrarArchivo(new Archivo("gataca.avi"));
        fat.mostrarMD();

        // fat.borrarArchivo(new Archivo("gataca.avi"));
        // fat.borrarArchivo(new Archivo("gataca.avi"));
        // fat.mostrarMD();
    }
}
