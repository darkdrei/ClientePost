/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.correo;

/**
 *
 * @author dreicon
 */
public final class EnvioCorreo {

    private static final EnvioCorreo envio_correo = new EnvioCorreo();

    private EnvioCorreo() {
    }

    public static void send() {
        logica.db.Configuracion c = new logica.db.Configuracion();
        //
        String contenido="";
        for (logica.Ingrediente arg : c.getCampos(c.get_sDato().getUnidades(), c.get_sDato().getIngrediente())) {
            contenido+="<tr><td>"+arg.getNombre()+"</td><td>"+arg.getDescripcion()+"</td></tr>";
        }
        String body = "";
        body += "<style  type=\"text/css\">\n"
                + "table {\n"
                + "    width:100%;\n"
                + "}\n"
                + "table, th, td {\n"
                + "    border: 1px solid black;\n"
                + "    border-collapse: collapse;\n"
                + "}\n"
                + "th, td {\n"
                + "    padding: 5px;\n"
                + "    text-align: left;\n"
                + "}\n"
                + "table#t01 tr:nth-child(even) {\n"
                + "    background-color: #eee;\n"
                + "}\n"
                + "table#t01 tr:nth-child(odd) {\n"
                + "   background-color:#fff;\n"
                + "}\n"
                + "table#t01 th	{\n"
                + "    background-color: black;\n"
                + "    color: white;\n"
                + "}\n"
                + "</style>";
        body += "<table id=\"t01\">\n"
                + "  <tr >\n"
                + "    <th>Nombre</th>\n"
                + "    <th>Existencias</th>		\n"
                + "  </tr>\n"
                +contenido+"\n"
                + "</table>";
        logica.correo.Gmail.send("login", "darkdrei88@gmail.com",
                "password", "mqyjykammndoxdrh",
                "to", c.get_sDato().getCorreo(),
                "cc", c.get_sDato().getCorreo(),
                "bcc", c.get_sDato().getCorreo(),
                "subject", "Existencias Ingredientes y Bebidas",
                "body",body);
    }
}
