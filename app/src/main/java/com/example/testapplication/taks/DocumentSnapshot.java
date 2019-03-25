package com.example.testapplication.taks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

public class DocumentSnapshot {

    @Nullable
    public void get(@NonNull String fieldPath) throws IOException {
        final Properties properties = new Properties();
        return properties.load(new StringReader(fieldPath));
    }
}

