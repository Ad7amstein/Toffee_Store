import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * The OTPManager class generates a One-Time Password (OTP) and sends it to a
 * specified email address.
 * The OTP is generated using a 6-digit random number and is sent to the email
 * address using JavaMail API.
 * The email configuration includes the SMTP server, port, sender email address,
 * and password.
 * 
 * @author Adham Allam
 * @see <a href="https://www.linkedin.com/in/adham-allam/">My LinkedIn
 *      profile</a>
 */
public class OTPManager {
    public static int otpNum;

    /**
     * Creates a new OTP and sends it to the specified email address using JavaMail
     * API.
     *
     * @param toEmail the email address to which the OTP should be sent
     */
    public static void Create(String toEmail) {

        // Random number generator to generate OTP
        Random random = new Random();
        otpNum = 100000 + random.nextInt(900000); // Generate a 6-digit random number as OTP

        // Sender and recipient email addresses
        String fromEmail = "adham32003200@gmail.com"; // replace with your email address

        // Email configuration
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com"); // replace with your SMTP server
        properties.put("mail.smtp.port", "587"); // replace with your SMTP server port

        // Create a session with email server
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, "ay7aga"); // replace with your email password
            }
        });

        try {
            // Create a message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Your One-Time Password");
            message.setText("Your OTP is: " + otpNum);

            // Send the message
            Transport.send(message);
            System.out.println("OTP sent successfully to " + toEmail);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
