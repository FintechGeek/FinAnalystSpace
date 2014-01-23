package org.charlestech.fin.utils;

import java.io.File;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

/*
 * @Description Sending emails utilities
 * @author Charles Chen
 * @date 14-1-23
 * @version 1.0
 */
public class MailUtils {
    private JavaMailSenderImpl mailSender;

    /**
     * Mail sender setter
     *
     * @param mailSender
     */
    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Send simple mail
     *
     * @param mailto  mailto address
     * @param mailcc  cc address
     * @param mailbcc bcc address
     * @param subject mail subject
     * @param content mail content
     * @param isHTML  if content displayed as HTML or not
     * @return 0:success -1:exception;-2:mailto is null
     */
    public int sendSimpleMail(String mailto, String mailcc, String mailbcc,
                              String subject, String content, boolean isHTML) {
        // Create mime message
        MimeMessage mess = mailSender.createMimeMessage();
        // Create mail helper to inject information into mess
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mess, true, "utf-8");
            helper.setFrom(mailSender.getUsername());
            helper.setTo(mailto);
            if (mailto == null) {
                return -2;
            }
            if (mailcc != null)
                helper.setCc(mailcc);
            if (mailbcc != null)
                helper.setBcc(mailbcc);
            helper.setSubject(subject);
            helper.setText(content, isHTML);
            // Send mail
            mailSender.send(mess);
            return 0;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Send simple mail
     *
     * @param mailto  mailto addresses
     * @param mailcc  cc addresses
     * @param mailbcc bcc addresses
     * @param subject mail subject
     * @param content mail content
     * @param isHTML  if content displayed as HTML or not
     * @return 0:success -1:exception;-2:mailto is null
     */
    public int sendSimpleMail(String[] mailto, String[] mailcc,
                              String[] mailbcc, String subject, String content, boolean isHTML) {
        // Create mime message
        MimeMessage mess = mailSender.createMimeMessage();
        // Create mail helper to inject information into mess
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mess, true, "utf-8");
            helper.setFrom(mailSender.getUsername());
            if (mailto == null) {
                return -2;
            }
            if (mailcc != null)
                helper.setCc(mailcc);
            if (mailbcc != null)
                helper.setBcc(mailbcc);
            helper.setSubject(subject);
            helper.setText(content, isHTML);
            // Send mail
            mailSender.send(mess);
            return 0;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Send mail with attachments
     *
     * @param mailto     mailto address
     * @param mailcc     cc address
     * @param mailbcc    bcc address
     * @param subject    mail subject
     * @param content    mail content
     * @param isHTML     if content displayed as HTML or not
     * @param imgFile    image files
     * @param notImgFile files in other types
     * @return 0:success -1:exception;-2:mailto is null
     */
    public int sendAttachMail(String mailto, String mailcc, String mailbcc,
                              String subject, String content, boolean isHTML, File[] imgFile,
                              File[] notImgFile) {
        // Create mime message
        MimeMessage mess = mailSender.createMimeMessage();
        // Create mail helper to inject information into mess
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mess, true, "utf-8");
            helper.setFrom(mailSender.getUsername());
            if (mailto == null) {
                return -2;
            }
            if (mailcc != null)
                helper.setCc(mailcc);
            if (mailbcc != null)
                helper.setBcc(mailbcc);
            helper.setSubject(subject);
            helper.setText(content, isHTML);
            // Add image files
            if (imgFile != null && imgFile.length > 0)
                for (File img : imgFile)
                    helper
                            .addInline(MimeUtility.encodeWord(img.getName()),
                                    img);
            // Add other files
            if (notImgFile != null && notImgFile.length > 0)
                for (File notImg : notImgFile)
                    helper.addAttachment(MimeUtility.encodeWord(notImg
                            .getName()), notImg);
            // Send mail
            mailSender.send(mess);
            return 0;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Send mail with attachments
     *
     * @param mailto     mailto addresses
     * @param mailcc     cc addresses
     * @param mailbcc    bcc addresses
     * @param subject    mail subject
     * @param content    mail content
     * @param isHTML     if content displayed as HTML or not
     * @param imgFile    image files
     * @param notImgFile files in other types
     * @return 0:success -1:exception;-2:mailto is null
     */
    public int sendAttachMail(String[] mailto, String[] mailcc,
                              String[] mailbcc, String subject, String content, boolean isHTML,
                              File[] imgFile, File[] notImgFile) {
        // Create mime message
        MimeMessage mess = mailSender.createMimeMessage();
        // Create mail helper to inject information into mess
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mess, true, "utf-8");
            helper.setFrom(mailSender.getUsername());
            if (mailto == null) {
                return -2;
            }
            if (mailcc != null)
                helper.setCc(mailcc);
            if (mailbcc != null)
                helper.setBcc(mailbcc);
            helper.setSubject(subject);
            helper.setText(content, isHTML);
            // Add image files
            if (imgFile != null && imgFile.length > 0)
                for (File img : imgFile)
                    helper
                            .addInline(MimeUtility.encodeWord(img.getName()),
                                    img);
            // Add other files
            if (notImgFile != null && notImgFile.length > 0)
                for (File notImg : notImgFile)
                    helper.addAttachment(MimeUtility.encodeWord(notImg
                            .getName()), notImg);
            // Send mail
            mailSender.send(mess);
            return 0;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
    }
}