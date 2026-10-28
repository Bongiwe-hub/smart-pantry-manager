
package com.example.smartpantrymanager;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.RequiresPermission;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// Creates notifications for ingredients that are expired
// or will expire within the next three days.
public class ExpiryNotificationHelper {

    // Unique ID for the notification channel.
    private static final String CHANNEL_ID = "pantry_expiry_channel";

    // Create the notification channel when this helper is initialized.
    public ExpiryNotificationHelper(Context context) {
        createNotificationChannel(context);
    }

    // Android 8.0 and newer require a notification channel.
    private void createNotificationChannel(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String channelName = "Pantry Expiry Alerts";
            String channelDescription =
                    "Alerts about expired or soon-to-expire ingredients";

            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    channelName,
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            channel.setDescription(channelDescription);

            NotificationManager manager =
                    context.getSystemService(NotificationManager.class);

            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
    }

    // Checks an ingredient's expiry date and displays an alert if needed.
    public void checkExpiryDate(
            Context context,
            String ingredientName,
            String expiryDate
    ) {

        // Do not process a missing expiry date or ingredient name.
        if (expiryDate == null || expiryDate.trim().isEmpty()
                || ingredientName == null || ingredientName.trim().isEmpty()) {
            return;
        }

        try {
            // Parse the expiry date using YYYY-MM-DD.
            SimpleDateFormat dateFormat =
                    new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

            // Reject invalid dates such as 2026-02-30.
            dateFormat.setLenient(false);

            Date expiry = dateFormat.parse(expiryDate.trim());

            if (expiry == null) {
                return;
            }

            // Compare calendar days without including the time.
            SimpleDateFormat dayFormat =
                    new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

            Date today = dayFormat.parse(dayFormat.format(new Date()));
            Date expiryDay = dayFormat.parse(dayFormat.format(expiry));

            if (today == null || expiryDay == null) {
                return;
            }

            long millisecondsPerDay = 24L * 60 * 60 * 1000;

            long daysRemaining =
                    (expiryDay.getTime() - today.getTime())
                            / millisecondsPerDay;

            // Do not notify for ingredients expiring more than three days away.
            if (daysRemaining > 3) {
                return;
            }

            // Android 13 and newer require notification permission.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                    && ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            // Create the notification message.
            String message;

            if (daysRemaining < 0) {
                message = ingredientName + " has expired.";
            } else if (daysRemaining == 0) {
                message = ingredientName + " expires today.";
            } else {
                message = ingredientName + " expires in "
                        + daysRemaining + " day(s).";
            }

            // Build the notification.
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(context, CHANNEL_ID)
                            .setSmallIcon(android.R.drawable.ic_dialog_info)
                            .setContentTitle("Pantry Expiry Alert")
                            .setContentText(message)
                            .setStyle(
                                    new NotificationCompat.BigTextStyle()
                                            .bigText(message)
                            )
                            .setPriority(
                                    NotificationCompat.PRIORITY_DEFAULT
                            )
                            .setAutoCancel(true);

            // Check permission again immediately before sending.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                    && ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            // Display the notification.
            try {
                sendNotification(
                        context,
                        ingredientName.hashCode(),
                        builder
                );
            } catch (SecurityException exception) {
                // Do not crash if Android denies notification access.
            }

        } catch (ParseException exception) {
            // Ignore invalid expiry dates instead of crashing.
        }
    }

    // Sends the notification after the permission checks above.
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private void sendNotification(
            Context context,
            int notificationId,
            NotificationCompat.Builder builder
    ) {
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(context);

        notificationManager.notify(notificationId, builder.build());
    }
}
