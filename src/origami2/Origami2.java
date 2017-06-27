/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package origami2;

import static com.oracle.jrockit.jfr.Transition.To;
//import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import java.util.ArrayList;
import logica.Validadora;
import logica.db.DaoFactura;
import logica.db.Categoria;
import logica.db.FabricaDaos;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.Vector;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.security.auth.Subject;
import javax.swing.JOptionPane;

import logica.db.Factura;
import logica.db.Usuario;
import sun.rmi.transport.Transport;

/**
 *
 * @author CTesting
 */
public class Origami2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, javax.mail.MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("darkdrei88@gmail.com", "mqyjykammndoxdrh");
                    }
                });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("darkdrei88@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("eupaal@hotmail.com"));
        message.setSubject("Desde la palicacion.");
        message.setText("<h3>Esto se envio desde origamis 2.</h3>");
        javax.mail.Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", "darkdrei88@gmail.com", "mqyjykammndoxdrh");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        System.out.println("Done");
        
        /**
         * ***************************************************
         */
        Gmail.send("login", "darkdrei88@gmail.com",
                "password", "mqyjykammndoxdrh",
                "to", "eupaal@hotmail.com",
                "cc", "eupaal@hotmail.com",
                "bcc", "eupaal@hotmail.com",
                "subject", "prueba desde origamis",
                "body", "<h1>Esto es de origamis sergio. </h1><p>exitosa!!</p>");
    }
    public static void generadorMD5(String m) throws NoSuchAlgorithmException {
        String original = m;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(original.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
    }

}
