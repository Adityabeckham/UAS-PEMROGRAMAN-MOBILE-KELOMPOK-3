package com.example.uas_pemrogramanmobile_kelompok3.utils

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Properties
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

object EmailSenderUtil {

    // TODO: Ganti SENDER_APP_PASSWORD dengan App Password asli milik snaw58016@gmail.com!
    private const val SENDER_EMAIL = "snaw58016@gmail.com"
    private const val SENDER_APP_PASSWORD = "usqm butg okmv ldkx" // GANTI INI DENGAN APP PASSWORD GMAIL 

    suspend fun sendTokenEmail(targetEmail: String, candidateName: String, token: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val props = Properties()
                props["mail.smtp.auth"] = "true"
                props["mail.smtp.starttls.enable"] = "true"
                props["mail.smtp.host"] = "smtp.gmail.com"
                props["mail.smtp.port"] = "587"

                val session = Session.getInstance(props, object : Authenticator() {
                    override fun getPasswordAuthentication(): PasswordAuthentication {
                        return PasswordAuthentication(SENDER_EMAIL, SENDER_APP_PASSWORD)
                    }
                })

                val message = MimeMessage(session)
                message.setFrom(InternetAddress(SENDER_EMAIL, "AssessPro HR System"))
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(targetEmail))
                message.subject = "Token Akses Ujian Anda - AssessPro"
                
                val htmlContent = """
                    <h3>Halo $candidateName,</h3>
                    <p>Anda telah didaftarkan untuk mengikuti sesi ujian/assessment.</p>
                    <p>Berikut adalah rincian akses Anda:</p>
                    <div style="padding: 15px; border: 1px solid #ccc; background-color: #f9f9f9; width: max-content;">
                        <h2 style="color: #4A148C; margin: 0;">Token Akses: $token</h2>
                    </div>
                    <p>Silakan buka aplikasi AssessPro, pilih menu "Masuk sebagai Peserta Ujian", dan masukkan token di atas.</p>
                    <br/>
                    <p>Semoga Sukses!</p>
                    <p><i>Tim Rekrutmen</i></p>
                """.trimIndent()

                message.setContent(htmlContent, "text/html; charset=utf-8")

                Transport.send(message)
                Log.d("EmailSender", "Email token berhasil dikirim ke $targetEmail")
                true
            } catch (e: Exception) {
                Log.e("EmailSender", "Gagal mengirim email: ${e.message}", e)
                e.printStackTrace()
                false
            }
        }
    }
}
