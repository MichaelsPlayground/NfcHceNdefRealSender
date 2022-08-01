# NFC HCE NDEF Sender

This app emulates a NFC tag with a NDEF message.

For reading the emulated tag you need a second application: NfcHceNdefReader

This app uses the AID D2760000850101 - this is the official NFC tag 4 class NDEF AID

AndroidManifest.xml:
```plaintext
    <uses-permission android:name="android.permission.NFC" />
    <uses-feature android:name="android.hardware.nfc.hce" android:required="true" />
    <application
    ...
    
    </activity>
    <service android:name=".myHostApduService" android:exported="true"
            android:permission="android.permission.BIND_NFC_SERVICE">
            <intent-filter>
                <action android:name="android.nfc.cardemulation.action.HOST_APDU_SERVICE"/>
                <!-- category required!!! this was not included in official android HCE documentation -->
                <category android:name="android.intent.category.DEFAULT"/>
            </intent-filter>
            <meta-data android:name="android.nfc.cardemulation.host_apdu_service"
                android:resource="@xml/apduservice"/>
      </service>
    </application>
```

This file is located in res/xml/apduservice.xml:

apduservice.xml:
```plaintext
<?xml version="1.0" encoding="utf-8"?>
<host-apdu-service xmlns:android="http://schemas.android.com/apk/res/android"
    android:description="@string/servicedesc"
    android:requireDeviceUnlock="false">
    <aid-group android:description="@string/aiddescription"
        android:category="other">
        <!-- Sample for the demo application -->
        <aid-filter android:name="F0394148148100" />
    </aid-group>
</host-apdu-service>
```



```plaintext

```



```plaintext

```