package com.example.testapplication.taks.interfaces;

import androidx.annotation.NonNull;
import com.example.testapplication.taks.Task;

public interface OnCompleteListener<TResult> {
    void onComplete(@NonNull Task<TResult> var1);
}

