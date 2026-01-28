# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# Gson
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }

# Hilt
-keepclassmembers class * {
    @javax.inject.Inject <init>(...);
}

# Room
-keep class uz.talim.data.local.database.** { *; }
-keep class * extends androidx.room.RoomDatabase
