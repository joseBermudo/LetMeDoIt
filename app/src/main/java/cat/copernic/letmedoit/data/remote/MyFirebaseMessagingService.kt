package cat.copernic.letmedoit.data.remote

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.NotificationCompat
import cat.copernic.letmedoit.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * Clase Encargada de manjear las notificaciones de Firebase
 */
class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        //Titulo y Mensaje de la notificación
        val messageTitle = message.notification?.title
        val messageBody = message.notification?.body


        //notification builder
        //Inidcamos el channel personalizado
        var builder = NotificationCompat.Builder(this, getString(R.string.deals_notification_id))
            .setSmallIcon(R.drawable.logo_letmedoit)
            .setContentTitle(messageTitle)
            .setContentText(messageBody)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationId = System.currentTimeMillis().toInt()
        // notificacion manager
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(notificationId,builder.build())

    }
}