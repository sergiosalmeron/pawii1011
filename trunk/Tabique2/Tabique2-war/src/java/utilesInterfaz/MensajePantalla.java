/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilesInterfaz;
public class MensajePantalla {
boolean error;
    String texto;

    public MensajePantalla(String texto, boolean error) {
        this.error = error;
        this.texto = texto;
    }

    public boolean isError() {
        return error;
    }

    public String getTexto() {
        return texto;
    }

}
