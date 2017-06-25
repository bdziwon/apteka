package application.core.beans.magazineOrder;


import application.core.api.manager.MedicineManager;
import application.core.beans.utility.MessageBean;
import application.core.model.Medicine;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@ManagedBean(name = "magazineOrderBean")
@RequestScoped
public class MagazineOrderBean implements Serializable {

    @ManagedProperty(value = "#{messageBean}")
    MessageBean messageBean;

    @EJB(beanInterface = MedicineManager.class)
    private MedicineManager medicineManager;

    List<MagazineOrder> magazineOrders = new ArrayList<MagazineOrder>();

    @PostConstruct
    public void init() {
        List<Medicine> medicines = medicineManager.findAllMedicines();
        for (Medicine medicine : medicines) {
            if (!medicine.getType().equals("bought")) {
                //ignore produced medicines
                continue;
            }
            if (medicine.getQuantity() < medicine.getMinQuantity()) {
                MagazineOrder magazineOrder = new MagazineOrder();
                Long orderQuantity = medicine.getMaxQuantity() - medicine.getQuantity();

                magazineOrder.setMedicineName(medicine.getName());
                magazineOrder.setQuantity(orderQuantity);
                magazineOrders.add(magazineOrder);
            }
        }
    }

    public void sendMail() throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        final String username = "paiprojekt2017@gmail.com";
        final String password = "hubert123";
        String text = "";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream = writePdf(outputStream);

        SimpleDateFormat simpleDateHere = new SimpleDateFormat("dd-MM-yyyy ");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("paiprojekt2017@gmail.com"));
            message.setSubject("zamowienie z dnia" + "" + simpleDateHere.format(new Date()));
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText(text);

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment

            DataSource source = new ByteArrayDataSource(outputStream.toByteArray(), "application/octet-stream");
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName("order.pdf");

            multipart.addBodyPart(attachmentPart);
            // Send the complete message parts
            message.setContent(multipart);


            Transport.send(message);
            System.out.println("Sent message successfully....");
            messageBean.addMessage("Order has been sent to paiprojekt2017@gmail.com");


        } catch (MessagingException e) {
            messageBean.addMessage("Sending operation failed");

            throw new RuntimeException(e);
        }
    }

    public ByteArrayOutputStream writePdf(ByteArrayOutputStream outputStream) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        document.open();
        document.addTitle("Order");
        document.addSubject("Order");
        document.addKeywords("PSK, mail, order");
        document.addAuthor("PSK");
        document.addCreator("PSK");
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Chunk("Order text.."));

        document.add(paragraph);

        PdfPTable table = new PdfPTable(2);
        table.setSpacingBefore(50);
        table.setHeaderRows(1);
        PdfPCell header1 = new PdfPCell(new Phrase("Medicine name"));
        PdfPCell header2 = new PdfPCell(new Phrase("Quanitity"));
        table.addCell(header1);
        table.addCell(header2);
        table.completeRow();

        for (MagazineOrder magazineOrder : magazineOrders) {
            PdfPCell cell1 = new PdfPCell(new Phrase(magazineOrder.getMedicineName()));
            PdfPCell cell2 = new PdfPCell(new Phrase(String.valueOf(magazineOrder.getQuantity())));
            table.addCell(cell1);
            table.addCell(cell2);
            table.completeRow();
        }

        document.add(table);

        document.close();
        return outputStream;
    }

    public MedicineManager getMedicineManager() {
        return medicineManager;
    }

    public void setMedicineManager(MedicineManager medicineManager) {
        this.medicineManager = medicineManager;
    }

    public MessageBean getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(MessageBean messageBean) {
        this.messageBean = messageBean;
    }

    public List<MagazineOrder> getMagazineOrders() {
        return magazineOrders;
    }

    public void setMagazineOrders(List<MagazineOrder> magazineOrders) {
        this.magazineOrders = magazineOrders;
    }
}
